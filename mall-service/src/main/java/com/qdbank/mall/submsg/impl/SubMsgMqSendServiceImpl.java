package com.qdbank.mall.submsg.impl;

import com.alibaba.fastjson.JSON;
import com.qdbank.mall.domain.submsg.SubMsgCommonBO;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.submsg.SubMsgMqSendService;
import com.qdbank.mall.service.MqSendService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.KafkaException;
import org.springframework.stereotype.Service;

import static com.qdbank.mall.util.Constant.SUB_MSG_TOPIC;

/**
 * @Author zengxiong
 * @Description 订阅消息提醒kafka消息发送service实现
 * @Date 2022/7/22 11:37
 */
@Slf4j
@Service
public class SubMsgMqSendServiceImpl implements SubMsgMqSendService {

    @Autowired
    private MqSendService mqSendService;

    @Override
    public int sendSubMsgMq(SubMsgCommonBO subMsgCommonBO) {
        try {
            log.info("sub msg send data:{}", JSON.toJSONString(subMsgCommonBO));
            if (subMsgCommonBO == null || StringUtils.isBlank(subMsgCommonBO.getMsgCode())) {
                return 1;
            }
            mqSendService.sendMsg(SUB_MSG_TOPIC, "", subMsgCommonBO);
            return 1;
        } catch (ApiException e) {
            log.error("sub mgs send error, msgCode:{}", subMsgCommonBO.getMsgCode(), e);
            return 0;
        } catch (KafkaException kafkaException) {
            log.error("sub mgs send error, msgCode:{}", subMsgCommonBO.getMsgCode(), kafkaException);
            return 0;
        } catch (Exception e) {
            log.info("sub mgs send error, msgCode:{}", subMsgCommonBO.getMsgCode(), e);
            return 0;
        }
    }

}
