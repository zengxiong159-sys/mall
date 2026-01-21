package com.qdbank.mall.response.financial;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName OrderTotalResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/15 16:51
 * @Version 1.0
 **/
@Data
public class OrderTotalResDTO {
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "商户编号")
    private String merchantNo;
    @ApiModelProperty("订单类型")
    private String orderType;
    @ApiModelProperty(value = "订单数量")
    private String orderCount = "0";
    @ApiModelProperty(value = "商品售价折算价")
    private String productPrice = "0";
    @ApiModelProperty(value = "商品支付积分汇总")
    private String integrationCount = "0";
    @ApiModelProperty(value = "积分券汇总")
    private String couponCount = "0";
    @ApiModelProperty(value = "指定商品券汇总")
    private String productCouponCount = "0";
    @ApiModelProperty(value = "现金")
    private String orderCash = "0";
    @ApiModelProperty(value = "运费")
    private String freightAmount = "0";
    @ApiModelProperty(value = "运费加现金")
    private String cashFreightAmount = "0";
    @ApiModelProperty(value = "优惠券类型")
    private String couponType;
    @ApiModelProperty(value = "优惠券类型描述")
    private String couponTypeDesc;
    @ApiModelProperty(value = "发放形式")
    private String distributeWay;
    @ApiModelProperty(value = "优惠金额")
    private String discountAmount;
}
