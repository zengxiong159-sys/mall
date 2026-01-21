package com.qdbank.mall.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @Author zengxiong
 * @Description 优惠券类型枚举
 * @Date 2021/8/13 17:28
 */
@Getter
public enum CouponTypeEnum {

    /**
     * 积分兑换券
     */
    INTEGRATION_COUPON(0L, "积分兑换券"),

    /**
     * 指定商品免费兑换券
     */
    PRODUCT_FREE_COUPON(1L, "指定商品免费兑换券"),

    /**
     * 指定商品现金兑换券
     */
    PRODUCT_CASH_COUPON(2L, "指定商品现金兑换券"),

    /**
     * 指定专区现金优惠券
     */
    PREFECTURE_CASH_COUPON(4L, "指定专区现金优惠券"),

    /**
     * 全场通用现金优惠券
     */
    GENERAL_CASH_COUPON(5L, "全场通用现金优惠券");

    private Long code;
    private String desc;

    CouponTypeEnum(Long code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code获取枚举信息
     *
     * @param code 码值
     * @return 枚举信息
     */
    public static CouponTypeEnum getEnumByCode(Long code) {
        return Arrays.stream(CouponTypeEnum.values())
                .filter(userStatus -> userStatus.getCode().equals(code))
                .findFirst().orElse(null);
    }
}
