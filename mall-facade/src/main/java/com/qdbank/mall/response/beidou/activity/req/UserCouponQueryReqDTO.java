package com.qdbank.mall.response.beidou.activity.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author zengxiong
 * @Description 用户优惠券查询请求DTO
 * @Date 2021/12/1 9:34
 */
@Data
public class UserCouponQueryReqDTO {

    /**
     * 通联核心客户号
     */
    @NotEmpty(message = "核心客户号不允许为空")
    private String custNo;

    /**
     * 券id
     */
    @NotNull
    @JsonSerialize(using = ToStringSerializer.class)
    private Long couponId;

}
