package com.qdbank.mall.model.paymentFlow;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class PaymentFlowDO implements Serializable {
    private Long paymentFolwId;

    /**
     * 通联客户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "通联客户号")
    private Long custNo;

    /**
     * 订单号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单号")
    private String orderSn;

    /**
     * 账户类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "账户类型")
    private String acctType;

    /**
     * 交易时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "交易时间")
    private String oriTransDt;

    /**
     * 交易流水号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "交易流水号")
    private String oriTransser;

    /**
     * 支付类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "支付类型")
    private String queryType;

    /**
     * 金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "金额")
    private String transAmt;

    /**
     * 积分
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分")
    private String transe;

    /**
     * 行内准入接口返回准入标识
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "行内准入接口返回准入标识")
    private String accessSignId;

    /**
     * 状态
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态")
    private String status;

    /**
     * 创建时间      
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间        
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getPaymentFolwId() {
        return paymentFolwId;
    }

    public void setPaymentFolwId(Long paymentFolwId) {
        this.paymentFolwId = paymentFolwId;
    }

    public Long getCustNo() {
        return custNo;
    }

    public void setCustNo(Long custNo) {
        this.custNo = custNo;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public String getOriTransDt() {
        return oriTransDt;
    }

    public void setOriTransDt(String oriTransDt) {
        this.oriTransDt = oriTransDt;
    }

    public String getOriTransser() {
        return oriTransser;
    }

    public void setOriTransser(String oriTransser) {
        this.oriTransser = oriTransser;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }

    public String getTranse() {
        return transe;
    }

    public void setTranse(String transe) {
        this.transe = transe;
    }

    public String getAccessSignId() {
        return accessSignId;
    }

    public void setAccessSignId(String accessSignId) {
        this.accessSignId = accessSignId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paymentFolwId=").append(paymentFolwId);
        sb.append(", custNo=").append(custNo);
        sb.append(", orderSn=").append(orderSn);
        sb.append(", acctType=").append(acctType);
        sb.append(", oriTransDt=").append(oriTransDt);
        sb.append(", oriTransser=").append(oriTransser);
        sb.append(", queryType=").append(queryType);
        sb.append(", transAmt=").append(transAmt);
        sb.append(", transe=").append(transe);
        sb.append(", accessSignId=").append(accessSignId);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}