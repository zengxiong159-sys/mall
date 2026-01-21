package com.qdbank.mall.request.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class RoleReqDTO implements Serializable {
    @Length(max = 20,min = 1)
    @NotBlank
    @ApiModelProperty(value = "角色名称", required = true)
    private String roleName;
    @NotNull
    @ApiModelProperty(value = "角色状态：0->禁用；1->启用", required = true)
    private Long status;
    @Length(max = 50)
    @NotBlank
    @ApiModelProperty(value = "角色描述", required = true)
    private String roleDescription;

}
