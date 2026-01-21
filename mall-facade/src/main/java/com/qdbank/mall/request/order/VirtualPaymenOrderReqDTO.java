package com.qdbank.mall.request.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 实物支付请求
 * @ClassName PaymentInKindOrderReqDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/1/23 12:05
 * @Version 1.0
 **/
@Data
public class VirtualPaymenOrderReqDTO extends CommonOrderReqDTO{




    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "充值账号")
    private String accNo;
}
