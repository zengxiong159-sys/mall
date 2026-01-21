package com.qdbank.mall.response.productcategory;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProductCategoryResDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "分类编号")
    private Long id;
    @ApiModelProperty(value = "父级编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    @ApiModelProperty(value = "分类名称")
    private String categoryName;
    @ApiModelProperty(value = "分类级别：0 一级；1 二级 2 三级 3 四级")
    private Long categoryLevel;
    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    /**
     * 更新人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新人")
    private String updatedBy;

    @ApiModelProperty("子级分类")
    private List<ProductCategoryResDTO> children;
}
