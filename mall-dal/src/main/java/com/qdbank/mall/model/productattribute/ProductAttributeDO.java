package com.qdbank.mall.model.productattribute;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class ProductAttributeDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 属性分类主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "属性分类主键")
    private Long productAttributeCategoryId;

    /**
     * 属性名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "属性名称")
    private String name;

    /**
     * 属性选择类型：0->唯一；1->单选；2->多选
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "属性选择类型：0->唯一；1->单选；2->多选")
    private Short selectType;

    /**
     * 属性录入方式：0->手工录入；1->从列表中选取
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "属性录入方式：0->手工录入；1->从列表中选取")
    private Short inputType;

    /**
     * 可选值列表，以逗号隔开
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "可选值列表，以逗号隔开")
    private String inputList;

    /**
     * 排序字段：最高的可以单独上传图片
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "排序字段：最高的可以单独上传图片")
    private Long sort;

    /**
     * 分类筛选样式：1->普通；1->颜色
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "分类筛选样式：1->普通；1->颜色")
    private Long filterType;

    /**
     * 检索类型；0->不需要进行检索；1->关键字检索；2->范围检索
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "检索类型；0->不需要进行检索；1->关键字检索；2->范围检索")
    private Long searchType;

    /**
     * 相同属性产品是否关联；0->不关联；1->关联
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "相同属性产品是否关联；0->不关联；1->关联")
    private Long relatedStatus;

    /**
     * 是否支持手动新增；0->不支持；1->支持
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "是否支持手动新增；0->不支持；1->支持")
    private Long handAddStatus;

    /**
     * 属性的类型；0->规格；1->参数
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "属性的类型；0->规格；1->参数")
    private Long type;

    private static final long serialVersionUID = 1L;
}