package com.qdbank.mall.request.coupon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author zengxiong
 * @Description 券商品详情查询请求DTO
 * @Date 2021/8/12 15:47
 */
@Data
public class CouponDetailQueryReqDTO implements Serializable {
    private static final long serialVersionUID = 591812211048148245L;

    @NotNull
    @ApiModelProperty(value = "券商品id")
    private Long couponId;

    @NotNull
    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private Long couponType;
}
