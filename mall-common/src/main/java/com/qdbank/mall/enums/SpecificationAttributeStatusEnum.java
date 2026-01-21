package com.qdbank.mall.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @Author zengxiong
 * @Description 规格属性状态枚举
 * @Date 2021/5/26 17:28
 */
@Getter
public enum SpecificationAttributeStatusEnum {
    /**
     * 禁用
     */
    PUT_OFF(0L, "下架"),

    /**
     * 启用
     */
    PUT_ON(1L, "上架");

    private Long code;
    private String desc;

    SpecificationAttributeStatusEnum(Long code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code获取枚举信息
     *
     * @param code 码值
     * @return 枚举信息
     */
    public static SpecificationAttributeStatusEnum getEnumByCode(String code) {
        return Arrays.asList(SpecificationAttributeStatusEnum.values()).stream()
                .filter(userStatus -> userStatus.getCode().equals(code))
                .findFirst().orElse(null);
    }
}
