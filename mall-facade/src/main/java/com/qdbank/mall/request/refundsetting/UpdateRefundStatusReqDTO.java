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
public class UpdateRefundStatusReqDTO {
    @NotNull
    @ApiModelProperty(value = "退款设置编号",required = true)
    private Long id;
    @ApiModelProperty(value = "退款设置状态",required = true)
    private Long status;
}
