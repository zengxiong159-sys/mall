package com.qdbank.mall.request.product;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;

/**
 * @ClassName ProductReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/14 11:09
 * @Version 1.0
 **/
@Data
public class ProductLikeQueryReqDTO extends PageParams {
    /**
     * 主键
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
     * 商户名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    /**
     * 一级分类
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "一级分类")
    private Long categoryId1;

    /**
     * 二级分类
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "二级分类")
    private Long categoryId2;

    /**
     * 三级分类
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "三级分类")
    private Long categoryId3;

    /**
     * 四级分类
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "四级分类")
    private Long categoryId4;

    /**
     * 商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;

    /**
     * 上架状态 0 待入库 1 已入库 2 未上架 3 已上架
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "上架状态 0待入库 1已入库 2已上架")
    private Long publishStatus;
    /**
     * 商品售价区间最小值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价区间最小值")
    private BigDecimal productCashMin;
    /**
     * 商品售价区间最大值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价区间最大值")
    private BigDecimal productCashMax;

    @ApiModelProperty(value = "规格编号")
    private Long productSkuId;
    @ApiModelProperty(value = "所属系统",hidden = true)
    private String system;
    private String mulStatus;
    @ApiModelProperty(value = "商品标识：默认0:常规商品 1:达标专属礼")
    private Integer  identification;
}
