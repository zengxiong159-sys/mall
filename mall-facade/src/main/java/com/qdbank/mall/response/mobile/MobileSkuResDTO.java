package com.qdbank.mall.response.mobile;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName MobileSkuResDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/5/7 13:57
 * @Version 1.0
 **/
@Data
public class MobileSkuResDTO {

    /**
     * 商品规格id
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "商品规格id")
    private Long mobileSkuId;

    /**
     * 商品售价
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价")
    private BigDecimal productPrice;

    /**
     * 商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;

    /**
     * 规格图片URL
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规格图片URL")
    private String skuPicUrl;

    /**
     * 商城产品id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商城产品id")
    private Long productId;

    /**
     * 网信产品id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "网信产品id")
    private String supplyProductId;

    /**
     * 产品类型 1：话费 2：
     流量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "产品类型1：话费2：流量")
    private String supplyType;

    /**
     * 产品大小，type=1 时单
     位为元，type=2 时单位 为 M
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "产品大小，type=1时单位为元，type=2时单位为M")
    private String supplyProductSize;

    /**
     * 运营商类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运营商类型")
    private String supplySupplierType;

    /**
     * 运营商类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "供应商价格")
    private BigDecimal supplyPrice;




}
