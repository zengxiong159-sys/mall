package com.qdbank.mall.response.order.orderdetail;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.qdbank.mall.response.skustock.SkustockResDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName OrderProductInfoResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/28 9:53
 * @Version 1.0
 * 订单中商品信息
 **/
@Data
public class OrderProductInfoResDTO {

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
     * 商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;

    /**
     * 商品售价中现金金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价中现金金额")
    private BigDecimal productCash;

    /**
     * 商品售价中积分值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价中积分值")
    private Long productIntegration;
    /**
     * 商品属性:JSON格式
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品属性:JSON格式")
    private String productSpData;

    @ApiModelProperty(value = "商品数量")
    private Long productCount;
}
