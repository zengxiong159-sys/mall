package com.qdbank.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import com.yomahub.tlog.core.enhance.*;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.qdbank.mall.mapper")//多模块下需要加该配置，否则找不到mapper
@EnableAsync
@EnableTransactionManagement
@EnableFeignClients
@EnableDiscoveryClient
public class MallCmsMainApplication extends SpringBootServletInitializer {
    static {
        AspectLogEnhance.enhance();
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MallCmsMainApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MallCmsMainApplication.class, args);
    }
}
