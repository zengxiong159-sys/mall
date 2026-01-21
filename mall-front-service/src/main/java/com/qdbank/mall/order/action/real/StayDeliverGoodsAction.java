package com.qdbank.mall.order.action.real;

import com.qdbank.mall.annotation.StateError;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.payment.CloseTypeEnum;
import com.qdbank.mall.constant.payment.event.RealOrderHandlerEvent;
import com.qdbank.mall.constant.payment.orderstatus.RealOrderStatus;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderStatusDO;
import com.qdbank.mall.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class StayDeliverGoodsAction implements Action<RealOrderStatus, RealOrderHandlerEvent> {


    @Autowired
    OrderService orderService;


        @Override
        @StateError
        @Transactional(rollbackFor = Exception.class)
        public void execute(StateContext<RealOrderStatus, RealOrderHandlerEvent> context) {
            OrderDO order =  context.getMessage().getHeaders().get(Constant.ORDER_STR,OrderDO.class);
            //待发货
            OrderStatusDO orderStatusDO = new OrderStatusDO();
            orderStatusDO.setBeforeApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
            orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
            orderStatusDO.setBeforeStatus(RealOrderStatus.Status.PREPARE_PAY.status);

            orderStatusDO.setAfterApproveStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
            orderStatusDO.setAfterCloseType(CloseTypeEnum.INIT.closeType);
            orderStatusDO.setAfterStatus(RealOrderStatus.Status.STAY_DELIVER_GOODS.status);

      //      OrderDO updateOrder = new OrderDO();
      //      updateOrder.setPaymentTime(new Date());
            boolean flag = orderService.updateUserOrderStatus(null,order.getCustNo()+"",order,orderStatusDO);
            log.info("待发货..[{}][{}]",order.getOrderSn(),flag);
        }



}
