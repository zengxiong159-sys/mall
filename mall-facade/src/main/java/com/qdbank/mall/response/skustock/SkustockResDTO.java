package com.qdbank.mall.response.skustock;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName SkustockResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/14 16:05
 * @Version 1.0
 **/
@Data
public class SkustockResDTO {
    /**
     * 规格编号
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "规格编号")
    private Long productSkuId;

    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "商品编号")
    private Long productId;

    /**
     * 市场价
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "市场价")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal marketPrice;

    /**
     * 建议售价
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "建议售价")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal advicePrice;

    /**
     * 商品售价
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal productPrice;

    /**
     * 商品售价中现金金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价中现金金额")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal productCash;

    /**
     * 商品售价中积分值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价中积分值")
    private Long productIntegration;

    /**
     * 商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;

    /**
     * 限购开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "限购开始时间")
    private Date promotionStartTime;

    /**
     * 限购结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "限购结束时间")
    private Date promotionEndTime;

    /**
     * 每人限购数量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "每人限购数量")
    private Long promotionPerLimit;

    /**
     * 积分结算标识：0 不结算 1 需要结算
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分结算标识：0不结算1需要结算")
    private Long integrationPayFlag;

    /**
     * 商品库存
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品库存")
    private Long productStock;

    /**
     * 规格图片URL
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规格图片URL")
    private String skuPicUrl;
    @ApiModelProperty("图片名称")
    private String skuPicName;
    /**
     * 商品锁定库存(已下单商品数量)
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品锁定库存(已下单商品数量)")
    private Long productLockStock;

    /**
     * 商品属性:JSON格式
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品属性:JSON格式")
    private String productSpData;


    @ApiModelProperty(value = "销量")
    private Long salCount;
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
     * 修改人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;


    @ApiModelProperty(value = "剩余时间")
    private Long leftTime;

    @ApiModelProperty(value = "剩余限购数量")
    private Long leftLimitCount;


    @ApiModelProperty(value = "限购标识：0 否 1是")
    private String limitCountFlag;

    @ApiModelProperty(value = "规格属性")
    private Map skuParams;

    /**
     * 状态标识：0上架 1下架
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态标识：0上架 1下架")
    private Long status;

    /**
     * 折算价
     */
    @ApiModelProperty(value = "折算价")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal exchangePrice;

    /**
     * 最多抵扣积分量
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "最多抵扣积分量")
    private Long maxIntegralDeduct;

    /**
     * 最小抵扣积分量
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "最小抵扣积分量")
    private Long minIntegralDeduct;

    @ApiModelProperty(value = "优惠金额")
    private BigDecimal discountAmount;
}
