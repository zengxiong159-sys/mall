package com.qdbank.mall.model.couponrecord;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CouponrecordDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 积分兑换订单id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分兑换订单id")
    private Long orderId;

    /**
     * 客户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "客户号")
    private String custNo;

    /**
     * 积分兑换券信息id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分兑换券信息id")
    private Long integralCouponId;

    /**
     * 积分值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分值")
    private Long couponValue;

    /**
     * 积分折算成人民币金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分折算成人民币金额")
    private BigDecimal couponPrice;

    /**
     * 积分券过期天数
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分券过期天数")
    private Long expireDate;

    /**
     * 积分券名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分券名称")
    private String couponName;

    /**
     * 积分券状态 :0 待支付 1 已取消  2 待使用 3 已使用 4 已过期 5 已完成
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分券状态 :0 待支付 1 已取消  2 待使用 3 已使用 4 已过期 5 已完成")
    private Long status;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public Long getIntegralCouponId() {
        return integralCouponId;
    }

    public void setIntegralCouponId(Long integralCouponId) {
        this.integralCouponId = integralCouponId;
    }

    public Long getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(Long couponValue) {
        this.couponValue = couponValue;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public Long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Long expireDate) {
        this.expireDate = expireDate;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
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
        sb.append(", orderId=").append(orderId);
        sb.append(", custNo=").append(custNo);
        sb.append(", integralCouponId=").append(integralCouponId);
        sb.append(", couponValue=").append(couponValue);
        sb.append(", couponPrice=").append(couponPrice);
        sb.append(", expireDate=").append(expireDate);
        sb.append(", couponName=").append(couponName);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}