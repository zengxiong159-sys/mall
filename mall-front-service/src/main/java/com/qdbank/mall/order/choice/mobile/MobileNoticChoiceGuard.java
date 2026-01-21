package com.qdbank.mall.order.choice.mobile;

import com.qdbank.mall.constant.payment.event.MobileReChargePayOrderEvent;
import com.qdbank.mall.constant.payment.orderstatus.MobileReChargePayOrderStatus;
import com.qdbank.mall.order.choice.CommonNoticeActionAdaptor;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

/**
 * @author hongjh
 * 通知
 */
public class MobileNoticChoiceGuard extends CommonNoticeActionAdaptor implements Guard<MobileReChargePayOrderStatus,MobileReChargePayOrderEvent> {

    @Override
    public boolean evaluate(StateContext<MobileReChargePayOrderStatus,MobileReChargePayOrderEvent> context) {
        return super.excute(context);
    }
}
