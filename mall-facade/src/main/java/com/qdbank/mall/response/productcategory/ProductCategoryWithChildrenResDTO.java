package com.qdbank.mall.response.productcategory;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class ProductCategoryWithChildrenResDTO extends ProductCategoryResDTO{
    @ApiModelProperty("子级分类")
    private List<ProductCategoryResDTO> children;
}
