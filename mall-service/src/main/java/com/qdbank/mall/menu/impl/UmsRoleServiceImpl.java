package com.qdbank.mall.menu.impl;

import com.github.pagehelper.PageHelper;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.role.RoleDOMapper;
import com.qdbank.mall.mapper.role.UmsRoleMenuRelationMapper;
import com.qdbank.mall.mapper.role.UmsRoleResourceRelationMapper;
import com.qdbank.mall.mapper.user.UmsAdminRoleRelationMapper;
import com.qdbank.mall.menu.UmsRoleService;
import com.qdbank.mall.model.menu.UmsMenuDO;
import com.qdbank.mall.model.role.*;
import com.qdbank.mall.model.user.UmsAdminRoleRelation;
import com.qdbank.mall.model.user.UmsAdminRoleRelationExample;
import com.qdbank.mall.model.user.UmsResourceDO;
import com.qdbank.mall.request.role.RoleQueryLikeReqDTO;
import com.qdbank.mall.request.role.RoleReqDTO;
import com.qdbank.mall.request.role.UpdateRoleReqDTO;
import com.qdbank.mall.response.menu.MenuResDTO;
import com.qdbank.mall.response.resource.ResourceResDTO;
import com.qdbank.mall.response.role.RoleResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminCacheService;
import com.qdbank.mall.user.UmsAdminService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台角色管理Service实现类
 * Created by ningyuehuai on 2020/10/30.
 */
@Service
public class UmsRoleServiceImpl extends BaseServiceImpl implements UmsRoleService {
    @Autowired
    private RoleDOMapper roleMapper;
    @Autowired
    private UmsRoleMenuRelationMapper roleMenuRelationMapper;
    @Autowired
    private UmsRoleResourceRelationMapper roleResourceRelationMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Override
    public int create(RoleReqDTO role) {
        RoleDO roleDO = new RoleDO();
        BeanUtils.copyProperties(role,roleDO);
        roleDO.setRoleSort(0L);
        umsAdminService.injectUserValue(roleDO);
        roleDO.setId(super.generateId());
        return roleMapper.insert(roleDO);
    }

    @Override
    public int update(Long id, UpdateRoleReqDTO role) {
        RoleDO roleDO = new RoleDO();
        BeanUtils.copyProperties(role,roleDO);
        umsAdminService.injectUpdateUserValue(roleDO);
        roleDO.setId(id);
        return roleMapper.updateByPrimaryKeySelective(roleDO);
    }


    @Override
    public int delete(List<Long> ids) {
        RoleDOExample example = new RoleDOExample();
        example.createCriteria().andIdIn(ids);
        if(CollectionUtils.isNotEmpty(getRelationListById(ids))){
            throw  new ApiException(ResultCode.ROLE_ID_BIND);
        }
        int count = roleMapper.deleteByExample(example);
        adminCacheService.delResourceListByRoleIds(ids);
        return count;
    }

    @Override
    public List<RoleResDTO> list() {
        RoleDOExample roleDOExample = new RoleDOExample();
        roleDOExample.setOrderByClause("create_time desc");
        roleDOExample.createCriteria().andStatusEqualTo(1L);
        return this.getAllRole(roleDOExample);
    }

