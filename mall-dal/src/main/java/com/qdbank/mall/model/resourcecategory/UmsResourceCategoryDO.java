package com.qdbank.mall.model.resourcecategory;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class UmsResourceCategoryDO extends BaseDO implements Serializable {
    private Long id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "排序")
    private Integer sort;
    private static final long serialVersionUID = 1L;
}