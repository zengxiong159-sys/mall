package com.qdbank.mall.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @Author zengxiong
 * @Description 优惠券发放方式枚举
 * @Date 2021/8/13 17:28
 */
@Getter
public enum CouponDistributeWayEnum {

    /**
     * 自行兑换
     */
    SELF_EXCHANGE(0L, "自行兑换"),

    /**
     * 行方发放
     */
    SYSTEM_DISTRIBUTE(1L, "行方发放");

    private Long code;
    private String desc;

    CouponDistributeWayEnum(Long code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code获取枚举信息
     *
     * @param code 码值
     * @return 枚举信息
     */
    public static CouponDistributeWayEnum getEnumByCode(Long code) {
        return Arrays.stream(CouponDistributeWayEnum.values())
                .filter(userStatus -> userStatus.getCode().equals(code))
                .findFirst().orElse(null);
    }
}
