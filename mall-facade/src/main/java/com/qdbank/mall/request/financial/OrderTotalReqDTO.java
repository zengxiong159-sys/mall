package com.qdbank.mall.request.financial;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName OrderTotalDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/15 10:35
 * @Version 1.0
 **/
@Data
public class OrderTotalReqDTO {
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "商户编号")
    private String merchantNo;
    @ApiModelProperty("订单类型")
    private String orderType;
    @ApiModelProperty(value = "订单数量")
    private String orderCount;
    @ApiModelProperty(value = "订单是付款")
    private String payAmount;
    @ApiModelProperty(value = "商品支付积分汇总")
    private String integrationCount;
    @ApiModelProperty(value = "积分券汇总")
    private String couponCount;
    @ApiModelProperty(value = "指定商品券汇总")
    private String productCouponCount;
    @ApiModelProperty(value = "现金")
    private String orderCash;
    @ApiModelProperty(value = "运费")
    private String freightAmount;
    @ApiModelProperty(value = "运费加现金")
    private String cashAndFreight;
}
