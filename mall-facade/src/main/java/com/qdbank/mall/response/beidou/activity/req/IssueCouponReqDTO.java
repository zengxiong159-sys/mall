package com.qdbank.mall.response.beidou.activity.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author zengxiong
 * @Description 发券接口请求DTO
 * @Date 2021/12/1 13:54
 */
@Data
public class IssueCouponReqDTO {

    /**
     * 券id
     */
    @NotNull
    private Long couponId;

    /**
     * 通联核心客户号
     */
    @NotEmpty(message = "核心客户号不允许为空")
    private String custNo;

}
