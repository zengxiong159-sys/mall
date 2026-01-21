package com.qdbank.mall.service;

import com.qdbank.mall.exception.ApiException;

public interface MqSendService {
    /**
     * 发送mq消息
     * @param topic 消息主题
     * @param obj 消息体
     */
    public void sendMsg(String topic,String tag,Object obj) throws ApiException;

}
