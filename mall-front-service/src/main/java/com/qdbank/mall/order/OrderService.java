package com.qdbank.mall.order;

import com.qdbank.mall.constant.payment.metrics.MetricsEnum;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.constant.payment.metrics.MetricsHolder;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderStatusDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.model.orderrefund.OrderRefundWithOrderDetailDO;
import com.qdbank.mall.model.rechargestatusdetail.RechargeStatusDO;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.order.business.handler.VirtualPaymenHandler;
import com.qdbank.mall.request.third.payment.NoticeReqDTO;
import com.qdbank.mall.response.order.OrderResDTO;
import com.qdbank.mall.response.refund.OrderRefundResDTO;
import com.qdbank.mall.response.third.payment.RefundResDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderService {


    /**
     * 创建订单
     * @param orderDO
     * @return
     */
    Integer createOrder(OrderDO orderDO);


    /**
     * 通过订单号查询订单信息
     * @param orderSn 订单号
     * @return
     */
    OrderDO qryNotNoticOrderByOrderSn(String orderSn);

    OrderDO qryOrderBySn(String orderSn);

    boolean updateUserOrderStatus(OrderDO orderDO,String custNo, OrderDO oldOrder, OrderStatusDO orderStatus);

    boolean updatePaymentTime(String custNo, String orderSn, Date paymentTime);

    int noticOrderStatus(String custNo, String orderSn, Long noticFlag, NoticeReqDTO reqDTO);

    List<OrderResDTO> qryOrders(String custNo,String status ,ProductEnum... productEnum);

    List<OrderRefundResDTO> qryRefundOrderList(String custNo);

    boolean updateRefundStatus(OrderRefundDO orderRefundDO, String custNo, String orderSn, Long beforeStatus, Long afterStatus);

    void createApplyRefund(OrderRefundDO orderRefund);

    OrderRefundWithOrderDetailDO qryApplyRefund(String  refundSeriNo);

    void createProcessStatus(String orderSn, Long detailLevel, Long status);

    void rmProcessStatus(String orderSn, Long status);

    List<RechargeStatusDO> qryProcessStatus(String orderSn);

    Long countOrderByProductTypeAndStatus(String custNo,Long status, ProductEnum... productEnums);

    OrderRefundResDTO realRefundDetail(String refundSeriNo);

    void sendPost(OrderDO order, MetricsHolder holder, String msg);

    RefundResDTO errorRefund(String id);

    void orderAmt(OrderDO order, Long productCount, BigDecimal productPrice, BigDecimal productCash, Long productIntegration, BigDecimal couponAmount, BigDecimal freightAmount);

    void initOrderCash(OrderDO order, SkustockDO skustock);

    VirtualPaymenHandler getHandler(OrderDO order);
}
