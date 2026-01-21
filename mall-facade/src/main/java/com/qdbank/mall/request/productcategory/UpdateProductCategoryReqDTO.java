package com.qdbank.mall.request.productcategory;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateProductCategoryReqDTO {
    @NotNull
    @ApiModelProperty(value = "类目编号",required = true)
    Long id;
    @ApiModelProperty("类目名称")
    private String categoryName;
}
