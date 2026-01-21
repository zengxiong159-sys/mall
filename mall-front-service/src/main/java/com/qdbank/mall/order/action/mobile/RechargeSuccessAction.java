package com.qdbank.mall.order.action.mobile;

import com.qdbank.mall.annotation.StateError;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.payment.*;
import com.qdbank.mall.constant.payment.event.MobileReChargePayOrderEvent;
import com.qdbank.mall.constant.payment.lifestatus.MobileRechargeOrderLifeStatus;
import com.qdbank.mall.constant.payment.orderstatus.MobileReChargePayOrderStatus;
import com.qdbank.mall.mobile.MobileService;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderStatusDO;
import com.qdbank.mall.model.rechargeMobileFlow.RechargeMobileFlowDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.business.handler.MobileRechargePaymenHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class RechargeSuccessAction implements Action<MobileReChargePayOrderStatus, MobileReChargePayOrderEvent> {

    @Autowired
    OrderService orderService;

    @Autowired
    MobileService mobileService;



        @Override
        @StateError
        @Transactional(rollbackFor = Exception.class)
        public void execute(StateContext<MobileReChargePayOrderStatus,MobileReChargePayOrderEvent> context) {
            OrderDO order =  context.getMessage().getHeaders().get(Constant.ORDER_STR,OrderDO.class);

            //step 订单处理
            OrderStatusDO orderStatusDO = new OrderStatusDO();
            orderStatusDO.setBeforeApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
            orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
            orderStatusDO.setBeforeStatus(MobileReChargePayOrderStatus.Status.CHARGING.status);

            orderStatusDO.setAfterApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
            orderStatusDO.setAfterCloseType(CloseTypeEnum.INIT.closeType);
            orderStatusDO.setAfterStatus(MobileReChargePayOrderStatus.Status.HAS_FINISH.status);

            OrderDO updateOrder = new OrderDO();
            boolean flag = orderService.updateUserOrderStatus(updateOrder,order.getCustNo()+"",order,orderStatusDO);
            //更新状态流程
            orderService.createProcessStatus(order.getOrderSn(), MobileRechargeOrderLifeStatus.HAS_FINISH.status,MobileRechargeOrderLifeStatus.HAS_FINISH.status);

            //流水表
            RechargeMobileFlowDO flow = new RechargeMobileFlowDO();
            flow.setStatus(MobileRechargeStatus.CHARGE_SUCCESS.code);
            mobileService.updateMobileFlow(flow,order.getOrderSn());

            //更新状态
            log.info("话费充值，创建操作[{}]",order.getOrderSn());
        }



}
