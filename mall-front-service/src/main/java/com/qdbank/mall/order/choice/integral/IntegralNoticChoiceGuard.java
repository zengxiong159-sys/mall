package com.qdbank.mall.order.choice.integral;

import com.qdbank.mall.constant.payment.event.IntegralOrderEvent;
import com.qdbank.mall.constant.payment.orderstatus.IntegralOrderStatus;
import com.qdbank.mall.order.choice.CommonNoticeActionAdaptor;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

/**
 * @author hongjh
 * 通知
 */
public class IntegralNoticChoiceGuard extends CommonNoticeActionAdaptor implements Guard<IntegralOrderStatus, IntegralOrderEvent> {



    @Override
    public boolean evaluate(StateContext<IntegralOrderStatus, IntegralOrderEvent> context) {
        return super.excute(context);
    }
}
