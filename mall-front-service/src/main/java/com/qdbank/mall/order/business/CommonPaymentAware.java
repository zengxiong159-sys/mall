package com.qdbank.mall.order.business;

import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.request.order.CommonOrderReqDTO;
import com.qdbank.mall.response.third.payment.RefundResDTO;

/**
 * 支付系统抽象
 * @author hongjh
 * @date 2021/3/21
 */
public interface CommonPaymentAware {

    /**
     * 商品支付类型
     * @return
     */
    ProductEnum getProductType();

    /**
     * 下单通用逻辑
     * @param order
     * @param commonOrderReq
     */
    default void preCreateOrderCheck(OrderDO order, CommonOrderReqDTO commonOrderReq){};

    /**
     * 下单处理后逻辑
     * @param order
     * @param commonOrderReq
     */
    default void afterCreateOrder(OrderDO order, CommonOrderReqDTO commonOrderReq){};


    /**
     * 取消逻辑
     * @param order
     */
    default void preCancelOrderCheck(OrderDO order){};

    /**
     * 跳转地址
     * @return
     */
    default String payBackJumpUrl(){return null;};

    /**
     * 支付成功
     * @param order
     */
    default void paySuccess(OrderDO order){};


    /**
     * 支付失败
     * @param order
     */
    default void payFail(OrderDO order){};

    /**
     * 取消
     * @param order
     */
    default void cancel(OrderDO order){};

    default void handlerRefund(OrderDO order, OrderRefundDO orderRefundDO, RefundResDTO res){};
}
