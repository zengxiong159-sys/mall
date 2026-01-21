package com.qdbank.mall.request.financial;

import cn.hutool.core.date.DatePattern;
import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName FinancialReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/13 16:28
 * @Version 1.0
 **/
@Data
public class FinancialReqDTO extends PageParams implements Serializable {
    @NotNull
    @ApiModelProperty(value = "开始日期",required = true)
    @DateTimeFormat(pattern= DatePattern.NORM_DATE_PATTERN)
    private Date startDate;
    @NotNull
    @ApiModelProperty(value = "结束日期",required = true)
    @DateTimeFormat(pattern=DatePattern.NORM_DATE_PATTERN)
    private Date endDate;
    @ApiModelProperty(value = "商户编号")
    private String merchantNo;
    @NotNull
    @ApiModelProperty(value = "交易类型 2 商城交易汇总 3 商城交易明细 4 商城积分结算月汇总表 5 营销费用月汇总表",required = true)
    private Long tradeType;
}