    @Override
    public List<RoleResDTO> list(RoleQueryLikeReqDTO roleQueryLikeReqDTO) {
        PageHelper.startPage(roleQueryLikeReqDTO.getPageNum(), roleQueryLikeReqDTO.getPageSize());
        RoleDOExample example = new RoleDOExample();
        RoleDOExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(roleQueryLikeReqDTO.getRoleName())) {
            criteria.andRoleNameLike("%" + roleQueryLikeReqDTO.getRoleName() + "%");
        }
        if(roleQueryLikeReqDTO.getId() != null){
            criteria.andIdEqualTo(roleQueryLikeReqDTO.getId());
        }
        return this.getAllRole(example);
    }

    @Override
    public List<MenuResDTO> getMenuList(Long adminId) {
        return changeList(roleMapper.getMenuList(adminId));
    }

    @Override
    public List<MenuResDTO> listMenu(Long roleId) {
        List<UmsMenuDO> menuDOS = roleMapper.getMenuListByRoleId(roleId);
        return this.changeList(menuDOS);
    }

    @Override
    public List<ResourceResDTO> listResource(Long roleId) {
        return this.getRoleResources(roleMapper.getResourceListByRoleId(roleId));
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        //先删除原有关系
        UmsRoleMenuRelationExample example=new UmsRoleMenuRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleMenuRelationMapper.deleteByExample(example);
        UmsRoleMenuRelationDO relation = new UmsRoleMenuRelationDO();
        relation.setRoleId(roleId);
        umsAdminService.injectUserValue(relation);
        //批量插入新关系
        for (Long menuId : menuIds) {
            relation.setId(super.generateId());
            relation.setMenuId(menuId);
            roleMenuRelationMapper.insert(relation);
        }
        return menuIds.size();
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        //先删除原有关系
        UmsRoleResourceRelationExample example=new UmsRoleResourceRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        int count = roleResourceRelationMapper.deleteByExample(example);
        UmsRoleResourceRelationDO relation = new UmsRoleResourceRelationDO();
        relation.setRoleId(roleId);
        umsAdminService.injectUserValue(relation);
        int insertCount = 0 ;
        if(CollectionUtils.isNotEmpty(resourceIds)){
            //批量插入新关系
            for (Long resourceId : resourceIds) {
                if(resourceId.equals(210445612672376832L)){
                    relation.setResourceId(214469071173869568L);
                    relation.setId(super.generateId());
                    roleResourceRelationMapper.insert(relation);
                }
                if(resourceId.equals(210449517737172992L)){
                    relation.setResourceId(214469218180030464L);
                    relation.setId(super.generateId());
                    roleResourceRelationMapper.insert(relation);
                }
                if(resourceId.equals(210453736842092544L)){
                    relation.setResourceId(214469407926149120L);
                    relation.setId(super.generateId());
                    roleResourceRelationMapper.insert(relation);
                }
                relation.setResourceId(resourceId);
                relation.setId(super.generateId());
                insertCount = roleResourceRelationMapper.insert(relation);
            }
        }
        adminCacheService.delResourceListByRole(roleId);
        return insertCount;
    }

    @Override
    public List<UmsAdminRoleRelation> getRelationListById(List<Long> roleIds) {
        UmsAdminRoleRelationExample example = new UmsAdminRoleRelationExample();
        example.createCriteria().andRoleIdIn(roleIds);
        List<UmsAdminRoleRelation> relationList = adminRoleRelationMapper.selectByExample(example);
        return relationList;
    }

    private List<RoleResDTO> getAllRole(RoleDOExample example){
        List<RoleResDTO> roleResDTOS = new ArrayList<>();
        List<RoleDO> roleDOS = roleMapper.selectByExample(example);
        for(RoleDO roleDO : roleDOS){
            RoleResDTO roleResDTO = new RoleResDTO();
            BeanUtils.copyProperties(roleDO,roleResDTO);
            roleResDTOS.add(roleResDTO);
        }
        return roleResDTOS;
    }
    private List<MenuResDTO> changeList(List<UmsMenuDO> menuDOS){
        List<MenuResDTO> resDTOList = new ArrayList<>();
        for(UmsMenuDO menuDO : menuDOS){
            MenuResDTO menuResDTO = new MenuResDTO();
            BeanUtils.copyProperties(menuDO,menuResDTO);
            resDTOList.add(menuResDTO);
        }
        return resDTOList;
    }

    private List<ResourceResDTO> getRoleResources(List<UmsResourceDO> resourceDOS){
        List<ResourceResDTO> resourceResDTOS = new ArrayList<>();
        for(UmsResourceDO umsResourceDO : resourceDOS){
            ResourceResDTO resourceResDTO = new ResourceResDTO();
            BeanUtils.copyProperties(umsResourceDO,resourceResDTO);
            resourceResDTOS.add(resourceResDTO);
        }
        return resourceResDTOS;
    }

}
