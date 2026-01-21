package com.qdbank.mall.order.choice.real;

import com.qdbank.mall.constant.payment.event.RealOrderHandlerEvent;
import com.qdbank.mall.constant.payment.orderstatus.RealOrderStatus;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

/**
 * @author hongjh
 * 审核
 */
@Component("reviewedChoiceGuard")
public class ReviewedChoiceGuard implements Guard<RealOrderStatus, RealOrderHandlerEvent> {

    @Override
    public boolean evaluate(StateContext<RealOrderStatus, RealOrderHandlerEvent> context) {
        //审核状态
        boolean noticeReq = context.getMessage().getHeaders().get("confirmStatus",Boolean.class);
        return noticeReq;
    }
}
