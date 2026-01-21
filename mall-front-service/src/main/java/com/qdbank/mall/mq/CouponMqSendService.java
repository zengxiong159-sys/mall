package com.qdbank.mall.mq;

import com.qdbank.mall.domain.CouponMQBO;

public interface CouponMqSendService {
    /**
     * 优惠券信息发送MQ
     * @param couponMQBO
     */
    public void couponMqSend(CouponMQBO couponMQBO);
}
