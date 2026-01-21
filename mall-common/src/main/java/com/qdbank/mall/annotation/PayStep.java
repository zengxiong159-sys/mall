package com.qdbank.mall.annotation;

import com.qdbank.mall.constant.payment.event.RealOrderHandlerEvent;
import com.qdbank.mall.constant.payment.orderstatus.RealOrderStatus;

import java.lang.annotation.*;


/**
 * 支付步骤-流程
 * @author hongjh
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PayStep {

    /**
     * The source states.
     *
     * @return the source states.
     */
    RealOrderStatus[] source() default {};

    /**
     * The target states.
     *
     * @return the target states.
     */
    RealOrderStatus[] target() default {};


    /**
     * 事件类型
     *
     * @return the target states.
     */
    RealOrderHandlerEvent event();

}
