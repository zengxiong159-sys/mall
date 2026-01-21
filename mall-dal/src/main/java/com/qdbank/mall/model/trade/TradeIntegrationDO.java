package com.qdbank.mall.model.trade;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class TradeIntegrationDO implements Serializable {
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
    private String merchantNo;

    /**
     * 商户名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 需结算积分（元）
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "需结算积分（元）")
    private String needPayIntegration;

    /**
     * 不需结算积分(元)
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "不需结算积分(元)")
    private String notNeedPayIntegration;

    /**
     * 积分兑换券(元)
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分兑换券(元)")
    private String integrationCoupon;

    /**
     * 需结算汇总
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "需结算汇总")
    private String needPayTotalIntegration;

    /**
     * 汇总
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "汇总")
    private String totalIntegration;

    /**
     * 日期
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "日期")
    private String paymentTime;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

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

    public String getNeedPayIntegration() {
        return needPayIntegration;
    }

    public void setNeedPayIntegration(String needPayIntegration) {
        this.needPayIntegration = needPayIntegration;
    }

    public String getNotNeedPayIntegration() {
        return notNeedPayIntegration;
    }

    public void setNotNeedPayIntegration(String notNeedPayIntegration) {
        this.notNeedPayIntegration = notNeedPayIntegration;
    }

    public String getIntegrationCoupon() {
        return integrationCoupon;
    }

    public void setIntegrationCoupon(String integrationCoupon) {
        this.integrationCoupon = integrationCoupon;
    }

    public String getNeedPayTotalIntegration() {
        return needPayTotalIntegration;
    }

    public void setNeedPayTotalIntegration(String needPayTotalIntegration) {
        this.needPayTotalIntegration = needPayTotalIntegration;
    }

    public String getTotalIntegration() {
        return totalIntegration;
    }

    public void setTotalIntegration(String totalIntegration) {
        this.totalIntegration = totalIntegration;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", merchantNo=").append(merchantNo);
        sb.append(", merchantName=").append(merchantName);
        sb.append(", needPayIntegration=").append(needPayIntegration);
        sb.append(", notNeedPayIntegration=").append(notNeedPayIntegration);
        sb.append(", integrationCoupon=").append(integrationCoupon);
        sb.append(", needPayTotalIntegration=").append(needPayTotalIntegration);
        sb.append(", totalIntegration=").append(totalIntegration);
        sb.append(", paymentTime=").append(paymentTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}