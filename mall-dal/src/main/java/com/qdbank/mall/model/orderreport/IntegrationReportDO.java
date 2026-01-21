package com.qdbank.mall.model.orderreport;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName IntegrationReportDO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/21 10:01
 * @Version 1.0
 **/
@Data
public class IntegrationReportDO {
    @ApiModelProperty(value = "日期")
    private Date paymentTime;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "商户编号")
    private Long   merchantNo;
    @ApiModelProperty(value = "需结算积分")
    private BigDecimal needPayIntegration;
    @ApiModelProperty(value = "不需要结算积分")
    private BigDecimal   notNeedPayIntegration;
    @ApiModelProperty(value = "积分兑换券")
    private BigDecimal   integrationCoupon;
    @ApiModelProperty(value = "积分汇总")
    private BigDecimal totalIntegration;
    @ApiModelProperty(value = "需要结算积分汇总")
    private BigDecimal needPayTotalIntegration;
}
