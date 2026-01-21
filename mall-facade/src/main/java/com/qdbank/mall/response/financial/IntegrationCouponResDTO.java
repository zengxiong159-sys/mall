package com.qdbank.mall.response.financial;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @ClassName IntegrationCouponResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/21 17:28
 * @Version 1.0
 **/
@Data
public class IntegrationCouponResDTO {
    @ApiModelProperty(value = "日期")
    private String paymentTime;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "商户编号")
    private String   merchantNo;
    @ApiModelProperty(value = "需结算积分")
    private String needPayIntegration;
    @ApiModelProperty(value = "不需要结算积分")
    private String   notNeedPayIntegration;
    @ApiModelProperty(value = "积分兑换券")
    private String   integrationCoupon;
    @ApiModelProperty(value = "需要结算积分汇总")
    private String needPayTotalIntegration;
    @ApiModelProperty(value = "积分汇总")
    private String totalIntegration;
}
