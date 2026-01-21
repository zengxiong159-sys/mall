package com.qdbank.mall.response.integral;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName IntegralBalanceResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/3/24 16:49
 * @Version 1.0
 **/
@Data
public class IntegralBalanceResDTO {
    @ApiModelProperty(value = "积分余额")
    private String pointBal;
    @ApiModelProperty(value = "积分抵扣标识 0不展示 1展示")
    private Integer hideFlag;
    @ApiModelProperty(value = "积分签到标识 0不支持签到 1可以签到")
    private Integer signFlag;
    @ApiModelProperty(value = "默认积分抵扣金额")
    private BigDecimal defaultIntegralCash;
}
