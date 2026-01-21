package com.qdbank.mall.order;

import com.qdbank.mall.constant.payment.event.OrderEventAware;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderStatusDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.order.business.CommonPaymentAware;
import com.qdbank.mall.request.order.CommonOrderReqDTO;
import com.qdbank.mall.request.third.payment.NoticeReqDTO;
import com.qdbank.mall.response.third.payment.QryPayResDTO;
import com.qdbank.mall.response.third.payment.RefundResDTO;
import org.springframework.messaging.Message;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

public interface PaymentService {


    @Transactional(rollbackFor = Exception.class)
    OrderDO createOrder(OrderDO order,CommonOrderReqDTO req, CommonPaymentAware aware);


    void cancelOrder(OrderDO order, OrderStatusDO orderStatusDO, CommonPaymentAware aware);

    RefundResDTO refundOrderWithException(OrderDO order, CommonPaymentAware aware);

    RefundResDTO refundOrder(OrderRefundDO orderRefundDO,OrderDO order, CommonPaymentAware aware);

    QryPayResDTO qryOrderInfo(OrderDO order);

    void handlerNotic(OrderDO order, NoticeReqDTO qryPayRes);

    Message handlerOrder(Object orderObj, OrderEventAware _event, Map params);

    Message  handlerBsn(Long custNo,String orderSn,OrderDO order, OrderEventAware _event, Map params);

    OrderRefundDO initOrderRefund(OrderDO orderDO, String note);
}
