package com.qdbank.mall.coupon;

import com.qdbank.mall.model.usercoupon.UserCouponDetailDO;

import java.util.List;

/**
 * @Author zengxiong
 * @Description 订阅消息优惠券到期提醒任务
 * @Date 2022/7/26 11:03
 */
public interface CouponExpireNoticeService {

    /**
     * 批量异步发送优惠券到期提醒mq
     *
     * @param userCouponDOList 用户优惠券信息
     */
    void batchAsyncSendMq(List<UserCouponDetailDO> userCouponDOList);

}
