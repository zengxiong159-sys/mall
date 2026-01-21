package com.qdbank.mall.constant.payment;

/**
 *
 * @author hongjh
 * 关闭枚举
 *
 */
public enum CloseTypeEnum {

    /**
     * 订单关闭类型：0 用户取消关闭 1 超时自动关闭 2 支付失败关闭 3 退款成功关闭
     */

    INIT(-1L,"初始化"),
    USER_CANCEL(0L,"用户取消关闭"),
    TIMEOUT_CANCEL(1L,"超时自动关闭"),
    PAY_FAIL(2L,"支付失败关闭"),
    REFUND_SUCCESS(3L,"退款成功关闭"),
    CHARGIND_FAIL(4L,"充值失败"),
    ;
    ;

    public Long closeType;
    public String msg;

    CloseTypeEnum(Long closeType, String msg){
        this.closeType=closeType;
        this.msg=msg;
    }

    public static CloseTypeEnum getCloseTypeEnumByCloseType(Long closeType){
        for(CloseTypeEnum paymentEnum : CloseTypeEnum.values()){
            if(paymentEnum.closeType.equals(closeType)){
                return paymentEnum;
            }
        }
        return null;
    }



}
