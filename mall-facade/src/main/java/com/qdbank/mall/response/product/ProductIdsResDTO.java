package com.qdbank.mall.response.product;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName ProductIdsResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/7/25 8:01
 * @Version 1.0
 **/
@Data
public class ProductIdsResDTO {
    /**
     * 商品主图URL
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品主图URL")
    private String mailPicUrl;

    /**
     * 商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;
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
    private BigDecimal productPrice ;

    @ApiModelProperty(value = "商品属性： 0 常规商品 1 分期商品")
    private Integer productAttr;

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


}
