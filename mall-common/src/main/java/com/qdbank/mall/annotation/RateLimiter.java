package com.qdbank.mall.annotation;

import com.qdbank.mall.enums.LimitType;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    /**
     * 限流key
     */
    String key() default "";

    /**
     * 限流时间,单位秒
     */
    int time() default 120;

    /**
     * 限流次数
     */
    int count() default 5;


}
