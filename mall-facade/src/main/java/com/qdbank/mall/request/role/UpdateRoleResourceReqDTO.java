package com.qdbank.mall.request.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName UpdateRoleResourceReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/23 10:08
 * @Version 1.0
 **/
@Data
public class UpdateRoleResourceReqDTO {
    @NotNull
    @ApiModelProperty(value = "角色ID",required = true)
    private Long roleId;
    @ApiModelProperty(value = "资源ID集合",required = true)
    private String resourceIds;
}
