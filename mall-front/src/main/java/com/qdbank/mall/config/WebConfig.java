package com.qdbank.mall.config;


import com.qdbank.mall.util.JwtTokenUtil;
import com.qdbank.mall.web.JwtTokenFilter;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * web 服务定义
 */
@Configuration
public class WebConfig {

   /* @Bean
    public SecurityConfig securityConfig(){
        return new SecurityConfig();
    }*/


    @Bean("jwtFilter")
    public Filter jwtFilter(){
        return new JwtTokenFilter();
    }


    @Bean
    public DelegatingFilterProxyRegistrationBean delegatingFilterProxyRegistrationBean(){
        DelegatingFilterProxyRegistrationBean bean = new DelegatingFilterProxyRegistrationBean("jwtFilter");
        bean.addUrlPatterns("/*");
        return bean;
    }


}
