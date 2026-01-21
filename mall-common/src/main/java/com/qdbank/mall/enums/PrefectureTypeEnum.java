package com.qdbank.mall.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @Author zengxiong
 * @Description 专区类型枚举
 * @Date 2021/8/13 17:28
 */
@Getter
public enum PrefectureTypeEnum {

    /**
     * 常规专区
     */
    NORMAL(0, "常规专区"),

    /**
     * 达标专区
     */
    UP_TO_STANDARD(1, "达标专区");

    private Integer code;
    private String desc;

    PrefectureTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code获取枚举信息
     *
     * @param code 码值
     * @return 枚举信息
     */
    public static PrefectureTypeEnum getEnumByCode(Integer code) {
        return Arrays.stream(PrefectureTypeEnum.values())
                .filter(type -> type.getCode().equals(code))
                .findFirst().orElse(null);
    }
}
