package com.qdbank.mall.model.rechargeMobileFlow;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class RechargeMobileFlowDO implements Serializable {
    /**
     * 用户手机充值流水主键 
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "用户手机充值流水主键")
    private Long rechargeMobileFolwId;

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
     * 客户充值手机号码
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "客户充值号码")
    private String mobile;

    /**
     * 充值规格
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "充值规格")
    private Long skuId;

    /**
     * 网信订单号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "网信订单号")
    private String wxOrderId;

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

    /**
     * 话费充值原因
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "话费充值原因")
    private String rechargeRemark;

    private static final long serialVersionUID = 1L;

    public Long getRechargeMobileFolwId() {
        return rechargeMobileFolwId;
    }

    public void setRechargeMobileFolwId(Long rechargeMobileFolwId) {
        this.rechargeMobileFolwId = rechargeMobileFolwId;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getWxOrderId() {
        return wxOrderId;
    }

    public void setWxOrderId(String wxOrderId) {
        this.wxOrderId = wxOrderId;
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

    public String getRechargeRemark() {
        return rechargeRemark;
    }

    public void setRechargeRemark(String rechargeRemark) {
        this.rechargeRemark = rechargeRemark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rechargeMobileFolwId=").append(rechargeMobileFolwId);
        sb.append(", custNo=").append(custNo);
        sb.append(", orderSn=").append(orderSn);
        sb.append(", mobile=").append(mobile);
        sb.append(", skuId=").append(skuId);
        sb.append(", wxOrderId=").append(wxOrderId);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", rechargeRemark=").append(rechargeRemark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}