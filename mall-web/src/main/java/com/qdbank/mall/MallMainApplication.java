package com.qdbank.mall;

import com.yomahub.tlog.core.enhance.AspectLogEnhance;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by ningyh on 2020/10/25 上午10:54
 * <p>
 * describe：
 */
@SpringBootApplication
@MapperScan("com.qdbank.mall.mapper")//多模块下需要加该配置，否则找不到mapper
@EnableAsync
@EnableTransactionManagement
@EnableFeignClients
@EnableDiscoveryClient
public class MallMainApplication extends SpringBootServletInitializer {
    static {
        AspectLogEnhance.enhance();
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MallMainApplication.class);
    }

    public static void main(String[] args) {
        System.setProperty("zookeeper.sasl.client", "false");
        SpringApplication.run(MallMainApplication.class, args);
    }
}
