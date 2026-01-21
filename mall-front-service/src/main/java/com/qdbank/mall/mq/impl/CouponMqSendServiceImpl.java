package com.qdbank.mall.mq.impl;

import com.qdbank.mall.domain.CouponMQBO;
import com.qdbank.mall.mapper.send.MqSendDOMapper;
import com.qdbank.mall.model.send.MqSendDO;
import com.qdbank.mall.mq.CouponMqSendService;
import com.qdbank.mall.service.SendCouponMqService;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName CouponMqSendServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/5/23 11:44
 * @Version 1.0
 **/
@Service
public class CouponMqSendServiceImpl extends BaseServiceImpl implements CouponMqSendService {
    @Autowired
    private MqSendDOMapper mqSendDOMapper;
    @Autowired
    private SendCouponMqService sendCouponMqService;
    @Override
    public void couponMqSend(CouponMQBO couponMQBO) {
        int succFlag = sendCouponMqService.sendCouponMessage(couponMQBO);
        if(succFlag == 0){//mq写入失败
            MqSendDO mqSendDO = new MqSendDO();
            mqSendDO.setId(super.generateId());
            mqSendDO.setCreateTime(new Date());
            mqSendDO.setUniqueId(couponMQBO.getCouponId());
            mqSendDOMapper.insert(mqSendDO);
        }
    }
}
