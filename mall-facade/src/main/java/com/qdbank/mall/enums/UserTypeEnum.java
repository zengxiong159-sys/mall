package com.qdbank.mall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  UserTypeEnum {
    WHITE_USER("0","白名单用户"),
    BANK_USER("1","行员");
    private String userCode;
    private String userDesc;
}
