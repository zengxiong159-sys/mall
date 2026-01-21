package com.qdbank.mall.merchant.impl;

import com.qdbank.mall.merchant.MerchantCacheService;
import com.qdbank.mall.model.merchant.MerchantDO;
import com.qdbank.mall.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * @ClassName MerchantCashServiceImpl
 * @Description 商户信息缓存
 * @Author ningyuehuai
 * @Date 2021/2/24 11:42
 * @Version 1.0
 **/
@Service
@RefreshScope
public class MerchantCacheServiceImpl implements MerchantCacheService {
    @Autowired
    private RedisService redisService;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.merchant}")
    private String REDIS_KEY_MERCHANT;

    @Override
    public void setMerchant(MerchantDO merchant) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MERCHANT + ":" + merchant.getMerchantNo();
        redisService.set(key, merchant, REDIS_EXPIRE);
    }

    @Override
    public void delMerchant(Long id) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MERCHANT + ":" + id;
        redisService.del(key);
    }

    @Override
    public MerchantDO getMerchantById(Long id) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MERCHANT + ":" + id;
        return (MerchantDO) redisService.get(key);
    }
}
