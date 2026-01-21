package com.qdbank.mall.response.beidou.activity.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author zengxiong
 * @Description 查询优惠券是否已失效
 * @Date 2021/12/13 11:18
 */
@Data
public class CouponInvalidatedReqDTO {

    /**
     * 券id
     */
    @NotNull
    private Long couponId;

}
