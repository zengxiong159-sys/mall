package com.qdbank.mall.constant.payment.metrics;

/**
 *
 * @author hongjh
 * 监控枚举
 *
 */
public enum OrderRefundMetricsEnum {


    USER_REFUND("1","用户主动退款"),
    SYSTEM("2","系统自动退款"),
    ERROR("3","极端场景退款"),

    ;

    public String metricsType;
    public String desc;

    OrderRefundMetricsEnum(String metricsType, String desc){
        this.metricsType=metricsType;
        this.desc=desc;
    }



}
