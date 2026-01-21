package com.qdbank.mall.request.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UpdateRoleReqDTO implements Serializable {
    @NotNull
    @ApiModelProperty(value = "角色ID")
    Long id;
    @Length(max = 20)
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @Length(max = 1)
    @ApiModelProperty(value = "角色状态：0->禁用；1->启用")
    private Long status;
    @Length(max = 50)
    @ApiModelProperty(value = "角色描述")
    private String roleDescription;
}
