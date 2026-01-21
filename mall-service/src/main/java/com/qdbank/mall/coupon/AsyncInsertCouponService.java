package com.qdbank.mall.coupon;

import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.usercoupon.UserCouponDO;

import java.util.Date;
import java.util.List;

public interface AsyncInsertCouponService {
    public void batchAnsyInsert(List<UserCouponDO> userCouponDOList);
    public void batchInsert(List<UserCouponDO> userCouponDOList);
    public void deleteFile(String groupName,String filePath);
    public <T> List<List<T>> buildBatchList(List<T> list);
    public void batchUpdate(List<OrderDO> orderDOList);

    public void batchInsertOrder(List<OrderDO> orderDOS);

    /**
     * 异步发送批次券到MQ
     * @param batchNo
     */
    public void batchAnsySendMq(String batchNo,Long status);

    /**
     * 异步发送优惠券到账提醒mq
     *
     * @param couponDO  券信息
     * @param sendTime 发放时间
     */
    void batchAsyncSendCouponToAcctMq(CouponDO couponDO, Date sendTime);

}
