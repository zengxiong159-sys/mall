package com.qdbank.mall.order.choice.real;

import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.payment.event.RealOrderHandlerEvent;
import com.qdbank.mall.constant.payment.orderstatus.RealOrderStatus;
import com.qdbank.mall.model.order.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

/**
 * @author hongjh
 * -----分支判断----
 * 支付成功后的判断:订单是否关闭
 */
@Slf4j
public class RealPaySuccessCloseChoiceGuard implements Guard<RealOrderStatus, RealOrderHandlerEvent> {


    @Override
    public boolean evaluate(StateContext<RealOrderStatus, RealOrderHandlerEvent> context) {
        OrderDO orderDO = context.getMessage().getHeaders().get(Constant.ORDER_STR,OrderDO.class);
        Long status =orderDO.getStatus();
        //关闭
        if(RealOrderStatus.CANCEL.status.equals(status)){
            log.info("支付成功后，出现订单已取消[{}]",orderDO.getOrderSn());
            return false;
        }
        return true;
    }
}
