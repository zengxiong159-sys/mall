package com.qdbank.mall.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UpdateUserStatusReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/22 11:10
 * @Version 1.0
 **/
@Data
public class UpdateUserStatusReqDTO {
    @NotNull
    @ApiModelProperty(value = "用户编号")
    private Long id;
    @NotNull
    @ApiModelProperty(value = "用户状态")
    private Long status;
}
