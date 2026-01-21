package com.qdbank.mall.order.action.real;

import com.qdbank.mall.annotation.StateError;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.payment.CloseTypeEnum;
import com.qdbank.mall.constant.payment.event.RealOrderHandlerEvent;
import com.qdbank.mall.constant.payment.orderstatus.RealOrderStatus;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderStatusDO;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.order.business.handler.RealPaymentHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class PayFailAction implements Action<RealOrderStatus, RealOrderHandlerEvent> {

    @Autowired
    PaymentService paymentService;

    @Autowired
    RealPaymentHandler realPaymentHandler;


        @Override
        @StateError
        @Transactional(rollbackFor = Exception.class)
        public void execute(StateContext<RealOrderStatus, RealOrderHandlerEvent> context) {
            log.info("支付失败退款");
            //关闭
            OrderDO order =  context.getMessage().getHeaders().get(Constant.ORDER_STR,OrderDO.class);
            OrderStatusDO orderStatusDO = new OrderStatusDO();
            orderStatusDO.setBeforeApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
            orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
            orderStatusDO.setBeforeStatus(RealOrderStatus.Status.PREPARE_PAY.status);

            orderStatusDO.setAfterApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
            orderStatusDO.setAfterCloseType(CloseTypeEnum.PAY_FAIL.closeType);
            orderStatusDO.setAfterStatus(RealOrderStatus.Status.HAS_CLOSE.status);

            paymentService.cancelOrder(order,orderStatusDO,realPaymentHandler);
        }



}
