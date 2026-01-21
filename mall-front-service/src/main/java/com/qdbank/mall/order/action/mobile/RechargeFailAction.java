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
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.model.rechargeMobileFlow.RechargeMobileFlowDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.order.business.handler.MobileRechargePaymenHandler;
import com.qdbank.mall.order.mapper.RefundMapper;
import com.qdbank.mall.response.third.payment.RefundResDTO;
import com.qdbank.mall.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class RechargeFailAction implements Action<MobileReChargePayOrderStatus, MobileReChargePayOrderEvent> {
        @Autowired
        PaymentService paymentService;

        @Autowired
        OrderService orderService;

        @Autowired
        MobileService mobileService;

        @Autowired
    RefundMapper refundMapper;

        @Override
        @StateError
        @Transactional(rollbackFor = Exception.class)
        public void execute(StateContext<MobileReChargePayOrderStatus,MobileReChargePayOrderEvent> context) {
            log.info("充值失败");

            //关闭
            OrderDO order =  context.getMessage().getHeaders().get(Constant.ORDER_STR,OrderDO.class);

            //更新退款状态流程
            orderService.createProcessStatus(order.getOrderSn(), MobileRechargeOrderLifeStatus.RECHARGE_FAIL.status,MobileRechargeOrderLifeStatus.RECHARGE_FAIL.status);



            //step 订单处理
            OrderStatusDO orderStatusDO = new OrderStatusDO();
            orderStatusDO.setBeforeApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
            orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
            orderStatusDO.setBeforeStatus(MobileReChargePayOrderStatus.Status.CHARGING.status);

            orderStatusDO.setAfterApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
            orderStatusDO.setAfterCloseType(CloseTypeEnum.CHARGIND_FAIL.closeType);
            orderStatusDO.setAfterStatus(MobileReChargePayOrderStatus.Status.HAS_CLOSE.status);


    //        boolean flag = orderService.updateUserOrderStatus(updateOrder,order.getCustNo()+"",order.getOrderSn(),orderStatusDO);
            // 退款失败兜底处理-- 退款操作--报错情况：通过二次触发
            paymentService.cancelOrder(order,orderStatusDO,orderService.getHandler(order));

            //流水表
            RechargeMobileFlowDO flow = new RechargeMobileFlowDO();
            flow.setStatus(MobileRechargeStatus.CHARGE_FAIL.code);
            flow.setRechargeRemark(order.getRechargeRemark());
            mobileService.updateMobileFlow(flow,order.getOrderSn());




            OrderRefundDO orderRefundDO=paymentService.initOrderRefund(order,"充值失败，自动退款");
            orderRefundDO.setRefundCash(order.getOrderCash());
            // 退款折算价
            orderRefundDO.setRefundAmount(order.getPayAmount());
            //积分--不存在
            orderRefundDO.setRefundIntegration(order.getOrderIntegration());
            //发起退款操作
            //更新退款状态流程
            orderService.createProcessStatus(order.getOrderSn(), MobileRechargeOrderLifeStatus.CREATE_REFUND.status,MobileRechargeOrderLifeStatus.CREATE_REFUND.status);
            RefundResDTO res  =paymentService.refundOrder(orderRefundDO,order,orderService.getHandler(order));

            if(res!=null){
                orderService.createProcessStatus(order.getOrderSn(), MobileRechargeOrderLifeStatus.REFUND_SUCCESS.status,MobileRechargeOrderLifeStatus.REFUND_SUCCESS.status);
            }else{
                //退款失败
                orderService.createProcessStatus(order.getOrderSn(), MobileRechargeOrderLifeStatus.REFUND_FAIL.status,MobileRechargeOrderLifeStatus.REFUND_FAIL.status);
            }
            log.info("新增记录[{}]", JsonUtil.toJSONString(orderRefundDO));
            orderService.createApplyRefund(orderRefundDO);
        }





}
