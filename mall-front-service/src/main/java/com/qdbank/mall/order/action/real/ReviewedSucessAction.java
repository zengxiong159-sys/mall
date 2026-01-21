package com.qdbank.mall.order.action.real;

import com.qdbank.mall.annotation.StateError;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.payment.CloseTypeEnum;
import com.qdbank.mall.constant.payment.event.RealOrderHandlerEvent;
import com.qdbank.mall.constant.payment.orderstatus.RealOrderStatus;
import com.qdbank.mall.constent.payment.RefundStatausEnum;
import com.qdbank.mall.dao.refund.RefundDao;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderStatusDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.order.business.handler.RealPaymentHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 审核成功
 * @author hongjh
 */
@Slf4j
public class ReviewedSucessAction implements Action<RealOrderStatus, RealOrderHandlerEvent> {

    @Autowired
    OrderService orderService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    RealPaymentHandler realPaymentHandler;

    @Autowired
    RefundDao refundDao;

        @Override
        @Transactional(rollbackFor = Exception.class)
        @StateError
        public void execute(StateContext<RealOrderStatus, RealOrderHandlerEvent> context) {
            OrderDO order =  context.getMessage().getHeaders().get(Constant.ORDER_STR,OrderDO.class);
            log.info("审核成功");
            //step 1 发起退款 TODO 退款成功，本系统异常，补偿处理
            OrderRefundDO orderRefund =refundDao.qryNewOrderRefundByOrderSn(order.getOrderSn());
            paymentService.refundOrder(orderRefund,order,null);

            //step 2 逻辑处理
       //     OrderRefundDO orderRefundDO = new OrderRefundDO();
            orderRefund.setHandleResult(1L);
            orderRefund.setHandleStartTime(new Date());
            boolean flag=orderService.updateRefundStatus(orderRefund,order.getCustNo()+"",order.getOrderSn(), RefundStatausEnum.PREPARE_APPROVE.refundStatus,RefundStatausEnum.APPROVE_YES.refundStatus);


            //step 3  取消订单
            OrderStatusDO orderStatusDO = new OrderStatusDO();
            orderStatusDO.setBeforeApproveStatus(RefundStatausEnum.PREPARE_APPROVE.refundStatus);
            orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
            orderStatusDO.setBeforeStatus(order.getStatus());
            orderStatusDO.setAfterApproveStatus(RefundStatausEnum.REFUND.refundStatus);
            orderStatusDO.setAfterCloseType(CloseTypeEnum.REFUND_SUCCESS.closeType);
            orderStatusDO.setAfterStatus(RealOrderStatus.Status.HAS_CLOSE.status);
            paymentService.cancelOrder(order,orderStatusDO,realPaymentHandler);

            //更新审核
        //    orderRefundDO.setHandleFinishTime(new Date());
            orderService.updateRefundStatus(orderRefund,order.getCustNo()+"",order.getOrderSn(),RefundStatausEnum.APPROVE_YES.refundStatus,RefundStatausEnum.REFUND.refundStatus);

            log.info("审核成功..[{}]",order.getOrderSn());
        }



}
