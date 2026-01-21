package com.qdbank.mall.menu;

import com.qdbank.mall.model.user.UmsAdminRoleRelation;
import com.qdbank.mall.request.role.RoleQueryLikeReqDTO;
import com.qdbank.mall.request.role.RoleReqDTO;
import com.qdbank.mall.request.role.UpdateRoleReqDTO;
import com.qdbank.mall.response.menu.MenuResDTO;
import com.qdbank.mall.response.resource.ResourceResDTO;
import com.qdbank.mall.response.role.RoleResDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ningyh on 2020/10/25 下午1:16
 * <p>
 * describe：
 */
public interface UmsRoleService {
    /**
     * 添加角色
     */
    int create(RoleReqDTO role);

    /**
     * 修改角色信息
     */
    int update(Long id, UpdateRoleReqDTO role);

    /**
     * 批量删除角色
     */
    int delete(List<Long> ids);

    /**
     * 获取所有角色列表
     */
    List<RoleResDTO> list();

    /**
     * 分页获取角色列表
     */
    List<RoleResDTO> list(RoleQueryLikeReqDTO roleQueryLikeReqDTO);

    /**
     * 根据管理员ID获取对应菜单
     */
    List<MenuResDTO> getMenuList(Long adminId);

    /**
     * 获取角色相关菜单
     */
    List<MenuResDTO> listMenu(Long roleId);

    /**
     * 获取角色相关资源
     */
    List<ResourceResDTO> listResource(Long roleId);

    /**
     * 给角色分配菜单
     */
    @Transactional
    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     * 给角色分配资源
     */
    @Transactional
    int allocResource(Long roleId, List<Long> resourceIds);

    /**
     * 获取指定角色id与用户关联关系
     * @param id
     * @return
     */
    List<UmsAdminRoleRelation> getRelationListById(List<Long> roleIds);


}
