package com.qdbank.mall.request.refundsetting;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UpdateRefundReasonReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/23 14:50
 * @Version 1.0
 **/
@Data
public class UpdateRefundReasonReqDTO {
    @NotNull
    @ApiModelProperty(value = "退款设置编号",required = true)
    private Long id;
    @NotNull
    @ApiModelProperty(value = "退款设置原因",required = true)
    private String refundReason;
    @ApiModelProperty(value = "0 未开启 1 开启")
    private Long status;
}
