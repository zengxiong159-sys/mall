package com.qdbank.mall.cmsuser;


import com.qdbank.mall.model.user.UmsResourceDO;
import com.qdbank.mall.model.user.UserInfoDO;

import java.util.List;

/**
 * 后台用户缓存操作类
 * Created by ningyuehuai on 2020/10/13.
 */
public interface CmsAdminCacheService {
    /**
     * 删除后台用户缓存
     */
    void delAdmin(Long adminId);

    /**
     * 删除后台用户资源列表缓存
     */
    void delResourceList(Long adminId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    void delResourceListByRole(Long roleId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    void delResourceListByRoleIds(List<Long> roleIds);

    /**
     * 当资源信息改变时，删除资源项目后台用户缓存
     */
    void delResourceListByResource(Long resourceId);

    /**
     * 获取缓存后台用户信息
     */
    UserInfoDO getAdmin(String username);

    /**
     * 根据用户id查询用户详情
     * @param id
     * @return
     */
    UserInfoDO getAdminById(Long id);

    /**
     * 使用主键作为key存入缓存中
     * @param  admin
     */
    void setAdminById(UserInfoDO admin);

    /**
     * 设置缓存后台用户信息
     */
    void setAdmin(UserInfoDO admin);

    /**
     * 获取缓存后台用户资源列表
     */
    List<UmsResourceDO> getResourceList(Long adminId);

    /**
     * 设置后台后台用户资源列表
     */
    void setResourceList(Long adminId, List<UmsResourceDO> resourceList);

    /**
     * 使用redis获取自增主键
     * @param key
     * @param delta 步长
     * @return
     */
    Long getRedisIncrID(String key,Long delta);

    /**
     * 密码输入错误删除缓存
     * @param key
     * @return
     */
    public Boolean deleteKey(String key);

    /**
     * 根据用户名删除缓存中数据
     * @param username
     * @return
     */
    public Boolean deleteByUsername(String username);

    /**
     * 根据指定参数删除缓存数据
     * @param redisDatabase 数据库
     * @param redisKeyAdmin 系统名
     * @param userName  用户名
     * @return 删除结果 true:成功 false:失败
     */
    Boolean deleteKey(String redisDatabase, String redisKeyAdmin, String userName);
}
