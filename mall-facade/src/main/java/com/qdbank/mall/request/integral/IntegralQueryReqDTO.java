package com.qdbank.mall.request.integral;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class IntegralQueryReqDTO extends IntegralBalanceReqDTO{
    @ApiModelProperty(value = "小程序返回sessionKey")
    private String sessionKey;
}
