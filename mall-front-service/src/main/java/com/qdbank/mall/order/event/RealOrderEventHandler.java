
package com.qdbank.mall.order.event;

import com.qdbank.mall.annotation.StateError;
import com.qdbank.mall.constant.*;
import com.qdbank.mall.constant.payment.*;
import com.qdbank.mall.constant.payment.event.RealOrderHandlerEvent;
import com.qdbank.mall.constant.payment.orderstatus.RealOrderStatus;
import com.qdbank.mall.constent.payment.RefundStatausEnum;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderStatusDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.order.business.handler.RealPaymentHandler;
import com.qdbank.mall.request.order.RealOrderReqDTO;
import com.qdbank.mall.request.third.payment.NoticeReqDTO;
import com.qdbank.mall.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Component
@WithStateMachine(id = Constant.REAL_ORDER_MACHINE)
@Slf4j
public class RealOrderEventHandler {

    @Autowired
    RealPaymentHandler realPaymentHandler;

    @Autowired
    PaymentService paymentService;

    @Autowired
    OrderService orderService;

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
        RealOrderReqDTO realOrder  = (RealOrderReqDTO) message.getHeaders().get("realOrder");
        OrderDO order =(OrderDO) message.getHeaders().get(Constant.ORDER_STR);
        //商品类型初始化，优惠券校验使用
        order.setProductId(realOrder.getProductId());
        order.setProductSkuId(realOrder.getProductSkuId());
        order.setDiscountAmount(realOrder.getDiscountAmount());
        paymentService.createOrder(order,realOrder, realPaymentHandler);
        log.info("订单初始化:[{}]", JsonUtil.toJSONString(order));
    }

    @OnTransition(source ="PREPARE_PAY", target = "PAY_WAIT")
    @StateError
    public void pay(Message<RealOrderHandlerEvent> message) {
        //TODO ???
        log.info("支付");
    }

    @OnTransition(source ="PREPARE_PAY",target = "PAY_NOTIC_CHOICE")
    @StateError
    public void notic(Message<RealOrderHandlerEvent> message) {
        log.info("订单通知");
        OrderDO order =  message.getHeaders().get(Constant.ORDER_STR,OrderDO.class);
        log.info("订单通知:[{}]", JsonUtil.toJSONString(order));
    }


    /**
     * 发货
     * @param message
     */
    @OnTransition(source ="STAY_DELIVER_GOODS", target = "DELIVER_GOODS")
    @StateError
    public void deliverGoods(Message<RealOrderHandlerEvent> message) {
        OrderDO order =  message.getHeaders().get(Constant.ORDER_STR,OrderDO.class);
        // 更新已发货状态
        OrderStatusDO orderStatusDO = new OrderStatusDO();
        orderStatusDO.setBeforeApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setBeforeStatus(RealOrderStatus.Status.STAY_DELIVER_GOODS.status);

        orderStatusDO.setAfterApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setAfterCloseType(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setAfterStatus(RealOrderStatus.Status.DELIVER_GOODS.status);
        OrderDO updateOrder = new OrderDO();
        updateOrder.setDeliveryTime(new Date());
        orderService.updateUserOrderStatus(updateOrder,order.getCustNo()+"",order,orderStatusDO);
    }

    /**
     * 用户确认
     * @param message
     */
    @OnTransition(source ="DELIVER_GOODS", target = "HAS_FINISH")
    @StateError
    public void userCheck(Message<RealOrderHandlerEvent> message) {
        OrderDO order =  message.getHeaders().get(Constant.ORDER_STR,OrderDO.class);
        // 更新已发货状态
        OrderStatusDO orderStatusDO = new OrderStatusDO();
        orderStatusDO.setBeforeApproveStatus(order.getRefundStatus());
        orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setBeforeStatus(RealOrderStatus.Status.DELIVER_GOODS.status);

        orderStatusDO.setAfterApproveStatus(order.getRefundStatus());
        orderStatusDO.setAfterCloseType(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setAfterStatus(RealOrderStatus.Status.HAS_FINISH.status);
        OrderDO updateOrder = new OrderDO();
        updateOrder.setReceiveTime(new Date());
        updateOrder.setConfirmStatus(1L);
        orderService.updateUserOrderStatus(updateOrder,order.getCustNo()+"",order,orderStatusDO);
    }

    /**
     * 已发货、待发货--退款
     * @param message
     */
    @OnTransition(source ={"HAS_FINISH","STAY_DELIVER_GOODS"}, target = "REVIEWED")
    @StateError
    public void applyRefund1(Message<RealOrderHandlerEvent> message) {
        OrderRefundDO orderRefundDO =  message.getHeaders().get("applyOrderRefundReqDTO",OrderRefundDO.class);
       OrderDO orderDO = message.getHeaders().get(Constant.ORDER_STR,OrderDO.class);
        realPaymentHandler.commitReviewed(orderRefundDO,orderDO);
    }


    /**
     * 审核操作
     * @param message
     */
    @OnTransition(source ="REVIEWED", target = "REVIEWED_CHOICE")
    @StateError
    public void approval(Message<RealOrderHandlerEvent> message) {
        log.info("审核中转...");
    }

    /**
     * 审核撤销
     * @param message
     */
    @Transactional(rollbackFor = Exception.class)
    @OnTransition(source ="REVIEWED", target = "REVOCATION")
    @StateError
    public void cancelRefund(Message<RealOrderHandlerEvent> message) {
        OrderDO order =  message.getHeaders().get(Constant.ORDER_STR,OrderDO.class);
        // 撤销 待支付--> 取消
        OrderStatusDO orderStatusDO = new OrderStatusDO();
        orderStatusDO.setBeforeApproveStatus(RefundStatausEnum.PREPARE_APPROVE.refundStatus);
        orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setBeforeStatus(order.getStatus());

        //用户取消
        orderStatusDO.setAfterApproveStatus(RefundStatausEnum.APPROVE_CANCEL.refundStatus);
        orderStatusDO.setAfterCloseType(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setAfterStatus(order.getStatus());

        orderService.updateUserOrderStatus(null,order.getCustNo()+"",order,orderStatusDO);

        //更新申请状态--根据状态机制，只有一条申请撤销记录

        orderService.updateRefundStatus(null,order.getCustNo()+"",order.getOrderSn(),RefundStatausEnum.PREPARE_APPROVE.refundStatus,RefundStatausEnum.APPROVE_CANCEL.refundStatus);

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
        orderStatusDO.setBeforeStatus(RealOrderStatus.Status.PREPARE_PAY.status);

        orderStatusDO.setAfterApproveStatus(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setAfterCloseType(CloseTypeEnum.USER_CANCEL.closeType);
        orderStatusDO.setAfterStatus(RealOrderStatus.Status.HAS_CLOSE.status);

        paymentService.cancelOrder(order,orderStatusDO,realPaymentHandler);

    }

    /**
     * 取消后的成功支付通知
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
            paymentService.refundOrderWithException(order,realPaymentHandler);
        }
    }


    /**
     * 超时取消
     * @param message
     */
    @Transactional
    @StateError
    @OnTransition(source ="PREPARE_PAY", target = "TIMEOUT_CANCEL")
    public void timeOutCancel(Message<RealOrderHandlerEvent> message) {
        OrderDO order =  message.getHeaders().get(Constant.ORDER_STR,OrderDO.class);
        //关闭
        //0 用户取消关闭 1 超时自动关闭 2 支付失败关闭 3 退款成功关闭
        // 取消订单相应逻辑
        OrderStatusDO orderStatusDO = new OrderStatusDO();
        orderStatusDO.setBeforeApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setBeforeStatus(RealOrderStatus.Status.PREPARE_PAY.status);

        orderStatusDO.setAfterApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        orderStatusDO.setAfterCloseType(CloseTypeEnum.TIMEOUT_CANCEL.closeType);
        orderStatusDO.setAfterStatus(RealOrderStatus.Status.HAS_CLOSE.status);
        paymentService.cancelOrder(order,orderStatusDO,realPaymentHandler);
    }













}
