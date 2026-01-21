package com.qdbank.mall.response.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfoResDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "用户编号")
    private Long id;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "备注信息")
    private String note;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "上一次登录时间")
    private Date loginTime;
    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用")
    private Long status;
    @ApiModelProperty(value = "用户手机号", required = true)
    private String mobile;
    @ApiModelProperty(value = "所在部门")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long deptno;
    @ApiModelProperty(value = "用户姓名", required = true)
    private String nickName;
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createdTime;
    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;
    @ApiModelProperty(value = "创建人", required = true)
    private String createdBy;
    @ApiModelProperty(value = "更新人", required = true)
    private String updatedBy;
    @ApiModelProperty(value = "用户角色", required = true)
    private String role;
    @ApiModelProperty(value = "账号", required = true)
    private String username;
    @ApiModelProperty(value = "部门名称")
    private String deptname;
    @ApiModelProperty(value = "角色名称")
    private String roleNames;
    @ApiModelProperty(value = "密码")
    private String password;

}
