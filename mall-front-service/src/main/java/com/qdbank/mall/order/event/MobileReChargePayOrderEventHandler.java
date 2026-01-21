package com.qdbank.mall.order.event;

import com.alibaba.fastjson.JSON;
import com.qdbank.mall.annotation.StateError;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.constant.payment.*;
import com.qdbank.mall.constant.payment.event.MobileReChargePayOrderEvent;
import com.qdbank.mall.constant.payment.lifestatus.MobileRechargeOrderLifeStatus;
import com.qdbank.mall.constant.payment.orderstatus.MobileReChargePayOrderStatus;
import com.qdbank.mall.coupon.CouponService;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mobile.MobileService;
import com.qdbank.mall.model.mobileSku.MobileSkuDO;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderStatusDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.order.business.handler.MobileRechargePaymenHandler;
import com.qdbank.mall.order.business.handler.VirtualPaymenHandler;
import com.qdbank.mall.request.order.MobileRechargePaymenOrderReqDTO;
import com.qdbank.mall.request.order.OilRechargePaymenOrderReqDTO;
import com.qdbank.mall.request.order.VirtualPaymenOrderReqDTO;
import com.qdbank.mall.request.third.payment.NoticeReqDTO;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;


@Component
@WithStateMachine(id = Constant.MOBILE_RECHARGEPAY_ORDER_MACHINE)
@Slf4j
public class MobileReChargePayOrderEventHandler {

    @Autowired
    CouponService couponService;

    @Autowired
    OrderService orderService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    MobileService mobileService;


    @OnTransition(target = "INIT")
    @StateError
    public void init(Message<MobileReChargePayOrderEvent> message) {
        log.info("订单初始化");
    }

    @OnTransition(source = "INIT",target = "PREPARE_PAY")
    @StateError
    public void create(Message<MobileReChargePayOrderEvent> message) {
        log.info("创建订单");
        //获取订单
        VirtualPaymenOrderReqDTO req  = (VirtualPaymenOrderReqDTO) message.getHeaders().get("mobileOrder");
        OrderDO order = (OrderDO) message.getHeaders().get(Constant.ORDER_STR);

        ProductEnum productEnum=null;
        if(req instanceof MobileRechargePaymenOrderReqDTO){
            log.info("话费充值处理[{}]", JSON.toJSONString(req));
            productEnum = ProductEnum.MOBILE_RECHARGE;
            MobileRechargePaymenOrderReqDTO orderReqDTO = (MobileRechargePaymenOrderReqDTO) req;
            //手机规格
            Long mobileSkuId=orderReqDTO.getMobileSkuId();
            //运营商code
            String supplierType=orderReqDTO.getSupplierType();

            //获取信息
            MobileSkuDO mobileSku=mobileService.qryMobileSkuByMobileSkuId(supplierType,mobileSkuId);
            if (mobileSku==null) {
                throw new ApiException(ResultCode.PRODUCT_NOT_DEPLOY);
            }

            //商品类型初始化，优惠券校验使用
            order.setProductId(mobileSku.getProductId());
            order.setProductSkuId(mobileSku.getMobileSkuId());

        }else if(req instanceof OilRechargePaymenOrderReqDTO){
            log.info("油卡充值处理[{}]", JSON.toJSONString(req));
            //商品类型初始化，优惠券校验使用
            order.setProductId(req.getProductId());
            order.setProductSkuId(req.getProductSkuId());
            productEnum = ProductEnum.OIL_CARD;
        }

        paymentService.createOrder(order,req, SpringContextUtils.getBean(productEnum.handlerName,VirtualPaymenHandler.class));
        log.info("订单初始化:[{}]", JsonUtil.toJSONString(order));
    }

    @OnTransition(source ="PREPARE_PAY", target = "PAY_WAIT")
    @StateError
    public void pay(Message<MobileReChargePayOrderEvent> message) {
        log.info("支付");
    }

    /**
     * 用户取消
     * @param message
     */
    @OnTransition(source ="PREPARE_PAY", target = "CANCEL")
    @StateError
    public void cancel(Message<MobileReChargePayOrderEvent> message) {
        OrderDO order =  message.getHeaders().get(Constant.ORDER_STR,OrderDO.class);
        //关闭
        //0 用户取消关闭 1 超时自动关闭 2 支付失败关闭 3 退款成功关闭
        // 取消订单相应逻辑
        OrderStatusDO orderStatusDO = new OrderStatusDO();
        orderStatusDO.setBeforeApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setBeforeStatus(MobileReChargePayOrderStatus.Status.PREPARE_PAY.status);

        orderStatusDO.setAfterApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setAfterCloseType(CloseTypeEnum.USER_CANCEL.closeType);
        orderStatusDO.setAfterStatus(MobileReChargePayOrderStatus.Status.HAS_CLOSE.status);

        paymentService.cancelOrder(order,orderStatusDO,orderService.getHandler(order));
        orderService.createProcessStatus(order.getOrderSn(), MobileRechargeOrderLifeStatus.CLOSE.status,MobileRechargeOrderLifeStatus.CLOSE.status);

    }


    /**
     * 用户取消
     * @param message
     */
    @OnTransition(source ="PREPARE_PAY", target = "TIMEOUT_CANCEL")
    @StateError
    public void timeOutCancel(Message<MobileReChargePayOrderEvent> message) {
        OrderDO order =  message.getHeaders().get(Constant.ORDER_STR,OrderDO.class);
        //关闭
        //0 用户取消关闭 1 超时自动关闭 2 支付失败关闭 3 退款成功关闭
        // 取消订单相应逻辑
        OrderStatusDO orderStatusDO = new OrderStatusDO();
        orderStatusDO.setBeforeApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setBeforeStatus(MobileReChargePayOrderStatus.Status.PREPARE_PAY.status);

        orderStatusDO.setAfterApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setAfterCloseType(CloseTypeEnum.TIMEOUT_CANCEL.closeType);
        orderStatusDO.setAfterStatus(MobileReChargePayOrderStatus.Status.HAS_CLOSE.status);
        paymentService.cancelOrder(order,orderStatusDO,orderService.getHandler(order));
        orderService.createProcessStatus(order.getOrderSn(), MobileRechargeOrderLifeStatus.CLOSE.status,MobileRechargeOrderLifeStatus.CLOSE.status);

    }


    /**
     * 超时取消退款
     * @param message
     */
    @OnTransition(source ={"TIMEOUT_CANCEL","CANCEL"}, target = "REFUND")
    @StateError
    public void refund(Message<MobileReChargePayOrderEvent> message) {
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
            paymentService.refundOrderWithException(order,orderService.getHandler(order));
        }

    }











}
