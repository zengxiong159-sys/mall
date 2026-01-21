package com.qdbank.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.qdbank.mall.domain.CouponMQBO;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.service.MqSendService;
import com.qdbank.mall.service.SendCouponMqService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.KafkaException;
import org.springframework.stereotype.Service;

/**
 * @ClassName SendCouponMqServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/5/18 20:51
 * @Version 1.0
 **/
@Slf4j
@Service
public class SendCouponMqServiceImpl implements SendCouponMqService {
    private static final String MQ_TOPIC = "WECHAT_TP_SPG_COUPON";
    private static final String MQ_TAG = "wechat_spg_coupon_tag";
    @Autowired
    private ApplicationContext context;
    @Autowired
    private MqSendService mqSendService;
    @Override
    public int sendCouponMessage(CouponMQBO couponMQBO) {
        try {
            log.info("写入MQ消息data:{}", JSON.toJSONString(couponMQBO));
            if(couponMQBO == null || StringUtils.isBlank(couponMQBO.getCouponId())) return 1;
            String topic = getCouponDynamicTopic();
            log.info("topic:{}",topic);
            mqSendService.sendMsg(topic,MQ_TAG,couponMQBO);
            return 1;
        }catch (ApiException e){
            log.info("写入MQ消息主键:{} 异常:{}",couponMQBO.getCouponId(),e);
            return 0;
        }catch (KafkaException kafkaException){
            log.info("写入MQ消息主键:{} kafka异常:{}",couponMQBO.getCouponId(),kafkaException);
            return 0;
        }catch (Exception e){
            log.info("写入MQ消息主键:{} 其他异常:{}",couponMQBO.getCouponId(),e);
            return 0;
        }
    }


    public String getActiveProfile() {
        return context.getEnvironment().getActiveProfiles()[0];
    }

    public String getCouponDynamicTopic() {
        String activeProfiles = getActiveProfile();
        return activeProfiles.startsWith("sit") ? MQ_TOPIC + "_" + activeProfiles : MQ_TOPIC;
    }
}
