package com.qdbank.mall.submsg;

import com.qdbank.mall.domain.submsg.SubMsgCommonBO;

/**
 * @Author zengxiong
 * @Description 订阅消息提醒kafka消息发送service
 * @Date 2022/7/22 11:29
 */
public interface SubMsgMqSendService {

    /**
     * 发送订单状态提醒kafka消息
     *
     * @param subMsgCommonBO 订阅消息信息
     * @return 发送结果
     */
    int sendSubMsgMq(SubMsgCommonBO subMsgCommonBO);

}
