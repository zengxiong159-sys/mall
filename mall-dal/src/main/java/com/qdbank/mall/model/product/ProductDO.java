package com.qdbank.mall.model.product;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDO extends BaseDO implements Serializable {
    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品编号")
    private Long productId;

    /**
     * 商户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号")
    private Long merchantNo;

    /**
     * 商户姓名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户姓名")
    private String merchantName;

    /**
     * 商品主图URL
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品主图URL")
    private String mailPicUrl;

    /**
     * 一级分类编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "一级分类编号")
    private Long categoryId1;

    /**
     * 二级分类编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "二级分类编号")
    private Long categoryId2;

    /**
     * 三级分类编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "三级分类编号")
    private Long categoryId3;

    /**
     * 四级分类编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "四级分类编号")
    private Long categoryId4;

    /**
     * 上架状态 0 待入库 1 已入库 2 未上架 3 已上架
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "上架状态 0待入库 1已入库 2已上架")
    private Long publishStatus;

    /**
     * 商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;

    /**
     * 商品描述
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品描述")
    private String productDescription;

    /**
     * 运费模板编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运费模板编号")
    private Long freightTemplateId;

    /**
     * 商品详情
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品详情")
    private String productDetail;
    @ApiModelProperty(value = "商品标识：默认0:常规商品 1:达标专属礼")
    private Integer  identification;
    /**
     * 最多抵扣积分量
     */
    @ApiModelProperty(value = "最多抵扣积分量")
    private Long maxIntegralDeduct;
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "商品属性： 0 常规商品 1 分期商品")
    private Integer productAttr;
    @ApiModelProperty(value = "商品优先级")
    private Long productLevel;
}