package com.qdbank.mall.order.action.mobile;

import com.qdbank.mall.annotation.StateError;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.payment.*;
import com.qdbank.mall.constant.payment.event.MobileReChargePayOrderEvent;
import com.qdbank.mall.constant.payment.lifestatus.MobileRechargeOrderLifeStatus;
import com.qdbank.mall.constant.payment.orderstatus.MobileReChargePayOrderStatus;
import com.qdbank.mall.exception.MobileException;
import com.qdbank.mall.mobile.MobileService;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderStatusDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.business.handler.MobileRechargePaymenHandler;
import com.qdbank.mall.order.mq.MobileRechargeRefundHandler;
import com.qdbank.mall.request.third.mobile.MobileNoticeReqDTO;
import com.qdbank.mall.request.third.mobile.ThirdNoticeReqDTO;
import com.qdbank.mall.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
public class MobileRechargeAction implements Action<MobileReChargePayOrderStatus, MobileReChargePayOrderEvent> {

    @Autowired
    OrderService orderService;

    @Autowired
    MobileRechargeRefundHandler mobileRechargeRefundHandler;

  /*  @Autowired
    MobileRechargePaymenHandler mobileRechargePaymenHandler;

    @Autowired
    MobileService mobileService;*/




        @Override
        @StateError
        @Transactional(rollbackFor = Exception.class)
        public void execute(StateContext<MobileReChargePayOrderStatus,MobileReChargePayOrderEvent> context) {
            OrderDO order = context.getMessage().getHeaders().get(Constant.ORDER_STR, OrderDO.class);
            //step 1 订单处理
            OrderStatusDO orderStatusDO = new OrderStatusDO();
            orderStatusDO.setBeforeApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
            orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
            orderStatusDO.setBeforeStatus(MobileReChargePayOrderStatus.Status.PREPARE_PAY.status);

            orderStatusDO.setAfterApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
            orderStatusDO.setAfterCloseType(CloseTypeEnum.INIT.closeType);
            orderStatusDO.setAfterStatus(MobileReChargePayOrderStatus.Status.CHARGING.status);

      //      OrderDO updateOrder = new OrderDO();

      //      updateOrder.setPaymentTime(new Date());
            //话费流水号
            orderService.updateUserOrderStatus(null, order.getCustNo() + "", order, orderStatusDO);
            //更新状态流程
            orderService.createProcessStatus(order.getOrderSn(), MobileRechargeOrderLifeStatus.PAY_SUCCESS.status, MobileRechargeOrderLifeStatus.PAY_SUCCESS.status);

            //step 2 话费充值
            boolean flag = true;
            String errorMsg = null;
            try {
                String wxOrderId = orderService.getHandler(order).charge(order);
                if (!StringUtils.hasText(wxOrderId)) {
                    flag = false;
                }
                log.info("用户号码[{}]-[{}]",order.getCustNo(),order.getRechargeMobile());
    //            mobileService.updateCustMobile(order.getCustNo()+"",order.getRechargeMobile(),order.getProductSkuId());
            } catch (MobileException e1) {
                log.error("网信充值失败：[{}]",JsonUtil.toJSONString(order),e1);
                flag = false;
                errorMsg=e1.getMessage();
            }catch (Exception e){
                //异步通知的操作，需要后台自行补偿确认异常；
                log.error("充值失败:[{}]",JsonUtil.toJSONString(order),e);
                return;
            }

            //充值失败-推MQ退款
            if(!flag){
                ThirdNoticeReqDTO req = new ThirdNoticeReqDTO();
                req.setStatus(MobileRechargeStatus.CHARGE_FAIL.code);
                req.setKhOrderId(order.getOrderSn());
                req.setErrMsg(errorMsg);
                mobileRechargeRefundHandler.refund(req);
            }

            //更新状态
            log.info("网信充值，创建操作[{}]",order.getOrderSn());
        }



}
