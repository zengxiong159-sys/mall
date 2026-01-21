package com.qdbank.mall.model.productattributevalue;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductAttributeValueDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

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

    private static final long serialVersionUID = 1L;
}