package com.qdbank.mall.response.coupon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserCouponCountResDTO implements Serializable {


    @ApiModelProperty(value = "待使用优惠券数量")
    private Integer prepareUseCount;

    @ApiModelProperty(value = "已使用优惠券数量")
    private Integer hasUseCount;


    @ApiModelProperty(value = "已过期优惠券数量")
    private Integer expireCount;









}