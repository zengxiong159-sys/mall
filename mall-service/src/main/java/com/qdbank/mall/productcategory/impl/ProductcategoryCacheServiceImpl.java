package com.qdbank.mall.productcategory.impl;

import com.qdbank.mall.model.productcategory.ProductcategoryDO;
import com.qdbank.mall.productcategory.ProductcategoryCacheService;
import com.qdbank.mall.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * @ClassName ProductcategoryCacheServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/7 10:15
 * @Version 1.0
 **/
@Service
public class ProductcategoryCacheServiceImpl implements ProductcategoryCacheService {
    @Autowired
    private RedisService redisService;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    private static final String REDIS_KEY_PARAMS_CONFIG = "REDIS_KEY_PRODUCT_CATEGORY";

    @Override
    public void setProductCategoryDO(ProductcategoryDO productCategoryDO) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_PARAMS_CONFIG + ":" + productCategoryDO.getId();
        redisService.set(key, productCategoryDO, REDIS_EXPIRE);
    }

    @Override
    public ProductcategoryDO getProductcategoryById(Long id) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_PARAMS_CONFIG + ":" + id;
        return (ProductcategoryDO) redisService.get(key);
    }

    @Override
    public void delProductcategory(Long id) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_PARAMS_CONFIG + ":" + id;
        redisService.del(key);
    }
}
