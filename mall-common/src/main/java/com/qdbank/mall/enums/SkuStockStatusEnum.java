package com.qdbank.mall.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @Author zengxiong
 * @Description 规格状态枚举
 * @Date 2021/5/26 17:28
 */
@Getter
public enum SkuStockStatusEnum {
    /**
     * 禁用
     */
    PUT_ON(0L, "上架"),

    /**
     * 启用
     */
    PUT_OFF(1L, "下架");

    private Long code;
    private String desc;

    SkuStockStatusEnum(Long code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code获取枚举信息
     *
     * @param code 码值
     * @return 枚举信息
     */
    public static SkuStockStatusEnum getEnumByCode(String code) {
        return Arrays.asList(SkuStockStatusEnum.values()).stream()
                .filter(userStatus -> userStatus.getCode().equals(code))
                .findFirst().orElse(null);
    }
}
