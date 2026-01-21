package com.qdbank.mall.model.mobileSkuProvince;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MobileSkuProvinceDO implements Serializable {
    private Long mobileSkuProvinceId;

    /**
     * 规格id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规格id")
    private Long mobileSkuId;

    /**
     * 商品售价
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价")
    private BigDecimal productPrice;

    /**
     * 商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;

    private BigDecimal supplyPrice;

    /**
     * 状态标识：1上架 0下架
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态标识：1上架0下架")
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

    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    /**
     * 更新人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新人")
    private String updatedBy;

    /**
     * 手机归属地
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "手机归属地")
    private String mobileAddress;

    private static final long serialVersionUID = 1L;

    public Long getMobileSkuProvinceId() {
        return mobileSkuProvinceId;
    }

    public void setMobileSkuProvinceId(Long mobileSkuProvinceId) {
        this.mobileSkuProvinceId = mobileSkuProvinceId;
    }

    public Long getMobileSkuId() {
        return mobileSkuId;
    }

    public void setMobileSkuId(Long mobileSkuId) {
        this.mobileSkuId = mobileSkuId;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(BigDecimal supplyPrice) {
        this.supplyPrice = supplyPrice;
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

    public String getMobileAddress() {
        return mobileAddress;
    }

    public void setMobileAddress(String mobileAddress) {
        this.mobileAddress = mobileAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mobileSkuProvinceId=").append(mobileSkuProvinceId);
        sb.append(", mobileSkuId=").append(mobileSkuId);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", productName=").append(productName);
        sb.append(", supplyPrice=").append(supplyPrice);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", mobileAddress=").append(mobileAddress);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}