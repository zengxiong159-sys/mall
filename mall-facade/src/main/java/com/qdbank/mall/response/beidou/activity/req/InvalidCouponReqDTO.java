package com.qdbank.mall.response.beidou.activity.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author zengxiong
 * @Description 优惠券失效请求DTO
 * @Date 2021/12/13 11:04
 */
@Data
public class InvalidCouponReqDTO {

    /**
     * 券id
     */
    @NotNull
    private Long couponId;

}
