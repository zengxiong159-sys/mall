package com.qdbank.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.service.MqSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @ClassName MqSendServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/5/18 19:47
 * @Version 1.0
 **/
@Service
@Slf4j
public class MqSendServiceImpl implements MqSendService  {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Override
    public void sendMsg(String topic,String tag, Object obj) throws ApiException{
        if(obj == null) return;
        ListenableFuture<org.springframework.kafka.support.SendResult<String, String>> future = kafkaTemplate.send(topic, JSON.toJSONString(obj));
        future.addCallback(new KafkaSendCallback<String, String>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent message with offset=[{}]", result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(KafkaProducerException ex) {
                log.error("Unable to send message due to : " + ex.getMessage(), ex);
                throw new ApiException("发送MQ消息异常");
            }
        });
    }

}
