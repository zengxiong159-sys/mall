package com.qdbank.mall.model.rechargeMobile;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class RechargeMobileDO implements Serializable {
    /**
     * 用户手机充值主键 
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "用户手机充值主键")
    private Long rechargeMobileId;

    /**
     * 通联客户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "通联客户号")
    private Long custNo;

    /**
     * 客户充值手机号码
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "客户充值手机号码")
    private Long mobile;

    /**
     * 充值规格
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "充值规格")
    private Long skuId;

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
     * 归属地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "归属地址")
    private String mobileAddress;

    /**
     * 运营商
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运营商")
    private String supplierType;

    private static final long serialVersionUID = 1L;

    public Long getRechargeMobileId() {
        return rechargeMobileId;
    }

    public void setRechargeMobileId(Long rechargeMobileId) {
        this.rechargeMobileId = rechargeMobileId;
    }

    public Long getCustNo() {
        return custNo;
    }

    public void setCustNo(Long custNo) {
        this.custNo = custNo;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
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

    public String getMobileAddress() {
        return mobileAddress;
    }

    public void setMobileAddress(String mobileAddress) {
        this.mobileAddress = mobileAddress;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rechargeMobileId=").append(rechargeMobileId);
        sb.append(", custNo=").append(custNo);
        sb.append(", mobile=").append(mobile);
        sb.append(", skuId=").append(skuId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", mobileAddress=").append(mobileAddress);
        sb.append(", supplierType=").append(supplierType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}