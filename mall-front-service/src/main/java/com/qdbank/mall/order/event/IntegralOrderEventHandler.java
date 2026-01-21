package com.qdbank.mall.order.event;

import com.qdbank.mall.annotation.StateError;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.payment.*;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.payment.event.IntegralOrderEvent;
import com.qdbank.mall.constant.payment.event.RealOrderHandlerEvent;
import com.qdbank.mall.constant.payment.lifestatus.IntegralOrderLifeStatus;
import com.qdbank.mall.constant.payment.orderstatus.IntegralOrderStatus;
import com.qdbank.mall.coupon.CouponService;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderStatusDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.order.business.handler.IntegralPaymentHandler;
import com.qdbank.mall.request.order.IntegralOrderReqDTO;
import com.qdbank.mall.request.third.payment.NoticeReqDTO;
import com.qdbank.mall.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.EventHeaders;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@WithStateMachine(id = Constant.INTEGRAL_ORDER_MACHINE)
@Slf4j
public class IntegralOrderEventHandler {

    @Autowired
    CouponService couponService;

    @Autowired
    OrderService orderService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    IntegralPaymentHandler integralPaymentHandler;

    @OnTransition(target = "INIT")
    @StateError
    public void init(Message<RealOrderHandlerEvent> message) {
        log.info("订单初始化");
    }

    @OnTransition(source = "INIT",target = "PREPARE_PAY")
    @StateError
    public void create(Message<RealOrderHandlerEvent> message) {
        log.info("创建订单");
        //获取订单
        IntegralOrderReqDTO realOrder  = (IntegralOrderReqDTO) message.getHeaders().get("integralOrder");
        OrderDO order = (OrderDO) message.getHeaders().get(Constant.ORDER_STR);
        paymentService.createOrder(order,realOrder, integralPaymentHandler);
        log.info("订单初始化:[{}]", JsonUtil.toJSONString(order));
    }

    @OnTransition(source ="PREPARE_PAY", target = "PAY_WAIT")
    @StateError
    public void pay(Message<RealOrderHandlerEvent> message) {
        log.info("支付");
    }

    /**
     * 用户取消
     * @param message
     */
    @OnTransition(source ="PREPARE_PAY", target = "CANCEL")
    @StateError
    public void cancel(Message<RealOrderHandlerEvent> message) {
        OrderDO order =  message.getHeaders().get(Constant.ORDER_STR,OrderDO.class);
        //关闭
        //0 用户取消关闭 1 超时自动关闭 2 支付失败关闭 3 退款成功关闭
        // 取消订单相应逻辑
        OrderStatusDO orderStatusDO = new OrderStatusDO();
        orderStatusDO.setBeforeApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setBeforeStatus(IntegralOrderStatus.Status.PREPARE_PAY.status);

        orderStatusDO.setAfterApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setAfterCloseType(CloseTypeEnum.USER_CANCEL.closeType);
        orderStatusDO.setAfterStatus(IntegralOrderStatus.Status.HAS_CLOSE.status);

        paymentService.cancelOrder(order,orderStatusDO,integralPaymentHandler);
        orderService.createProcessStatus(order.getOrderSn(), IntegralOrderLifeStatus.CLOSE.status,IntegralOrderLifeStatus.CLOSE.status);

    }


    /**
     * 用户取消
     * @param message
     */
    @OnTransition(source ="PREPARE_PAY", target = "TIMEOUT_CANCEL")
    @StateError
    public void timeOutCancel(Message<RealOrderHandlerEvent> message) {
        OrderDO order =  message.getHeaders().get(Constant.ORDER_STR,OrderDO.class);
        //关闭
        //0 用户取消关闭 1 超时自动关闭 2 支付失败关闭 3 退款成功关闭
        // 取消订单相应逻辑
        OrderStatusDO orderStatusDO = new OrderStatusDO();
        orderStatusDO.setBeforeApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setBeforeStatus(IntegralOrderStatus.Status.PREPARE_PAY.status);

        orderStatusDO.setAfterApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setAfterCloseType(CloseTypeEnum.TIMEOUT_CANCEL.closeType);
        orderStatusDO.setAfterStatus(IntegralOrderStatus.Status.HAS_CLOSE.status);
        paymentService.cancelOrder(order,orderStatusDO,integralPaymentHandler);
        orderService.createProcessStatus(order.getOrderSn(), IntegralOrderLifeStatus.CLOSE.status,IntegralOrderLifeStatus.CLOSE.status);

    }


