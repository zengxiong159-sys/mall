package com.qdbank.mall.constant.payment.metrics;

/**
 *
 * @author hongjh
 * 监控枚举
 *
 */
public enum MetricsEnum {


    CREATE_ORDER("1","下单"),


    PAY_SUCESS("4","支付成功"),
    PAY_ERROR("5","支付失败"),
    USER_OUT_CLOSE("6","用户关闭"),
    TIME_OUT_CLOSE("7","超时关闭"),

    REFUND("2","退款"),
    ERROR("3","系统异常"),

    ;

    public String metricsType;
    public String desc;










    MetricsEnum(String metricsType, String desc){
        this.metricsType=metricsType;
        this.desc=desc;
    }



}
