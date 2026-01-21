package com.qdbank.mall.response.financial;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @ClassName ProductCouponResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/22 19:32
 * @Version 1.0
 **/
@Data
public class ProductCouponResDTO {
    @ApiModelProperty(value = "日期")
    private String paymentTime;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "商户编号")
    private String   merchantNo;
    @ApiModelProperty(value = "指定商品免费兑换券金额")
    private String productCoupon;
    @ApiModelProperty(value = "指定商品免费兑换券批次号")
    private String batchNo;
    @ApiModelProperty(value = "商品券名称")
    private String couponName;
    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private Long couponType;
    @ApiModelProperty(value = "优惠券名称")
    private String couponTypeDesc;
}
