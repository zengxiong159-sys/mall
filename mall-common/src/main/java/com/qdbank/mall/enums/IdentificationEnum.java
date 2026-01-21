package com.qdbank.mall.enums;

import lombok.Getter;

/**
 * 商品标识枚举
 */
@Getter
public enum IdentificationEnum {
    COMMON_PRODUCT(0,"常规商品"),
    REACH_PRODUCT(1,"达标专属礼")
    ;
    /**
     * 商品标识
     */
    private Integer identification;
    /**
     * 商品标识描述
     */
    private String desc;
    private IdentificationEnum(Integer identification,String desc){
        this.desc = desc;
        this.identification = identification;
    }

    public static String getDesc(Integer identification){
        for(IdentificationEnum identificationEnum : IdentificationEnum.values()){
            if(identificationEnum.getIdentification().equals(identification)){
                return identificationEnum.getDesc();
            }
        }
        return "";
    }

}
