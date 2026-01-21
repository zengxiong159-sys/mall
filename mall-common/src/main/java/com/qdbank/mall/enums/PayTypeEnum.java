package com.qdbank.mall.enums;

import lombok.Getter;

@Getter
public enum PayTypeEnum {
    CASH(1L,"BM","纯现金"),
    INTEGRATION(0L,"BP","纯积分"),
    CASHINTEGRATION(2L,"PM","现金+积分");
    /**
     * 支付方式
     */
    private Long payType;
    /**
     * 转换后类型
     */
    private String changeType;
    /**
     * 描述
     */
    private String desc;
    private PayTypeEnum(Long payType,String changeType,String desc){
        this.payType = payType;
        this.changeType = changeType;
        this.desc = desc;
    }
    public static String getChangeType(Long payType){
        for (PayTypeEnum payTypeEnum : PayTypeEnum.values()){
            if(payTypeEnum.getPayType().equals(payType)) return payTypeEnum.getChangeType();
        }
        return "";
    }
}
