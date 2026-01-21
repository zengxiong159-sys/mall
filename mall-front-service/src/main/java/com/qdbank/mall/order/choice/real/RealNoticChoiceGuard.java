package com.qdbank.mall.order.choice.real;

import com.qdbank.mall.constant.payment.event.RealOrderHandlerEvent;
import com.qdbank.mall.constant.payment.orderstatus.RealOrderStatus;
import com.qdbank.mall.order.choice.CommonNoticeActionAdaptor;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

/**
 * @author hongjh
 * 通知
 */
public class RealNoticChoiceGuard extends CommonNoticeActionAdaptor implements Guard<RealOrderStatus, RealOrderHandlerEvent> {

    @Override
    public boolean evaluate(StateContext<RealOrderStatus, RealOrderHandlerEvent> context) {
        return super.excute(context);
    }
}
