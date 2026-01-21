package com.qdbank.mall.model.trade;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class TradeFileDataDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 商户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号")
    private String merCode;

    /**
     * 商户名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户名称")
    private String merName;

    /**
     * 订单号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单号")
    private String orderSn;

    /**
     * 客户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "客户号")
    private String custNo;

    /**
     * 订单日期
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单日期")
    private String txnDate;

    /**
     * 订单时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单时间")
    private String txnTime;

    /**
     * 订单状态 R 撤销 S正常
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单状态R撤销S正常")
    private String orderStatus;

    /**
     * 订单总金额：现金+积分
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单总金额：现金+积分")
    private BigDecimal orderAmt;

    /**
     * 订单消耗积分
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单消耗积分")
    private String expendPoint;

    /**
     * 订单消耗金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单消耗金额")
    private BigDecimal expendAmt;

    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品编号")
    private String itemId;

    /**
     * 建议售价
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "建议售价")
    private BigDecimal advicePrice;

    /**
     * 预留域
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "预留域")
    private String reserved;

    /**
     * 支付方式：BP|纯积分 BM|纯现金 PM|积分+现金
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "支付方式：BP|纯积分BM|纯现金PM|积分+现金")
    private String paymentMethod;

    /**
     * 积分结算标志： Y|结算 N|不结算
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分结算标志：Y|结算N|不结算")
    private String pointSetMark;

    /**
     * 日切时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "日切时间")
    private String txDt;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 机构号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "机构号")
    private String org;

    private static final long serialVersionUID = 1L;


}