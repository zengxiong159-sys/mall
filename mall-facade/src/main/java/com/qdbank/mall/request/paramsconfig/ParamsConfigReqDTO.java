package com.qdbank.mall.request.paramsconfig;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ParamsConfigReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/1 11:52
 * @Version 1.0
 **/
@Data
public class ParamsConfigReqDTO {

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

    /**
     * 参数类型：0 积分值比例配置 1 自动确认收货失效配置 2 退款申请有效期配置 3 物流公司配置
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "参数类型：0积分值比例配置1自动确认收货失效配置2退款申请有效期配置3物流公司配置")
    private Long paramType;

    /**
     * 参数描述
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "参数描述")
    private String paramDescription;

}
