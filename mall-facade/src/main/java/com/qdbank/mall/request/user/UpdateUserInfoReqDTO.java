package com.qdbank.mall.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UpdateUserInfoReqDTO {
    @NotNull
    @ApiModelProperty(value = "用户编号",required = true)
    Long id;
    @ApiModelProperty(value = "邮箱")
    private String username;
    @Length(max = 10)
    @ApiModelProperty(value = "用户姓名", required = true)
    private String nickName;
    @Length(max = 11,min = 11,message = "手机号长度必须11位")
    @ApiModelProperty(value = "用户手机号", required = true)
    @Pattern(regexp = "^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$")
    private String mobile;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @ApiModelProperty(value = "真实邮箱")
    private String email;
    @Length(max = 100)
    @ApiModelProperty(value = "备注")
    private String note;
    @ApiModelProperty(value = "用户状态")
    private Long status;
    @ApiModelProperty(value = "所在部门")
    private String deptno;
}
