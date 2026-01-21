package com.qdbank.mall.model.orderreport;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName OrderExportDO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/13 15:33
 * @Version 1.0
 **/
@Data
public class OrderReportExportDO {
    @ApiModelProperty(value = "开始日期",required = true)
    private Date startDate;
    @ApiModelProperty(value = "结束日期",required = true)
    private Date endDate;
    @ApiModelProperty(value = "商户编号",required = true)
    private String merchantNo;
    /**
     * 优惠券类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private Long couponType;
}
