package com.qdbank.mall.model.productcategory;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class ProductcategoryDO extends BaseDO implements Serializable {
    /**
     * 主键ID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 分类的编号：0表示一级分类
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "分类的编号：0表示一级分类")
    private Long parentId;

    /**
     * 分类名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    /**
     * 分类级别：0->1级；1->2级；2->3级
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "分类级别：0->1级；1->2级；2->3级")
    private Long categoryLevel;

    /**
     * 排序
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "排序")
    private Long categorySort;

    /**
     * 类别 0 ->实物商品 ； 1->虚拟商品
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "类别0->实物商品；1->虚拟商品")
    private Long categoryType;


    private static final long serialVersionUID = 1L;


}