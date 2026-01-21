package com.qdbank.mall.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class UserLoginReqDTO implements Serializable {
    @Length(max = 10)
    @NotNull
    @Email
    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @NotNull
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
