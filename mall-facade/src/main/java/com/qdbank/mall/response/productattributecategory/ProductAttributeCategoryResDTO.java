package com.qdbank.mall.response.productattributecategory;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductAttributeCategoryResDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "属性分类编号")
    private Long id;
    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "属性数量")
    private Long attributeCount;

    @ApiModelProperty(value = "参数数量")
    private Long paramCount;

}
