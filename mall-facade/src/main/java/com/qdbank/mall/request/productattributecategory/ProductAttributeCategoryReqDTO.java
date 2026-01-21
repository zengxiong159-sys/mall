package com.qdbank.mall.request.productattributecategory;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductAttributeCategoryReqDTO {
    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "属性数量")
    private Integer attributeCount;

    @ApiModelProperty(value = "参数数量")
    private Integer paramCount;

}
