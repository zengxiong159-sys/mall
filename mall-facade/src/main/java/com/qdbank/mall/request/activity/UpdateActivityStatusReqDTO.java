package com.qdbank.mall.request.activity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UpdateActivityStatusReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/23 16:43
 * @Version 1.0
 **/
@Data
public class UpdateActivityStatusReqDTO {
    @NotNull
    @ApiModelProperty(value = "活动编号",required = true)
    private Long id;
    @NotNull
    @ApiModelProperty(value = "活动状态 0 停用 1 启用",required = true)
    private Long status;
}
