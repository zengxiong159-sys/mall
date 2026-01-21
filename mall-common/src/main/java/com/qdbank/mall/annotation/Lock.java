package com.qdbank.mall.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 *
 * 分布式锁 <br>
 * @author hongjh
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Lock {

    /**
     * 锁名称,如果不指定，则为方法签名，可以用"@parameterName"的方式引用横切方法中参数值作为锁名称
     * 
     * @return
     */
    String name() default "";

    /**
     * 最大锁定时间
     * 
     * @return
     */
    long leaseTime();

    /**
     * 锁定时间单位
     * 
     * @return
     */
    TimeUnit timeUnit();

    /**
     * 完成后是否释放锁
     * 
     * @return
     */
    boolean unLockAfterDone() default true;
}