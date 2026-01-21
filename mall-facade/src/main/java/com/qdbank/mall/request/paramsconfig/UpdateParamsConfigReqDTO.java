package com.qdbank.mall.request.paramsconfig;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UpdateParamsConfigReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/1 14:25
 * @Version 1.0
 **/
@Data
public class UpdateParamsConfigReqDTO {
    @ApiModelProperty(value = "参数编号")
    private Long id;
    /**
     * 参数名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "参数名称")
    private String paramName;

    /**
     * 参数值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "参数值")
    private String paramValue;
}
