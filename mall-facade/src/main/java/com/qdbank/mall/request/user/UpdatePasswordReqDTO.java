package com.qdbank.mall.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @ClassName UpdatePasswordReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/19 11:53
 * @Version 1.0
 **/
@Data
public class UpdatePasswordReqDTO {
    @Email
    @NotBlank
    @Length(max = 50)
    @ApiModelProperty(value = "邮箱")
    private String username;
    @Length(max = 50)
    @NotBlank
    @ApiModelProperty(value = "用户原密码", required = true)
    private String oldPassword;
    @Length(max = 50)
    @NotBlank
    @ApiModelProperty(value = "用户新密码", required = true)
    private String newPassword;
}
