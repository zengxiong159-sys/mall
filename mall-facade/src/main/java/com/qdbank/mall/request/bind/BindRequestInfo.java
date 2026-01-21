package com.qdbank.mall.request.bind;

import lombok.Data;

import java.io.Serializable;

@Data
public class BindRequestInfo implements Serializable {

    /**证件号码*/
    private String idCde;

    /**手机号*/
    private String mobileNo;

    /**sessionNo*/
    private String sessionKey;

    /**授权码*/
    private String authCode;

    /**短信验证码*/
    private String messageCode;

    /**手机号*/
    private String mobilePhoneNo;
}
