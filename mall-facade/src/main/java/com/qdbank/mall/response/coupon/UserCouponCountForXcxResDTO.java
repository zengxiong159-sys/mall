package com.qdbank.mall.response.coupon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserCouponCountForXcxResDTO implements Serializable {


    @ApiModelProperty(value = "待使用优惠券数量")
    private Integer couponNum;











}