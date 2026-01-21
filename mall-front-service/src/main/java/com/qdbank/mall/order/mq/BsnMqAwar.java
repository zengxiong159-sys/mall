package com.qdbank.mall.order.mq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;

public interface BsnMqAwar {


     String getTopicHandlerType();

    ConsumeConcurrentlyStatus handler(byte[] msg) ;

}
