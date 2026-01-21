package com.qdbank.mall.request.resourcecategory;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ResourceCategoryReqDTO implements Serializable {
    @ApiModelProperty(value = "资源分类编号")
    Long id;
    @ApiModelProperty(value = "分类名称")
    private String name;
    @ApiModelProperty(value = "排序")
    private Integer sort = 0;
}
