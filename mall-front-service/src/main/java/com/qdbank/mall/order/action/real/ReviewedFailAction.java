package com.qdbank.mall.order.action.real;

import com.qdbank.mall.annotation.StateError;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.payment.CloseTypeEnum;
import com.qdbank.mall.constant.payment.event.RealOrderHandlerEvent;
import com.qdbank.mall.constant.payment.orderstatus.RealOrderStatus;
import com.qdbank.mall.constent.payment.RefundStatausEnum;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderStatusDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 审核失败
 * @author hongjh
 */
@Slf4j
public class ReviewedFailAction implements Action<RealOrderStatus, RealOrderHandlerEvent> {

    @Autowired
    OrderService orderService;


        @Override
        @StateError
        @Transactional(rollbackFor = Exception.class)
        public void execute(StateContext<RealOrderStatus, RealOrderHandlerEvent> context) {

            OrderDO order =  context.getMessage().getHeaders().get(Constant.ORDER_STR,OrderDO.class);
            // 审核失败
            OrderStatusDO orderStatusDO = new OrderStatusDO();
            orderStatusDO.setBeforeApproveStatus(RefundStatausEnum.PREPARE_APPROVE.refundStatus);
            orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
            orderStatusDO.setBeforeStatus(order.getStatus());

            //审核不通过
            orderStatusDO.setAfterApproveStatus(RefundStatausEnum.APPROVE_NO.refundStatus);
            orderStatusDO.setAfterCloseType(CloseTypeEnum.INIT.closeType);
            orderStatusDO.setAfterStatus(order.getStatus());

            orderService.updateUserOrderStatus(null,order.getCustNo()+"",order,orderStatusDO);

            OrderRefundDO orderRefundDO = new OrderRefundDO();
            orderRefundDO.setHandleResult(0L);
            orderRefundDO.setHandleStartTime(new Date());
            //更新申请状态--审核不通过
            boolean flag=orderService.updateRefundStatus(orderRefundDO,order.getCustNo()+"",order.getOrderSn(), RefundStatausEnum.PREPARE_APPROVE.refundStatus,RefundStatausEnum.APPROVE_NO.refundStatus);

            log.info("审核失败..[{}][{}]",order.getOrderSn(),flag);
        }



}
