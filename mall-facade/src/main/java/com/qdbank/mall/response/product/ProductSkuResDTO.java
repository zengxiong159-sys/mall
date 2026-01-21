package com.qdbank.mall.response.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.qdbank.mall.response.skustock.SkustockResDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName ProductSkuResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/6 22:53
 * @Version 1.0
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSkuResDTO extends ProductCategoryID{
    /**
     * 主键
     * 主键
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long productId;

    /**
     * 商户编号
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
     * 商品主图URL
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品主图URL")
    private String mailPicUrl;


    /**
     * 上架状态 0 待入库 1 已入库 2 未上架 3 已上架
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "上架状态 0 待入库 1 已入库 2已上架")
    private Long publishStatus;

    /**
     * 商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;

    /**
     * 商品分类名称
     *
     * @mbg.generated
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品分类名称")
    private String productCategoryName;

    /**
     * 商品描述
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品描述")
    private String productDescription;

    @ApiModelProperty(value = "商品详情")
    private String productDetail;

    @ApiModelProperty(value = "规格属性")
    List<SkustockResDTO> skustocks;

    @ApiModelProperty(value = "最低折算价")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal minExchangePrice;
    @ApiModelProperty(value = "商品优先级")
    private Long productLevel;
}
