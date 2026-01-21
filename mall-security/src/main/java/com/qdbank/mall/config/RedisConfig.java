package com.qdbank.mall.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Redis配置类
 * Created by ningyuehuai on 2020/10/2.
 */
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {

}
