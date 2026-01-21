package com.qdbank.mall.config;

import com.qdbank.mall.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 */
@Configuration
@EnableSwagger2
@Profile({"sit","uat","sit2"})
public class SwaggerCmsConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.qdbank.mall.controller")
                .title("商户端管理后台系统")
                .description("商户端管理后台系统相关接口文档")
                .contactName("mall")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
