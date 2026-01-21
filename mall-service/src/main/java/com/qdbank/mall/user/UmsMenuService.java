package com.qdbank.mall.user;


import com.github.pagehelper.PageInfo;
import com.qdbank.mall.model.menu.UmsMenuNode;
import com.qdbank.mall.request.menu.MenuReqDTO;
import com.qdbank.mall.request.menu.UpdateMenuReqDTO;
import com.qdbank.mall.response.menu.MenuResDTO;

import java.util.List;

/**
 * 后台菜单管理Service
 * Created by ningyuehuai on 2020/10/2.
 */
public interface UmsMenuService {
    /**
     * 创建后台菜单
     */
    int create(MenuReqDTO UmsMenuDO);

    /**
     * 修改后台菜单
     */
    int update(Long id, UpdateMenuReqDTO menuReqDTO);

    /**
     * 根据ID获取菜单详情
     */
    MenuResDTO getItem(Long id);

    /**
     * 根据ID删除菜单
     */
    int delete(Long id);

    /**
     * 分页查询后台菜单
     */
    PageInfo<MenuResDTO> list(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 树形结构返回所有菜单列表
     */
    List<UmsMenuNode> treeList();

    /**
     * 修改菜单显示状态
     */
    int updateHidden(Long id, Integer hidden);
}
