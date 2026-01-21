package com.qdbank.mall.core.rocketMQ.handler;

import com.alibaba.rocketmq.client.consumer.MQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.MessageListener;

/**
 * 实现类
 * @author hongjh
 */
public abstract class AbstractRocketConsumer implements RocketConsumer {

    public String topics;
    public String tags;
    public MessageListener messageListener;
    public String consumerTitel;
    public MQPushConsumer mqPushConsumer;

    /**
     * 必要的信息
     *
     * @param topics
     * @param tags
     * @param consumerTitel
     */
    public void necessary(String topics, String tags, String consumerTitel) {
        this.topics = topics;
        this.tags = tags;
        this.consumerTitel = consumerTitel;
    }

    @Override
    public void registerMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

}
