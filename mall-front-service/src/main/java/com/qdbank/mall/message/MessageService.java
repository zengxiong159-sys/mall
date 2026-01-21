package com.qdbank.mall.message;

/**
 * 短信服务
 * @author liuxiaodong
 * */
public interface MessageService {

    /**短信发送*/
    String sendMessage(String phoneNo);

    /**短信验证*/
    void verifyMessage(String authCode,String messageCode,String mobilePhoneNo);
}
