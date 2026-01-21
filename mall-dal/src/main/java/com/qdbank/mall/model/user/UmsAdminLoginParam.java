package com.qdbank.mall.model.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 用户登录参数
 * Created by ningyuehuai on 2020/10/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsAdminLoginParam {
    @NotNull
    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @NotNull
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
