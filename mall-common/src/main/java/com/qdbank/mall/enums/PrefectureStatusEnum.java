package com.qdbank.mall.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @Author zengxiong
 * @Description 专区状态枚举
 * @Date 2021/8/13 17:28
 */
@Getter
public enum PrefectureStatusEnum {

    /**
     * 停用
     */
    DISABLE(0L, "停用"),

    /**
     * 启用
     */
    ENABLE(1L, "启用");

    private Long code;
    private String desc;

    PrefectureStatusEnum(Long code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code获取枚举信息
     *
     * @param code 码值
     * @return 枚举信息
     */
    public static PrefectureStatusEnum getEnumByCode(Long code) {
        return Arrays.stream(PrefectureStatusEnum.values())
                .filter(status -> status.getCode().equals(code))
                .findFirst().orElse(null);
    }
}
