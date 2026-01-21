package com.qdbank.mall.order.choice.mobile;

import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.payment.event.MobileReChargePayOrderEvent;
import com.qdbank.mall.constant.payment.orderstatus.MobileReChargePayOrderStatus;
import com.qdbank.mall.constant.payment.MobileRechargeStatus;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.request.third.mobile.MobileNoticeReqDTO;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

/**
 * @author hongjh
 * 充值通知
 */
public class MobileChargeNoticChoiceGuard  implements Guard<MobileReChargePayOrderStatus,MobileReChargePayOrderEvent> {

    @Override
    public boolean evaluate(StateContext<MobileReChargePayOrderStatus,MobileReChargePayOrderEvent> context) {
        MobileNoticeReqDTO noticeReq = context.getMessage().getHeaders().get("mobileNoticeReq", MobileNoticeReqDTO.class);
        //订单号
        OrderDO order = context.getMessage().getHeaders().get(Constant.ORDER_STR,OrderDO.class);
        /**
         * L 失败 、S成功
         */
        String status= noticeReq.getStatus();

        /**
         * 1	充值中
         * 2	充值失败
         * 3	充值成功
         */
        MobileRechargeStatus statusEnum = MobileRechargeStatus.getName(status);

        if(MobileRechargeStatus.CHARGE_SUCCESS==statusEnum){
            return true;
        }else if(MobileRechargeStatus.CHARGE_FAIL==statusEnum){
            return false;
        }
        throw new ApiException(ResultCode.MOBILE_RECHARGE_ERROR);
    }
}
