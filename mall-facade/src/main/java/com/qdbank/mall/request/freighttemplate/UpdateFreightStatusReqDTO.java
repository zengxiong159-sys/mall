package com.qdbank.mall.request.freighttemplate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UpdateFreightStatusReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/23 15:28
 * @Version 1.0
 **/
@Data
public class UpdateFreightStatusReqDTO {
    @NotNull
    @ApiModelProperty(value = "运费模板编号",required = true)
    private Long freightTemplateId;
    @NotNull
    @ApiModelProperty(value = "模板状态：0 启用 1 禁用",required = true)
    private Long status;
}
