package com.qdbank.mall.constant.payment;

/**
 *
 * @author hongjh
 * 支付流水枚举
 *
 */
public enum PaymentStatusEnum {


    PREPATE_PAY("0","待支付"),
    PAY_SUCCESS("1","支付成功"),
    PAY_FAIL("2","支付失败"),
    REFUND_ING("3","退款中"),
    REFUND_SUCCESS("4","退款成功"),
    REFUND_FAIL("5","退款失败"),
    ;

    public String payType;
    public String msg;

    PaymentStatusEnum(String payType,  String msg){
        this.payType=payType;
        this.msg=msg;
    }



}
