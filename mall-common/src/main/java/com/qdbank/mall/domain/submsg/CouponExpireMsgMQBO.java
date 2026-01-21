package com.qdbank.mall.domain.submsg;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zengxiong
 * @Description 优惠券到期提醒订阅消息kafka定义
 * @Date 2022/7/25 16:00
 */
@Data
public class CouponExpireMsgMQBO implements Serializable {

    private static final long serialVersionUID = 598065837382726803L;

    /**
     * 优惠券名称
     */
    private String couponName;

    /**
     * 到期时间
     */
    private String expireDate;

    /**
     * 优惠券金额
     */
    private String couponAmount;

    /**
     * 跳转链接
     */
    private String jumpUrl;

}
