package com.qdbank.mall.user.impl;

import cn.hutool.core.collection.CollUtil;
import com.qdbank.mall.mapper.user.UmsAdminRoleRelationMapper;
import com.qdbank.mall.model.user.UmsResourceDO;
import com.qdbank.mall.model.user.UserInfoDO;
import com.qdbank.mall.model.user.UmsAdminRoleRelation;
import com.qdbank.mall.model.user.UmsAdminRoleRelationExample;
import com.qdbank.mall.response.user.UserInfoResDTO;
import com.qdbank.mall.service.RedisService;
import com.qdbank.mall.user.UmsAdminCacheService;
import com.qdbank.mall.user.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UmsAdminCacheService实现类
 * Created by ningyuehuai on 2020/10/13.
 */
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;

    /**
     * 用户缓存信息
     * 30 min
     */
    private Long ADMIN_REDIS_EXPIRE= 30*60L;

    @Override
    public void delAdmin(Long adminId) {
        UserInfoResDTO admin = adminService.getItem(adminId);
        if (admin != null) {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
            redisService.del(key);
        }
    }

    @Override
    public void delResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.del(key);
    }

    @Override
    public void delResourceListByRole(Long roleId) {
        UmsAdminRoleRelationExample example = new UmsAdminRoleRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<UmsAdminRoleRelation> relationList = adminRoleRelationMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getAdminId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {
        UmsAdminRoleRelationExample example = new UmsAdminRoleRelationExample();
        example.createCriteria().andRoleIdIn(roleIds);
        List<UmsAdminRoleRelation> relationList = adminRoleRelationMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getAdminId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public void delResourceListByResource(Long resourceId) {
        List<Long> adminIdList = adminRoleRelationMapper.getAdminIdList(resourceId);
        if (CollUtil.isNotEmpty(adminIdList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = adminIdList.stream().map(adminId -> keyPrefix + adminId).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public UserInfoDO getAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return (UserInfoDO) redisService.get(key);
    }

    @Override
    public UserInfoDO getAdminById(Long id) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + id;
        return (UserInfoDO)redisService.get(key);
    }

    @Override
    public void setAdminById(UserInfoDO admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getId();
        redisService.set(key, admin, REDIS_EXPIRE);
    }

    @Override
    public void setAdmin(UserInfoDO admin) {
        if(admin == null) return ;
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
        redisService.set(key, admin, ADMIN_REDIS_EXPIRE);
    }

    @Override
    public List<UmsResourceDO> getResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        return (List<UmsResourceDO>) redisService.get(key);
    }

    @Override
    public void setResourceList(Long adminId, List<UmsResourceDO> resourceList) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.set(key, resourceList, REDIS_EXPIRE);
    }

    @Override
    public Long getRedisIncrID(String key, Long delta) {
        String sourceKey = getUsernameLockKey(key);
        return redisService.incr(sourceKey,delta != null ? delta : 1);
    }
    @Override
    public Boolean deleteKey(String key){
        String sourceKey =  getUsernameLockKey(key);
        return redisService.del(sourceKey);
    }

    @Override
    public Boolean deleteByUsername(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return redisService.del(key);
    }

    public String getUsernameLockKey(String key){
       return REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":"+key;
    }

}
