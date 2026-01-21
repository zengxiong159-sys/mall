package com.qdbank.mall.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 *用户信息
 */
@Getter
@Setter
public class UserInfoReqDTO implements Serializable {
    @Email
    @NotBlank
    @Length(max = 50)
    @ApiModelProperty(value = "邮箱")
    private String username;
    @Length(max = 10)
    @NotBlank
    @ApiModelProperty(value = "用户姓名", required = true)
    private String nickName;
    @Length(max = 11,min = 11,message = "手机号长度必须11位")
    @NotBlank
    @ApiModelProperty(value = "用户手机号", required = true)
    @Pattern(regexp = "^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$")
    private String mobile;
    @ApiModelProperty(value = "真实邮箱")
    private String email;
    @Length(max = 100)
    @ApiModelProperty(value = "备注")
    private String note;
    @NotNull
    @ApiModelProperty(value = "所在部门")
    private Long deptno;
    @NotNull
    @ApiModelProperty(value = "用户状态 0 禁用 1 启用")
    private Long status;
}
