package com.qdbank.mall.model.orderreport;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName OrderTotalReportDO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/13 12:14
 * @Version 1.0
 **/
@Data
public class OrderTotalReportDO {
    @ApiModelProperty(value = "订单笔数")
    private Long total=0L;

    @ApiModelProperty(value = "商品实际售价折算价")
    private BigDecimal productPrice=new BigDecimal(0);
    /**
     * 支付现金
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "支付现金：实际支付现金(商品售价-券面值金额)部分不包含运费")
    private BigDecimal orderCash=new BigDecimal(0);


    /**
     * 订单积分
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单积分")
    private Long orderIntegration=0L;

    /**
     * 运费金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运费金额")
    private BigDecimal freightAmount=new BigDecimal(0);

    @ApiModelProperty(value = "现金+运费")
    private BigDecimal cashAndFreightAmount=new BigDecimal(0);

    @ApiModelProperty(value = "商户编号")
    private Long merchantNo;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "积分券汇总")
    private BigDecimal couponCount=new BigDecimal(0);
    @ApiModelProperty(value = "指定商品券汇总")
    private BigDecimal productCouponCount=new BigDecimal(0);

    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private String couponType;
    @ApiModelProperty(value = "发放形式")
    private String distributeWay;
    private BigDecimal discountAmount;
}

