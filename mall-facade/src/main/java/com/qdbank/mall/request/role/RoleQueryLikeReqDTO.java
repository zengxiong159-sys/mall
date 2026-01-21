package com.qdbank.mall.request.role;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class RoleQueryLikeReqDTO extends PageParams implements Serializable {
    @ApiModelProperty(value = "角色名称", required = true)
    private String roleName;
    @ApiModelProperty(value = "角色编号", required = true)
    private Long id;

}
