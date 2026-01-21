package com.qdbank.mall.response.coupon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author zengxiong
 * @Description 优惠券导出请求DTO
 * @Date 2021/8/20 17:34
 */
@Data
public class CouponExportReqDTO implements Serializable {
    private static final long serialVersionUID = -5800383961472784913L;

    @NotNull
    @ApiModelProperty(value = "优惠券类型")
    private Long couponType;

    @NotEmpty
    @ApiModelProperty(value = "券批次号")
    private String batchNo;
}
