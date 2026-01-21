package com.qdbank.mall.api;

import lombok.Getter;

/**
 * 状态枚举类
 */
@Getter
public enum StatusEnum {
    //活动状态
    ACTIVITY_CLOSE_STATUS(0l,"活动状态-停用"),
    ACTIVITY_OPEN_STATUS(1l,"活动状态-启用"),
    //广告状态
    ADVERTISMENT_CLOSE_STATUS(0l,"广告状态-停用"),
    ADVERTISMENT_OPEN_STATUS(1l,"广告状态-启用"),
    //优惠券状态 -1 待发放 0 待使用1 已使用2 已过期3 已作废
    USER_COUPON_NOT_SEND(-1L,"用户优惠券状态-待发放"),
    USER_COUPON_NOT_USED(0l,"用户优惠券状态-待使用"),
    USER_COUPON_USED(1l,"用户优惠券状态-已使用"),
    USER_COUPON_EXPIRE(2l,"用户优惠券状态-已过期"),
    USER_COUPON_INVLALID(3l,"用户优惠券状态-已作废"),
    //批次状态:0 待发放1 已发放2 已过期 3 已作废
    BATCH_STATUS_NOT_SENDED(0L,"批次状态-待发放"),
    BATCH_STATUS_SENDED(1L,"批次状态-已发放"),
    BATCH_STATUS_EXPIRE(2L,"批次状态-已过期"),
    BATCH_STATUS_INVLALID(3L,"批次状态-已作废"),
    BATCH_STATUS_TO_BE_EFFECTIVE(4L,"批次状态-待生效"),
    BATCH_STATUS_EFFECTIVE(5L,"批次状态-已生效"),
    BATCH_STATUS_INVALIDATION(6L,"批次状态-已失效"),
    //上架状态 0待入库 1已入库 2已上架
    PUBLISH_STATUS_OUT(0L,"待入库"),
    PUBLIST_STATUS_IN(1L,"已入库"),
    PUBLIST_STATUS_UP(2L,"已上架"),
    //券商品状态
    COUPON_PRODUCT_STATUS_OUT(0L, "待上架"),
    COUPON_PRODUCT_STATUS_ON(1L, "已上架"),
    COUPON_PRODUCT_STATUS_OFF(2L, "已下架");

    private StatusEnum (Long code,String msg){
        this.code = code;
        this.msg = msg;
    }
    private Long code;
    private String msg;
}
