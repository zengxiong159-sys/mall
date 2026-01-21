package com.qdbank.mall.response.bind;

import lombok.Data;

/**
 *  微信绑定明细信息
 * */
@Data
public class WechatBindDetailInfo {

    /**姓名*/
    private String name;

    /**性别*/
    private String gender;

    /**身份证号码*/
    private String idCde;

    /**微信openId*/
    private String openId;

    /**手机号*/
    private String mobilePhoneNo;
    /**
     *微信unionId
     */
    private String unionId;

    private String userType;

}
