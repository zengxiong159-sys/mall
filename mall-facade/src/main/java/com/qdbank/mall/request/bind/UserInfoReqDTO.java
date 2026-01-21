package com.qdbank.mall.request.bind;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserInfoReqDTO {
    @NotBlank(message = "手机号码不能为空")
    @NotNull(message = "手机号码不能为空")
    @Length(max = 11,min = 11,message = "手机号码格式有误")
    private String mobile;
}
