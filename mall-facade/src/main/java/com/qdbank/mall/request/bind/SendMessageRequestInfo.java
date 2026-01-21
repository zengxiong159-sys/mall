package com.qdbank.mall.request.bind;

import lombok.Data;

@Data
public class SendMessageRequestInfo {
    /**
     *  身份证号码
     * */
    private String idNo;

    /**
     *  手机号码
     * */
    private String mobilePhoneNo;

}
