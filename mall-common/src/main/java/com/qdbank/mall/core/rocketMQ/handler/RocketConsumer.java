package com.qdbank.mall.core.rocketMQ.handler;

import com.alibaba.rocketmq.client.consumer.listener.MessageListener;

/**
 * 消费者接口
 *
 * @author SimpleWu
 *
 */
public interface RocketConsumer {

    /**
     * 初始化消费者
     */
      void init();

    /**
     * 注册监听
     *
     * @param messageListener
     */
     void registerMessageListener(MessageListener messageListener);

}
