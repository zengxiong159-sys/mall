package com.qdbank.mall.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.mapper.menu.UmsMenuDOMapper;
import com.qdbank.mall.model.menu.UmsMenuDO;
import com.qdbank.mall.model.menu.UmsMenuDOExample;
import com.qdbank.mall.model.menu.UmsMenuNode;
import com.qdbank.mall.request.menu.MenuReqDTO;
import com.qdbank.mall.request.menu.UpdateMenuReqDTO;
import com.qdbank.mall.response.menu.MenuResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.user.UmsMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台菜单管理Service实现类
 * Created by ningyuehuai on 2020/10/2.
 */
@Service
public class UmsMenuServiceImpl extends BaseServiceImpl implements UmsMenuService {
    @Autowired
    private UmsMenuDOMapper menuMapper;
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @Override
    public int create(MenuReqDTO menuReqDTO) {
        UmsMenuDO umsMenuDO = new UmsMenuDO();
        BeanUtils.copyProperties(menuReqDTO,umsMenuDO);
        umsAdminService.injectUserValue(umsMenuDO);
        umsMenuDO.setId(super.generateId());
        updateLevel(umsMenuDO);
        return menuMapper.insert(umsMenuDO);
    }

    /**
     * 修改菜单层级
     */
    private void updateLevel(UmsMenuDO umsMenuDO) {
        if (umsMenuDO.getParentId() == 0) {
            umsMenuDO.setMenuLevel(0L);
            //没有父菜单时为一级菜单
        } else {
            //有父菜单时选择根据父菜单level设置
            UmsMenuDO parentMenu = menuMapper.selectByPrimaryKey(umsMenuDO.getParentId());
            if (parentMenu != null) {
                umsMenuDO.setMenuLevel(parentMenu.getMenuLevel() + 1);
            } else {
                umsMenuDO.setMenuLevel(0L);
            }
        }
    }

    @Override
    public int update(Long id, UpdateMenuReqDTO menuReqDTO) {
        UmsMenuDO umsMenuDO = new UmsMenuDO();
        BeanUtils.copyProperties(menuReqDTO,umsMenuDO);
        umsMenuDO.setId(id);
        umsAdminService.injectUpdateUserValue(umsMenuDO);
        updateLevel(umsMenuDO);
        return menuMapper.updateByPrimaryKeySelective(umsMenuDO);
    }

    @Override
    public MenuResDTO getItem(Long id) {
        UmsMenuDO menuDO = menuMapper.selectByPrimaryKey(id);
        MenuResDTO menuResDTO = new MenuResDTO();
        BeanUtils.copyProperties(menuDO,menuResDTO);
        return menuResDTO;
    }

    @Override
    public int delete(Long id) {
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<MenuResDTO> list(Long parentId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsMenuDOExample example = new UmsMenuDOExample();
        example.setOrderByClause("create_time desc");
        example.createCriteria().andParentIdEqualTo(parentId);
        List<UmsMenuDO> umsMenuDOList = menuMapper.selectByExample(example);
        List<MenuResDTO> menuResDTOList = new ArrayList<>();
        for(UmsMenuDO umsMenuDO : umsMenuDOList){
            MenuResDTO menuResDTO = new MenuResDTO();
            BeanUtils.copyProperties(umsMenuDO,menuResDTO);
            menuResDTOList.add(menuResDTO);
        }
        return super.getPageInfo(umsMenuDOList,menuResDTOList);
    }

    @Override
    public List<UmsMenuNode> treeList() {
        List<UmsMenuDO> menuList = menuMapper.selectByExample(new UmsMenuDOExample());
        List<UmsMenuNode> result = menuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
        return result;
    }

    @Override
    public int updateHidden(Long id, Integer hidden) {
        UmsMenuDO UmsMenuDO = new UmsMenuDO();
        UmsMenuDO.setId(id);
        return menuMapper.updateByPrimaryKeySelective(UmsMenuDO);
    }

    /**
     *
     * @param menu 父级菜单
     * @param menuList 所有菜单
     * @return 返回父级菜单+当前父级菜单下子菜单
     */
    private UmsMenuNode covertMenuNode(UmsMenuDO menu, List<UmsMenuDO> menuList) {
        UmsMenuNode node = new UmsMenuNode();
        BeanUtils.copyProperties(menu, node);
        List<UmsMenuNode> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
