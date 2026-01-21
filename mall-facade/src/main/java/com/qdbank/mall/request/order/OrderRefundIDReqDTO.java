package com.qdbank.mall.request.order;

import com.qdbank.mall.annotation.LockKey;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName CommonReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/23 18:49
 * @Version 1.0
 **/
@Data
public class OrderRefundIDReqDTO {

    @ApiModelProperty(value = "退款流水号")
    private String refundSerialNo;

    @ApiModelProperty(value = "订单号")
    private String orderSn;

}
