package com.qdbank.mall.model.orderreport;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * @ClassName IntegrationMerchantInfoDO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/22 14:57
 * @Version 1.0
 **/
@Data
public class TradeMerchantInfoDO {
    @ApiModelProperty(value = "交易日期")
    private String payTime;
    @ApiModelProperty(value = "商户号")
    private Long merchantNo;
    private String batchNo;

    @ApiModelProperty(value = "开始日期",required = true)
    private Date startDate;
    @ApiModelProperty(value = "结束日期",required = true)
    private Date endDate;

    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private Long couponType;

}
