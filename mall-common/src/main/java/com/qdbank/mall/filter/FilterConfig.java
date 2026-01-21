package com.qdbank.mall.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author Qdccb
 */
@Configuration
public class FilterConfig {
    /**
     * 注册过滤器
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean<Filter> filterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(antiReplayFilter());
        registration.addUrlPatterns("/*");
        registration.setName("antiReplayFilter");
        return registration;
    }

    /**
     * 实例化Filter
     *
     * @return Filter
     */
    @Bean
    public Filter antiReplayFilter() {
        return new AntiReplayFilter();
    }
}