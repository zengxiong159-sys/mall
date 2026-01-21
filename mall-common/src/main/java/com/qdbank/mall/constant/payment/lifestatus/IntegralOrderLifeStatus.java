package com.qdbank.mall.constant.payment.lifestatus;

public enum IntegralOrderLifeStatus {

    INIT(0L,"已下单"),
    PREPARE_PAY(1L,"待支付"),
    PAY(2L,"支付处理中"),
    PAY_SUCCESS(3L,"已支付"),
    PREPARE_USE(4L,"待使用"),
    HAS_USE(5L,"已使用"),
    CLOSE(6L,"取消关闭"),
    EXPIRE(7L,"已过期"),
    ;

    public Long status;
    public String msg;


    IntegralOrderLifeStatus(Long status,String msg){
        this.status=status;
        this.msg=msg;
    }




}
