package com.qdbank.mall.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Qdccb
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public AntiReplayInterceptor getInterceptor(){
        return new AntiReplayInterceptor();
    }

    @Bean
    public BankGatewayInterceptor  getBankGatewayInterceptor(){return new BankGatewayInterceptor();}

    /**
     * 注册拦截器
     *
     * @param registry registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/*/now",
                "/getToken",
                "/profile/**",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/webjars/**",
                "/*/api-docs",
                "/favicon.ico",
                "/actuator/**",
                "/error","/*/logout","/admin/info","/*/listAll","/**/export","/third/**","/*/task","/*/userCouponlist","/*/couponStatus","/*/publishStatus","/*/productByIds","/pay/**")
        ;

        registry.addInterceptor(getBankGatewayInterceptor()).addPathPatterns("/third/bank/notice");
    }
}