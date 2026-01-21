package com.qdbank.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import com.yomahub.tlog.core.enhance.*;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName MallFrontMainApplication
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/18 10:00
 * @Version 1.0
 **/
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableCaching
@EnableCircuitBreaker
@MapperScan("com.qdbank.mall.mapper")//多模块下需要加该配置，否则找不到mapper
@EnableFeignClients
@EnableDiscoveryClient
public class MallFrontMainApplication extends SpringBootServletInitializer {
    static {
        AspectLogEnhance.enhance();
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MallFrontMainApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MallFrontMainApplication.class, args);
    }

}

