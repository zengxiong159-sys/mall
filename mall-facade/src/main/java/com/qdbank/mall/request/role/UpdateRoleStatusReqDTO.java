package com.qdbank.mall.request.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UpdateRoleStatusReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/22 19:21
 * @Version 1.0
 **/
@Data
public class UpdateRoleStatusReqDTO {
    @NotNull
    @ApiModelProperty(value = "角色ID",required = true)
    private  Long id;
    @NotNull
    @ApiModelProperty(value = "状态 0 禁用 1 启用",required = true)
    private Long status;
}
