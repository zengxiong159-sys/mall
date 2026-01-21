package com.qdbank.mall.request.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName UpdateRoleMenuReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/23 9:01
 * @Version 1.0
 **/
@Data
public class UpdateRoleMenuReqDTO {
    @NotNull
    @ApiModelProperty(value = "角色ID",required = true)
    private Long roleId;
    @ApiModelProperty(value = "菜单ID集合")
    private String menuIds;
}
