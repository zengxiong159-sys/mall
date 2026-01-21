package com.qdbank.mall.api;

import lombok.Getter;

/**
 * 类型枚举类
 */
@Getter
public enum TypeEnum {
    COUPON_INTEGRATION_TYPE(0L,"积分兑换券"),
    COUPON_PRODUCT_TYPE(1L,"指定商品免费兑换券"),
    COUPON_PRODUCT_CASH_TYPE(2L,"指定商品现金兑换券")
    ;
    private TypeEnum (Long code,String msg){
        this.code = code;
        this.msg = msg;
    }
    private Long code;
    private String msg;

    public static String getCouponTypeDesc(Long couponType){
        if(couponType == null) return "";
        for(TypeEnum typeEnum : TypeEnum.values()){
            if(typeEnum.getCode().equals(couponType)) return typeEnum.getMsg();
        }
        return "";
    }
}
