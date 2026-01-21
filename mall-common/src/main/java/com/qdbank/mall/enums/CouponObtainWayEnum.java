package com.qdbank.mall.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @Author zengxiong
 * @Description 优惠券白名单获取方式枚举
 * @Date 2021/8/13 17:28
 */
@Getter
public enum CouponObtainWayEnum {

    /**
     * 白名单文件
     */
    WHITE_LIST_FILE(0L, "白名单文件"),

    /**
     * 北斗
     */
    BEIDOU(1L, "北斗");

    private Long code;
    private String desc;

    CouponObtainWayEnum(Long code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code获取枚举信息
     *
     * @param code 码值
     * @return 枚举信息
     */
    public static CouponObtainWayEnum getEnumByCode(Long code) {
        return Arrays.stream(CouponObtainWayEnum.values())
                .filter(userStatus -> userStatus.getCode().equals(code))
                .findFirst().orElse(null);
    }
}
