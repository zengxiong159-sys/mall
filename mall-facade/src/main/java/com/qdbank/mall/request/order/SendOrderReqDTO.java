package com.qdbank.mall.request.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @ClassName SendOrderReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/23 12:05
 * @Version 1.0
 **/
@Data
public class SendOrderReqDTO {
    @NotNull
    @ApiModelProperty(value = "订单主键")
    private Long orderId;
    @NotNull
    @Length(max = 50)
    @ApiModelProperty(value = "物流公司名称",required = true)
    private String deliveryCompanyName;
    @NotNull
    @Length(max = 50)
    @ApiModelProperty(value = "物流单号",required = true)
    private String deliverySn;
}
