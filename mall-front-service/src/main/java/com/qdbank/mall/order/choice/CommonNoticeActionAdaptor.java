package com.qdbank.mall.order.choice;

import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.request.third.payment.NoticeReqDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;

import java.util.Date;

@Slf4j
public class CommonNoticeActionAdaptor {


    public boolean excute(StateContext context) {
        NoticeReqDTO noticeReq = context.getMessage().getHeaders().get("noticeReq",NoticeReqDTO.class);
        //订单号
        OrderDO order = context.getMessage().getHeaders().get(Constant.ORDER_STR,OrderDO.class);
        /**
         * L 失败 、S成功
         */
        String status= noticeReq.getStatus();

        //状态
        if("S".equals(status)){
            return true;
        }else if("L".equals(status)){
            return false;
        }

        return false;
    }




}
