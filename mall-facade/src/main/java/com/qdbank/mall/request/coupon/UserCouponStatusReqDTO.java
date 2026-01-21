package com.qdbank.mall.request.coupon;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserCouponStatusReqDTO implements Serializable {
    private static final long serialVersionUID = 3047369014841209793L;
    /**
     * 用户优惠券编号
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "用户优惠券编号")
    private Long userCouponId;
}