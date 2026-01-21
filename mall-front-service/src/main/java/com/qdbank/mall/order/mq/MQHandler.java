package com.qdbank.mall.order.mq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.qdbank.mall.core.rocketMQ.config.RocketMQProperties;
import com.qdbank.mall.core.rocketMQ.handler.AbstractRocketConsumer;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OrderRefundErrorMq
 *
 * @author hongjh
 * @date 2021/4/25 18:24
 * @since 1.0.0
 */

@Component
@Slf4j
public class MQHandler extends AbstractRocketConsumer implements InitializingBean {

    static Map<String,Integer> mqDelay = new HashMap<>();

    static{
        //1s、 5s、 10s、 30s、 1m、 2m、 3m、 4m、 5m、 6m、 7m、 8m、 9m、 10m、 20m、 30m、 1h、 2h
        mqDelay.put("1s",1);
        mqDelay.put("5s",2);
        mqDelay.put("10s",3);
        mqDelay.put("30s",4);
        mqDelay.put("1m",5);
        mqDelay.put("2m",6);
        mqDelay.put("3m",7);
        mqDelay.put("4m",8);
        mqDelay.put("5m",9);
        mqDelay.put("6m",10);
        mqDelay.put("7m",11);
        mqDelay.put("8m",12);
        mqDelay.put("9m",13);
        mqDelay.put("10m",14);
        mqDelay.put("20m",15);
        mqDelay.put("30m",16);
        mqDelay.put("1h",17);
        mqDelay.put("2h",18);
    }



    @Autowired
    DefaultMQProducer defaultMQProducer;

    @Autowired
    OrderService orderService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    private RocketMQProperties properties;

    @Autowired
    ApplicationContext applicationContext;

    Map<String,BsnMqAwar> allBeans = new HashMap<>();





    /**
     * 初始化消费者
     */
    @Override
    public void init() {
        registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                //单条处理
                msgs.forEach(msg -> {
                    String topic = msg.getTopic();
                    BsnMqAwar awar =allBeans.get(topic);
                    log.info("MQ 执行 [{}]-[{}]",topic,new String(msg.getBody()));
                    awar.handler(msg.getBody());
                });
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
    }


    public static Integer getDelay(Integer time){
        return mqDelay.get(time+"m");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String,BsnMqAwar> beans =applicationContext.getBeansOfType(BsnMqAwar.class);
        for(BsnMqAwar awar : beans.values()){
            allBeans.put(awar.getTopicHandlerType(),awar);
        }
    }





}
