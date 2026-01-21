package com.qdbank.mall.model.refundErrorLog;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class RefundErrorLogDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long refundErrorLogId;

    /**
     * 通联客户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "通联客户号")
    private Long custNo;

    /**
     * 退款流水号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款流水号")
    private Long refundSerial;

    /**
     * 订单号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单号")
    private String orderSn;

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

    public Long getRefundErrorLogId() {
        return refundErrorLogId;
    }

    public void setRefundErrorLogId(Long refundErrorLogId) {
        this.refundErrorLogId = refundErrorLogId;
    }

    public Long getCustNo() {
        return custNo;
    }

    public void setCustNo(Long custNo) {
        this.custNo = custNo;
    }

    public Long getRefundSerial() {
        return refundSerial;
    }

    public void setRefundSerial(Long refundSerial) {
        this.refundSerial = refundSerial;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
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
        sb.append(", refundErrorLogId=").append(refundErrorLogId);
        sb.append(", custNo=").append(custNo);
        sb.append(", refundSerial=").append(refundSerial);
        sb.append(", orderSn=").append(orderSn);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}