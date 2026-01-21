package com.qdbank.mall.request.coupon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author zengxiong
 * @Description 更新用户优惠券使用状态请求参数
 * @Date 2021/8/31 16:07
 */
@Data
public class UpdateUserCouponStatusReqDTO implements Serializable {
    private static final long serialVersionUID = 3943181199413689874L;

    /**
     * 用户优惠券主键id
     */
    @ApiModelProperty(value = "用户优惠券主键id")
    @NotNull
    private Long userCouponId;

    /**
     * 用户使用券状态:0 待使用 1已使用 2已过期 3已作废
     */
    @ApiModelProperty(value = "用户使用券状态:0 待使用 1已使用 2已过期 3已作废")
    @NotNull
    private Long status;
}
