package com.qdbank.mall.model.address;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class AddressDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 通联客户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "通联客户号")
    private Long custNo;

    /**
     * 通联核心用户姓名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "通联核心用户姓名")
    private String custName;

    /**
     * 收件人手机号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "收件人手机号")
    private String receiverMobile;

    /**
     * 收货人姓名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "收货人姓名")
    private String receiverName;

    /**
     * 省份直辖市
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "省份直辖市")
    private String receiverProvince;

    /**
     * 城市
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "城市")
    private String receiverCity;

    /**
     * 区县
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "区县")
    private String receiverRegion;

    /**
     * 详细地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "详细地址")
    private String receiverDetailAddress;

    /**
     * 默认地址标识：0 非默认地址 1 默认地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "默认地址标识：0非默认地址1默认地址")
    private Long defaultFlag;

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

    public Long getCustNo() {
        return custNo;
    }

    public void setCustNo(Long custNo) {
        this.custNo = custNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverRegion() {
        return receiverRegion;
    }

    public void setReceiverRegion(String receiverRegion) {
        this.receiverRegion = receiverRegion;
    }

    public String getReceiverDetailAddress() {
        return receiverDetailAddress;
    }

    public void setReceiverDetailAddress(String receiverDetailAddress) {
        this.receiverDetailAddress = receiverDetailAddress;
    }

    public Long getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Long defaultFlag) {
        this.defaultFlag = defaultFlag;
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
        sb.append(", custNo=").append(custNo);
        sb.append(", custName=").append(custName);
        sb.append(", receiverMobile=").append(receiverMobile);
        sb.append(", receiverName=").append(receiverName);
        sb.append(", receiverProvince=").append(receiverProvince);
        sb.append(", receiverCity=").append(receiverCity);
        sb.append(", receiverRegion=").append(receiverRegion);
        sb.append(", receiverDetailAddress=").append(receiverDetailAddress);
        sb.append(", defaultFlag=").append(defaultFlag);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}