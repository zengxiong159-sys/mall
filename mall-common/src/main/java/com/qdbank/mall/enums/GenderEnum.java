package com.qdbank.mall.enums;

import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.exception.ApiException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnum {
    MAN("M","男"),
    WOMAN("F","女");
    private String gender;
    private String desc;
    public static String getGender(String desc){
        for(GenderEnum genderEnum : GenderEnum.values()){
            if(genderEnum.getDesc().equals(desc)) return genderEnum.getGender();
        }
        throw new ApiException(ResultCode.GENDER_ERROR);
    }

    public static String getDesc(String gender){
        for(GenderEnum genderEnum : GenderEnum.values()){
            if(genderEnum.getGender().equals(gender)) return genderEnum.getDesc();
        }
        throw new ApiException(ResultCode.GENDER_ERROR);
    }
}
