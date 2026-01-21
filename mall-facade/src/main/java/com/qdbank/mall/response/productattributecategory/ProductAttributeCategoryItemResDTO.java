package com.qdbank.mall.response.productattributecategory;

import com.qdbank.mall.response.productattribute.ProductAttributeResDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class ProductAttributeCategoryItemResDTO extends ProductAttributeCategoryResDTO {
    @ApiModelProperty(value = "商品属性列表")
    private List<ProductAttributeResDTO> productAttributeList;
}
