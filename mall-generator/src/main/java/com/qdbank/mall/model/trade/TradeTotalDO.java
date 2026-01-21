package com.qdbank.mall.model.trade;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class TradeTotalDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 商户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户号")
    private String merchantNo;

    /**
     * 商户名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 订单类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单类型")
    private String orderType;

    /**
     * 订单笔数汇总
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单笔数汇总")
    private String orderCount;

    /**
     * 商品实际售价折算价
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品实际售价折算价")
    private String productPrice;

    /**
     * 积分汇总
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分汇总")
    private String integrationCount;

    /**
     * 积分兑换券汇总
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分兑换券汇总")
    private String couponCount;

    /**
     * 指定商品兑换券汇总
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "指定商品兑换券汇总")
    private String productCouponCount;

    /**
     * 现金汇总
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "现金汇总")
    private String orderCash;

    /**
     * 运费汇总
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运费汇总")
    private String freightAmount;

    /**
     * 现金+运费
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "现金+运费")
    private String cashFreightAmount;

    /**
     * 交易日期
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "交易日期")
    private String paymentTime;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 优惠券类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券类型")
    private String couponType;

    /**
     * 发放方式: 0:用户兑换 1:行方发放
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "发放方式:0:用户兑换1:行方发放")
    private String distributeWay;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getIntegrationCount() {
        return integrationCount;
    }

    public void setIntegrationCount(String integrationCount) {
        this.integrationCount = integrationCount;
    }

    public String getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(String couponCount) {
        this.couponCount = couponCount;
    }

    public String getProductCouponCount() {
        return productCouponCount;
    }

    public void setProductCouponCount(String productCouponCount) {
        this.productCouponCount = productCouponCount;
    }

    public String getOrderCash() {
        return orderCash;
    }

    public void setOrderCash(String orderCash) {
        this.orderCash = orderCash;
    }

    public String getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(String freightAmount) {
        this.freightAmount = freightAmount;
    }

    public String getCashFreightAmount() {
        return cashFreightAmount;
    }

    public void setCashFreightAmount(String cashFreightAmount) {
        this.cashFreightAmount = cashFreightAmount;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getDistributeWay() {
        return distributeWay;
    }

    public void setDistributeWay(String distributeWay) {
        this.distributeWay = distributeWay;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", merchantNo=").append(merchantNo);
        sb.append(", merchantName=").append(merchantName);
        sb.append(", orderType=").append(orderType);
        sb.append(", orderCount=").append(orderCount);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", integrationCount=").append(integrationCount);
        sb.append(", couponCount=").append(couponCount);
        sb.append(", productCouponCount=").append(productCouponCount);
        sb.append(", orderCash=").append(orderCash);
        sb.append(", freightAmount=").append(freightAmount);
        sb.append(", cashFreightAmount=").append(cashFreightAmount);
        sb.append(", paymentTime=").append(paymentTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", couponType=").append(couponType);
        sb.append(", distributeWay=").append(distributeWay);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}