package com.qdbank.mall.request.productattributevalue;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ProductAttributeValueReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2020/12/12 10:44
 * @Version 1.0
 **/
@Data
public class ProductAttributeValueReqDTO {
    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品编号")
    private Long productId;

    /**
     * 商品属性编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品属性编号")
    private Long productAttributeId;

    /**
     * 商品具体属性值-手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品具体属性值-手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开")
    private String value;
}
