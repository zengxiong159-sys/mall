package com.qdbank.mall.constent.payment;

/**
 *
 * @author hongjh
 *  审核状态枚举
 *
 */
public enum RefundStatausEnum {

    INIT(-1L,"初始化"),
    PREPARE_APPROVE(0L,"待审核"),
    APPROVE_YES(1L,"审核通过"),
    REFUND(2L,"退款成功"),
    APPROVE_NO(3L,"审核不通过"),
    APPROVE_CANCEL(4L,"退款申请撤销"),
    ;
    ;

    public Long refundStatus;
    public String msg;

    RefundStatausEnum(Long refundStatus, String msg){
        this.refundStatus=refundStatus;
        this.msg=msg;
    }

    public static RefundStatausEnum getRefundStatausEnumByCode(Long refundStatus){
        for(RefundStatausEnum refundStatausEnum : RefundStatausEnum.values()){
            if(refundStatausEnum.refundStatus.equals(refundStatus)){
                return refundStatausEnum;
            }
        }
        return null;
    }





}
