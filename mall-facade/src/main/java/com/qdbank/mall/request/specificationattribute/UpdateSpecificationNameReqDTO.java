package com.qdbank.mall.request.specificationattribute;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UpdateSpecificationNameReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/23 16:22
 * @Version 1.0
 **/
@Data
public class UpdateSpecificationNameReqDTO {
    @NotNull
    @ApiModelProperty(value = "规格编号",required = true)
    private Long id;
    @NotNull
    @ApiModelProperty(value = "规格名称",required = true)
    private String name;

    /**
     * 商户编号 商户表主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号 商户表主键")
    private Long merchantNo;
}
