package com.qdbank.mall.response.productattributevalue;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ProductAttributeResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2020/12/12 10:45
 * @Version 1.0
 **/
@Data
public class ProductAttributeResDTO {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "商品编号")
    private Long productId;

    /**
     * 商品属性编号
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
