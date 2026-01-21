package com.qdbank.mall.paramsconfig.impl;

import com.qdbank.mall.model.paramsconfig.ParamsConfigDO;
import com.qdbank.mall.paramsconfig.ParamsConfigCacheService;
import com.qdbank.mall.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * @ClassName ParamsConfigCacheServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/1 17:33
 * @Version 1.0
 **/
@Service
public class ParamsConfigCacheServiceImpl implements ParamsConfigCacheService {
    @Autowired
    private RedisService redisService;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    private static final String REDIS_KEY_PARAMS_CONFIG = "REDIS_KEY_PARAMS_CONFIG";

    @Override
    public void setParamsConfigDO(ParamsConfigDO paramsConfigDO) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_PARAMS_CONFIG + ":" + paramsConfigDO.getId();
        redisService.set(key, paramsConfigDO, REDIS_EXPIRE);
    }

    @Override
    public void delParamsConfig(Long id) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_PARAMS_CONFIG + ":" + id;
        redisService.del(key);
    }

    @Override
    public ParamsConfigDO getParamsConfigById(Long id) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_PARAMS_CONFIG + ":" + id;
        return (ParamsConfigDO) redisService.get(key);
    }

    @Override
    public void setParamsConfigByName(ParamsConfigDO paramsConfigDO) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_PARAMS_CONFIG + ":" + paramsConfigDO.getParamName();
        redisService.set(key, paramsConfigDO, REDIS_EXPIRE);
    }

    @Override
    public ParamsConfigDO getParamsConfigByName(String paramName) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_PARAMS_CONFIG + ":" + paramName;
        return (ParamsConfigDO) redisService.get(key);
    }

    @Override
    public void deleteParamsConfigByName(String paramName) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_PARAMS_CONFIG + ":" + paramName;
        redisService.del(key);
    }
}
