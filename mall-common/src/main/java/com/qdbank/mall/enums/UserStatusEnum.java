package com.qdbank.mall.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @Author zengxiong
 * @Description 用户状态枚举
 * @Date 2021/5/26 17:28
 */
@Getter
public enum UserStatusEnum {
    /**
     * 禁用
     */
    DISABLE(0L, "禁用"),

    /**
     * 启用
     */
    ENABLE(1L, "启用");

    private Long code;
    private String desc;

    UserStatusEnum(Long code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code获取枚举信息
     *
     * @param code 码值
     * @return 枚举信息
     */
    public static UserStatusEnum getEnumByCode(String code) {
        return Arrays.asList(UserStatusEnum.values()).stream()
                .filter(userStatus -> userStatus.getCode().equals(code))
                .findFirst().orElse(null);
    }
}
