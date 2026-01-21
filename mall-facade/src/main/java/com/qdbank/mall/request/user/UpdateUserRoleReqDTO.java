package com.qdbank.mall.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName UpdateUserRoleReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/22 11:15
 * @Version 1.0
 **/
@Data
public class UpdateUserRoleReqDTO {
    @NotNull
    @ApiModelProperty(value = "用户编号")
    Long id;
    @ApiModelProperty(value = "角色列表ID")
    String roleIds;
}
