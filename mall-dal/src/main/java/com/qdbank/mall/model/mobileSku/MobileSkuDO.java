package com.qdbank.mall.model.mobileSku;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MobileSkuDO implements Serializable {
    /**
     * 商品售价
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
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

    /**
     * 规格图片URL
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规格图片URL")
    private String skuPicUrl;

    /**
     * 商城产品id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商城产品id")
    private Long productId;

    /**
     * 网信产品id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "网信产品id")
    private String supplyProductId;

    /**
     * 产品类型 1：话费 2：
流量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "产品类型1：话费2：流量")
    private String supplyType;

    /**
     * 产品大小，type=1 时单
位为元，type=2 时单位 为 M
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "产品大小，type=1时单位为元，type=2时单位为M")
    private String supplyProductSize;

    /**
     * 运营商类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运营商类型")
    private String supplySupplierType;

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

    private static final long serialVersionUID = 1L;

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

    public String getSkuPicUrl() {
        return skuPicUrl;
    }

    public void setSkuPicUrl(String skuPicUrl) {
        this.skuPicUrl = skuPicUrl;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getSupplyProductId() {
        return supplyProductId;
    }

    public void setSupplyProductId(String supplyProductId) {
        this.supplyProductId = supplyProductId;
    }

    public String getSupplyType() {
        return supplyType;
    }

    public void setSupplyType(String supplyType) {
        this.supplyType = supplyType;
    }

    public String getSupplyProductSize() {
        return supplyProductSize;
    }

    public void setSupplyProductSize(String supplyProductSize) {
        this.supplyProductSize = supplyProductSize;
    }

    public String getSupplySupplierType() {
        return supplySupplierType;
    }

    public void setSupplySupplierType(String supplySupplierType) {
        this.supplySupplierType = supplySupplierType;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mobileSkuId=").append(mobileSkuId);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", productName=").append(productName);
        sb.append(", skuPicUrl=").append(skuPicUrl);
        sb.append(", productId=").append(productId);
        sb.append(", supplyProductId=").append(supplyProductId);
        sb.append(", supplyType=").append(supplyType);
        sb.append(", supplyProductSize=").append(supplyProductSize);
        sb.append(", supplySupplierType=").append(supplySupplierType);
        sb.append(", supplyPrice=").append(supplyPrice);
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