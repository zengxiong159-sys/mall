package com.qdbank.mall.model.rechargestatusdetail;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class RechargeStatusDO implements Serializable {
    /**
     * 主键 
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 订单编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    /**
     * 排序优先级
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "排序优先级")
    private Long detailLevel;

    /**
     * 充值状态0 待支付 1 充值中 2 已完成 3 已关闭
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "充值状态0待支付1充值中2已完成3已关闭")
    private Long status;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Long getDetailLevel() {
        return detailLevel;
    }

    public void setDetailLevel(Long detailLevel) {
        this.detailLevel = detailLevel;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
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
        sb.append(", id=").append(id);
        sb.append(", orderSn=").append(orderSn);
        sb.append(", detailLevel=").append(detailLevel);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}