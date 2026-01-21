package com.qdbank.mall.message.impl;

import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.service.GenericException;
import com.google.common.collect.Maps;
import com.qdbank.mall.exception.MessageVerifyErrorException;
import com.qdbank.mall.exception.MessageVerifyTimeOutErrorException;
import com.qdbank.mall.message.MessageService;
import com.qdbank.mall.rpc.RRException;
import com.qdbank.mall.rpc.consumer.DubboService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    //渠道
    private static final String CHANNEL_CODE="MB";
    //模板代码
    private static final String BSN_CODE="SPG3003";
    //短信验证码验证失败错误码
    private static final String MESSAGE_VERIFY_ERROR_CODE="SE9000021";
    //短信验证码超时错误码
    private static final String MESSAGE_VERIFY_TIMEOUT_ERROR_CODE="SE9000022";

    @Resource
    private DubboService dubboService;

    @Override
    public String sendMessage(String mobilePhoneNo) {
        //调用接口发送短信
        Map request= Maps.newHashMap();
        request.put("bsnCode",BSN_CODE);
        request.put("channel",CHANNEL_CODE);
        request.put("custNo",mobilePhoneNo);
        request.put("mobileNo",mobilePhoneNo);
        request.put("param4",mobilePhoneNo);

        Map<String,String> result = dubboService.call("SC900001",request);
        return result.get("authCode");
    }

    @Override
    public void verifyMessage(String authCode,String messageCode,String mobilePhoneNo) {
        //短信验证
        Map request= Maps.newHashMap();
        request.put("authCode",authCode);
        request.put("messageAuth",messageCode);
        request.put("custNo",mobilePhoneNo);
        request.put("mobileNo",mobilePhoneNo);

        try {
            dubboService.call("SC900002",request);
        }catch (RRException e){
            if(StringUtils.equals(e.getErrorCode(),MESSAGE_VERIFY_ERROR_CODE)){

                throw new MessageVerifyErrorException("短信验证码错误");
            }else if(StringUtils.equals(e.getErrorCode(),MESSAGE_VERIFY_TIMEOUT_ERROR_CODE)){
                throw new MessageVerifyTimeOutErrorException("短信验证码超时");
            }
            throw e;
        }catch (GenericException e){
            if(StringUtils.contains(e.getMessage(),MESSAGE_VERIFY_ERROR_CODE)){
                throw new MessageVerifyErrorException("短信验证码错误");
            }else if(StringUtils.contains(e.getMessage(),MESSAGE_VERIFY_TIMEOUT_ERROR_CODE)){
                throw new MessageVerifyTimeOutErrorException("短信验证码超时");
            }
            throw new RpcException(e);
        }
    }
}
