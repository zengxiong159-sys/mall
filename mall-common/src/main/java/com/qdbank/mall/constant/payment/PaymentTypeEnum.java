package com.qdbank.mall.constant.payment;

import lombok.AllArgsConstructor;

/**
 *
 * @author hongjh
 * 支付枚举
 *
 */
@AllArgsConstructor
public enum PaymentTypeEnum {


    SCORE(0L,"A","纯积分","22"),
    MONEY_SCORE(2L,"B","现金+积分","22"),
    MONEY(1L,"C","纯现金",""),
    ;

    public Long payType;
    public String value;
    public String msg;
    public String accType;


    public static PaymentTypeEnum getPayEnumByPaytype(Long payType){
        for(PaymentTypeEnum paymentTypeEnum : PaymentTypeEnum.values()){
            if(paymentTypeEnum.payType.equals(payType)){
                return paymentTypeEnum;
            }
        }
        return null;
    }



}
