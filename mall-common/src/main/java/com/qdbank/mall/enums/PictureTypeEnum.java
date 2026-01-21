package com.qdbank.mall.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @Author zengxiong
 * @Description 图片类型枚举
 * @Date 2021/5/26 17:28
 */
@Getter
public enum PictureTypeEnum {
    /**
     * JPG
     */
    JPG,

    /**
     * PNG
     */
    PNG,

    /**
     * JPEG
     */
    JPEG;

    /**
     * 根据name获取枚举信息
     *
     * @param name 枚举名称
     * @return 枚举信息
     */
    public static PictureTypeEnum getEnumByName(String name) {
        return Arrays.stream(PictureTypeEnum.values())
                .filter(pictureType -> pictureType.name().equals(name))
                .findFirst().orElse(null);
    }
}
