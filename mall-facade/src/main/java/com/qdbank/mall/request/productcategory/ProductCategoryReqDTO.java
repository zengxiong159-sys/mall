package com.qdbank.mall.request.productcategory;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Data
public class ProductCategoryReqDTO implements Serializable {
    @ApiModelProperty("父分类的编号")
    private Long parentId;
    @NotNull
    @ApiModelProperty(value = "类目名称",required = true)
    private String categoryName;

    @ApiModelProperty(value="类别->0实物 1 ->虚拟")
    private Long categoryType;
    /**
     * 分类级别：0 一级 1 二级 2 三级 3 四级
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "分类级别：0->1级；1->2级；2->3级")
    private Long categoryLevel;
}
