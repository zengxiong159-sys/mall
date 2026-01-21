package com.qdbank.mall.core.rocketMQ.config;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import com.qdbank.mall.core.rocketMQ.handler.AbstractRocketConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.Map;

/**
 * mq配置
 *
 * @author hongjh
 */
@Slf4j
public class RocketCosumerMQ implements InitializingBean {

    @Autowired
    private RocketMQProperties properties;

    @Autowired
    private ApplicationContext applicationContext;


    public void initConsumer() {
        Map<String, AbstractRocketConsumer> consumers = applicationContext.getBeansOfType(AbstractRocketConsumer.class);
        if (consumers == null || consumers.size() == 0) {
            log.info("init rocket consumer 0");
        }
        Iterator<String> beans = consumers.keySet().iterator();
        while (beans.hasNext()) {
            String beanName = (String) beans.next();
            AbstractRocketConsumer consumer = consumers.get(beanName);
            consumer.init();
            createConsumer(consumer);
            log.info("init success consumer title {} , toips {} , tags {}", consumer.consumerTitel, consumer.tags,
                    consumer.topics);
        }
    }

    /**
     * 通过消费者信心创建消费者
     *
     */
    public void createConsumer(AbstractRocketConsumer arc) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(this.properties.getGroupName());
        consumer.setNamesrvAddr(this.properties.getNamesrvAddr());
        consumer.setConsumeThreadMin(this.properties.getConsumerConsumeThreadMin());
        consumer.setConsumeThreadMax(this.properties.getConsumerConsumeThreadMax());
        consumer.setMessageListener(arc.messageListener);
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        // consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        /**
         * 设置消费模型，集群还是广播，默认为集群
         */
         consumer.setMessageModel(MessageModel.CLUSTERING);

        /**
         * 设置一次消费消息的条数，默认为1条
         */
        consumer.setConsumeMessageBatchMaxSize(this.properties.getConsumerConsumeMessageBatchMaxSize());
        try {
            consumer.subscribe(properties.getBsn().getRechargeRefund(), "*");
            consumer.subscribe(properties.getBsn().getTimeOut(), "*");
            consumer.start();
       //     arc.mqPushConsumer=consumer;
        } catch (MQClientException e) {
            log.error("info consumer title {}", "sfdasdf", e);
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initConsumer();
    }


}
