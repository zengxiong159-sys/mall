package com.qdbank.mall.coupon;

import com.qdbank.mall.domain.CouponMQBO;
import com.qdbank.mall.model.usercoupon.UserCouponDO;
import com.qdbank.mall.model.usercoupon.UserCouponDetailDO;

import java.util.List;

public interface CouponMqSendService {
    /**
     * 优惠券信息发送MQ
     * @param couponMQBO
     */
    public void couponMqSend(CouponMQBO couponMQBO);

    public void couponListMqSend(List<UserCouponDO> userCouponDOList);

    public void userCouponDetailMqSend(UserCouponDetailDO userCouponDetailDO);
}
