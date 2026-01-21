package com.qdbank.mall.constant.payment.lifestatus;

public enum MobileRechargeOrderLifeStatus {

    INIT(0L,"已下单"),
    PREPARE_PAY(1L,"待支付"),
    PAY(2L,"支付处理中"),
    PAY_SUCCESS(3L,"已支付"),

    CHARGEING(4L,"充值中"),
    CHARGE_SUCCESS(5L,"充值成功"),
    CLOSE(6L,"取消关闭"),
    HAS_FINISH(7L,"已完成"),
    RECHARGE_FAIL(8L,"充值失败"),
    CREATE_REFUND(9L,"发起退款"),
    REFUND_SUCCESS(10L,"退款成功"),
    REFUND_FAIL(11L,"退款失败"),

    ;

    public Long status;
    public String msg;


    MobileRechargeOrderLifeStatus(Long status, String msg){
        this.status=status;
        this.msg=msg;
    }




}