    /**
     * 用户取消
     * @param message
     */
    @OnTransition(source ={"TIMEOUT_CANCEL","CANCEL"}, target = "REFUND")
    @StateError
    public void refund(Message<RealOrderHandlerEvent> message) {
        log.info("支付成功后，因订单已取消退款");
        //关闭
        OrderDO order =  message.getHeaders().get(Constant.ORDER_STR,OrderDO.class);
        //退款操作
        log.info("执行退款操作...[{}]",order.getOrderSn());

        NoticeReqDTO noticeReq = message.getHeaders().get("noticeReq",NoticeReqDTO.class);
        /**
         * L 失败 、S成功
         */
        String status= noticeReq.getStatus();
        //状态
        if("S".equals(status)){
            paymentService.refundOrderWithException(order,integralPaymentHandler);
        }


    }


    /*@OnTransition(source ="PAY_WAIT",target = "PAY_NOTIC_CHOICE")
    public void notic(@EventHeaders Map<String, Object> headers, Message<IntegralOrderEvent> message) {
        OrderDO order = (OrderDO) headers.get(Constant.ORDER_STR);
        log.info("订单通知:[{}]", JsonUtil.toJSONString(order));
    }*/



    @OnTransition(source = "PREPARE_USE", target = "EXPIRE")
    @StateError
    public void expire(@EventHeaders Map<String, Object> headers, Message<IntegralOrderEvent> message) {

        OrderDO order = (OrderDO) headers.get(Constant.ORDER_STR);
        log.info("积分兑换，过期操作[{}]",order.getOrderSn());

        //优惠券状态更新失效
        log.info("积分兑换，过期操作[{}]",order.getOrderSn());
        //优惠券状态更新失效
        boolean flag = couponService.activeUserCoupon(order,0L,2L);

        if(!flag){
            throw new ApiException(ResultCode.USER_COUPON_STATUS_ERROR);
        }

        OrderStatusDO orderStatusDO = new OrderStatusDO();
        orderStatusDO.setBeforeApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setBeforeStatus(IntegralOrderStatus.Status.PREPARE_USE.status);

        orderStatusDO.setAfterApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setAfterCloseType(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setAfterStatus(IntegralOrderStatus.Status.EXPIRE.status);
        orderService.updateUserOrderStatus(null,order.getCustNo()+"",order,orderStatusDO);
        orderService.createProcessStatus(order.getOrderSn(), IntegralOrderLifeStatus.EXPIRE.status,IntegralOrderLifeStatus.EXPIRE.status);

    }

    @OnTransition(source = "PREPARE_USE", target = "HAS_FINISH")
    @StateError
    public void use(@EventHeaders Map<String, Object> headers, Message<IntegralOrderEvent> message) {
        OrderDO order = (OrderDO) headers.get(Constant.ORDER_STR);
        log.info("积分兑换，已使用操作[{}]",order.getOrderSn());

        OrderStatusDO orderStatusDO = new OrderStatusDO();
        orderStatusDO.setBeforeApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setBeforeStatus(IntegralOrderStatus.Status.PREPARE_USE.status);

        orderStatusDO.setAfterApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setAfterCloseType(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setAfterStatus(IntegralOrderStatus.Status.HAS_FINISH.status);
        orderService.updateUserOrderStatus(null,order.getCustNo()+"",order,orderStatusDO);
        orderService.createProcessStatus(order.getOrderSn(), IntegralOrderLifeStatus.HAS_USE.status,IntegralOrderLifeStatus.HAS_USE.status);

    }

    @OnTransition(source = "HAS_FINISH", target = "PREPARE_USE")
    @StateError
    public void reset(@EventHeaders Map<String, Object> headers, Message<IntegralOrderEvent> message) {
        OrderDO order = (OrderDO) headers.get(Constant.ORDER_STR);
        Long afterStatus = (Long) headers.get("afterStatus");
        log.info("积分兑换，已使用操作[{}]",order.getOrderSn());

        OrderStatusDO orderStatusDO = new OrderStatusDO();
        orderStatusDO.setBeforeApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setBeforeStatus(IntegralOrderStatus.Status.HAS_FINISH.status);

        //判断是否过期

        orderStatusDO.setAfterApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setAfterCloseType(CloseTypeEnum.INIT.closeType);
        if(0L == afterStatus){
          orderStatusDO.setAfterStatus(IntegralOrderStatus.Status.PREPARE_USE.status);
        }else if(2L==afterStatus){
            orderStatusDO.setAfterStatus(IntegralOrderStatus.Status.EXPIRE.status);
        }else{
            throw new ApiException(ResultCode.USER_COUPON_STATUS_ERROR);
        }

        orderService.updateUserOrderStatus(null,order.getCustNo()+"",order,orderStatusDO);

        orderService.rmProcessStatus(order.getOrderSn(), IntegralOrderLifeStatus.HAS_USE.status);

    }





}
