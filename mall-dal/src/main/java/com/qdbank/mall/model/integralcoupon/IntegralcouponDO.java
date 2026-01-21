package com.qdbank.mall.model.integralcoupon;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class IntegralcouponDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 积分券类型 0 积分兑换券
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分券类型 0 积分兑换券")
    private Long couponType;

    /**
     * 积分券名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分券名称")
    private String couponName;

    /**
     * 规则描述
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规则描述")
    private String couponRuleDescription;

    /**
     * 积分券值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分值")
    private Long couponValue;

    /**
     * 面值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "面值")
    private BigDecimal faceValue;

    /**
     * 券过期时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "券过期时间")
    private Long expireDate;

    /**
     * 积分图片URL
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分图片URL")
    private String picUrl;

    /**
     * 商品类型 0 话费 1 油卡 2 视频会员
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品类型 0 话费 1 油卡 2 视频会员")
    private Long productType;

    /**
     * 商品id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品id")
    private Long productId;

    /**
     * 库存id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "库存id")
    private Long skuId;

    /**
     * 已兑换总量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "已兑换总量")
    private Long convertTotal;

    /**
     * 已使用总量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "已使用总量")
    private Long useTotal;

    /**
     * 已过期总量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "已过期总量")
    private Long expireTotal;

    /**
     * 积分券状态 0 待上架 1 已上架 2 已下架
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分券状态 0 待上架 1 已上架 2 已下架")
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

    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    /**
     * 修改人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCouponType() {
        return couponType;
    }

    public void setCouponType(Long couponType) {
        this.couponType = couponType;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponRuleDescription() {
        return couponRuleDescription;
    }

    public void setCouponRuleDescription(String couponRuleDescription) {
        this.couponRuleDescription = couponRuleDescription;
    }

    public Long getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(Long couponValue) {
        this.couponValue = couponValue;
    }

    public BigDecimal getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }

    public Long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Long expireDate) {
        this.expireDate = expireDate;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Long getProductType() {
        return productType;
    }

    public void setProductType(Long productType) {
        this.productType = productType;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getConvertTotal() {
        return convertTotal;
    }

    public void setConvertTotal(Long convertTotal) {
        this.convertTotal = convertTotal;
    }

    public Long getUseTotal() {
        return useTotal;
    }

    public void setUseTotal(Long useTotal) {
        this.useTotal = useTotal;
    }

    public Long getExpireTotal() {
        return expireTotal;
    }

    public void setExpireTotal(Long expireTotal) {
        this.expireTotal = expireTotal;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", couponType=").append(couponType);
        sb.append(", couponName=").append(couponName);
        sb.append(", couponRuleDescription=").append(couponRuleDescription);
        sb.append(", couponValue=").append(couponValue);
        sb.append(", faceValue=").append(faceValue);
        sb.append(", expireDate=").append(expireDate);
        sb.append(", picUrl=").append(picUrl);
        sb.append(", productType=").append(productType);
        sb.append(", productId=").append(productId);
        sb.append(", skuId=").append(skuId);
        sb.append(", convertTotal=").append(convertTotal);
        sb.append(", useTotal=").append(useTotal);
        sb.append(", expireTotal=").append(expireTotal);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}