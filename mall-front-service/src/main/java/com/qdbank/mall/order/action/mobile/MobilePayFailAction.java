package com.qdbank.mall.order.action.mobile;

import com.qdbank.mall.annotation.StateError;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.payment.*;
import com.qdbank.mall.constant.payment.event.MobileReChargePayOrderEvent;
import com.qdbank.mall.constant.payment.lifestatus.MobileRechargeOrderLifeStatus;
import com.qdbank.mall.constant.payment.orderstatus.MobileReChargePayOrderStatus;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderStatusDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.order.business.handler.MobileRechargePaymenHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.transaction.annotation.Transactional;

@Slf4j

public class MobilePayFailAction implements Action<MobileReChargePayOrderStatus, MobileReChargePayOrderEvent> {



    @Autowired
    PaymentService paymentService;

    @Autowired
    OrderService orderService;


    @Override
    @StateError
    @Transactional(rollbackFor = Exception.class)
    public void execute(StateContext<MobileReChargePayOrderStatus,MobileReChargePayOrderEvent> context) {
        log.info("支付失败退款");
        //关闭
        OrderDO order =  context.getMessage().getHeaders().get(Constant.ORDER_STR,OrderDO.class);
        OrderStatusDO orderStatusDO = new OrderStatusDO();
        orderStatusDO.setBeforeApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setBeforeStatus(MobileReChargePayOrderStatus.Status.PREPARE_PAY.status);

        orderStatusDO.setAfterApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setAfterCloseType(CloseTypeEnum.PAY_FAIL.closeType);
        orderStatusDO.setAfterStatus(MobileReChargePayOrderStatus.Status.HAS_CLOSE.status);

        paymentService.cancelOrder(order,orderStatusDO,orderService.getHandler(order));
        orderService.createProcessStatus(order.getOrderSn(), MobileRechargeOrderLifeStatus.CLOSE.status,MobileRechargeOrderLifeStatus.CLOSE.status);
    }



}
