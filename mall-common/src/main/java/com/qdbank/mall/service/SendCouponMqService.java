package com.qdbank.mall.service;

import com.qdbank.mall.domain.CouponMQBO;

public interface SendCouponMqService {
    /**
     * 发送优惠券消息
     * @param couponMQBO
     */
    public int sendCouponMessage(CouponMQBO couponMQBO);
}
