package com.qdbank.mall.model.ods;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OdsOrderDO implements Serializable {
    /**
     * 订单主键 
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单主键")
    private Long orderId;

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
     * "通联客户号对应的银行预留手机号"
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'通联客户号对应的银行预留手机号'")
    private Long custMobile;

    /**
     * 商户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号")
    private Long merchantNo;

    /**
     * 商户名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 订单编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品编号")
    private Long productId;

    /**
     * 商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;

    /**
     * 规格编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规格编号")
    private Long productSkuId;

    /**
     * 商品数量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品数量")
    private Long productCount;

    /**
     * 商品折算价 
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品折算价")
    private BigDecimal productPrice;

    /**
     * 商品售价中现金金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价中现金金额")
    private BigDecimal productCash;

    /**
     * 商品售价中积分量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价中积分量")
    private Long productIntegration;

    /**
     * 商品一级分类编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品一级分类编号")
    private Long categoryId1;

    /**
     * 商品二级分类编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品二级分类编号")
    private Long categoryId2;

    /**
     * 商品三级分类编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品三级分类编号")
    private Long categoryId3;

    /**
     * 商品四级分类编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品四级分类编号")
    private Long categoryId4;

    /**
     * 订单实付款(折算价)
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单实付款(折算价)")
    private BigDecimal payAmount;

    /**
     * 订单现金:包含商品售价中现金金额-优惠金额，不包含运费金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单现金:包含商品售价中现金金额-优惠金额，不包含运费金额")
    private BigDecimal orderCash;

    /**
     * 订单积分
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单积分")
    private Long orderIntegration;

    /**
     * 运费金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运费金额")
    private BigDecimal freightAmount;

    /**
     * 用户优惠券编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "用户优惠券编号")
    private Long userCouponId;

    /**
     * 优惠券类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private Long couponType;

    /**
     * 优惠券面值金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券面值金额")
    private BigDecimal couponAmount;

    /**
     * 支付方式： 0 纯积分  1 纯现金 2 积分+现金
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "支付方式：0纯积分1纯现金2积分+现金")
    private Long payType;

    /**
     * 订单状态：(根据商品类型区分）实物订单：0 待支付1 待发货2 已发货3 4 已关闭  话费订单：0 待支付1 充值中3 已完成4 已关闭 积分券兑换订单：0 待支付2 待使用3 已使用4 已关闭5 已过期
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单状态：(根据商品类型区分）实物订单：0待支付1待发货2已发货34已关闭话费订单：0待支付1充值中3已完成4已关闭积分券兑换订单：0待支付2待使用3已使用4已关闭5已过期")
    private Long status;

    /**
     * 订单关闭类型：0 用户取消关闭 1 超时自动关闭 2 支付失败关闭 3 退款成功关闭
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单关闭类型：0用户取消关闭1超时自动关闭2支付失败关闭3退款成功关闭")
    private Long closeType;

    /**
     * 退款状态 0 待审核 1 审核通过 2 退款成功 3审核不通过
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款状态0待审核1审核通过2退款成功3审核不通过")
    private Long refundStatus;

    /**
     * 商品类型：0 实物1 话费充值2 油卡充值3 视频会员充值4 积分兑换券
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品类型：0实物1话费充值2油卡充值3视频会员充值4积分兑换券")
    private Long productType;

    /**
     * 物流公司名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "物流公司名称")
    private String deliveryCompanyName;

    /**
     * 物流单号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "物流单号")
    private String deliverySn;

    /**
     * 收货人姓名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "收货人姓名")
    private String receiverName;

    /**
     * 收货人电话
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "收货人电话")
    private String receiverPhone;

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
     * 订单备注 
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单备注")
    private String note;

    /**
     * 确认收货状态：0 未确认1 已确认
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "确认收货状态：0未确认1已确认")
    private Long confirmStatus;

    /**
     * 积分结算标识：0 不结算 1 需要结算
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分结算标识：0不结算1需要结算")
    private Long integrationPayFlag;

    /**
     * 支付完成时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "支付完成时间")
    private Date paymentTime;

    /**
     * 发货时间  
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "发货时间")
    private Date deliveryTime;

    /**
     * 确认收货时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "确认收货时间")
    private Date receiveTime;

    /**
     * 充值手机号 
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "充值手机号")
    private String rechargeMobile;

    /**
     * 号码归属地 
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "号码归属地")
    private String mobileAddress;

    /**
     * 通知状态 0 未通知 1 已通知
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "通知状态0未通知1已通知")
    private Long noticeStatus;

    /**
     * 订单创建时间      
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单创建时间")
    private Date createTime;

    /**
     * 更新时间        
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 日切时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "日切时间")
    private String txDt;

    /**
     * 行内准入接口返回url 
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "行内准入接口返回url")
    private String reqUrl;

    /**
     * 行内准入接口返回准入标识
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "行内准入接口返回准入标识")
    private String accessSignId;

    private static final long serialVersionUID = 1L;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public Long getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(Long custMobile) {
        this.custMobile = custMobile;
    }

    public Long getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(Long merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductSkuId() {
        return productSkuId;
    }

    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
    }

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductCash() {
        return productCash;
    }

    public void setProductCash(BigDecimal productCash) {
        this.productCash = productCash;
    }

    public Long getProductIntegration() {
        return productIntegration;
    }

    public void setProductIntegration(Long productIntegration) {
        this.productIntegration = productIntegration;
    }

    public Long getCategoryId1() {
        return categoryId1;
    }

    public void setCategoryId1(Long categoryId1) {
        this.categoryId1 = categoryId1;
    }

    public Long getCategoryId2() {
        return categoryId2;
    }

    public void setCategoryId2(Long categoryId2) {
        this.categoryId2 = categoryId2;
    }

    public Long getCategoryId3() {
        return categoryId3;
    }

    public void setCategoryId3(Long categoryId3) {
        this.categoryId3 = categoryId3;
    }

    public Long getCategoryId4() {
        return categoryId4;
    }

    public void setCategoryId4(Long categoryId4) {
        this.categoryId4 = categoryId4;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getOrderCash() {
        return orderCash;
    }

    public void setOrderCash(BigDecimal orderCash) {
        this.orderCash = orderCash;
    }

    public Long getOrderIntegration() {
        return orderIntegration;
    }

    public void setOrderIntegration(Long orderIntegration) {
        this.orderIntegration = orderIntegration;
    }

    public BigDecimal getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(BigDecimal freightAmount) {
        this.freightAmount = freightAmount;
    }

    public Long getUserCouponId() {
        return userCouponId;
    }

    public void setUserCouponId(Long userCouponId) {
        this.userCouponId = userCouponId;
    }

    public Long getCouponType() {
        return couponType;
    }

    public void setCouponType(Long couponType) {
        this.couponType = couponType;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public Long getPayType() {
        return payType;
    }

    public void setPayType(Long payType) {
        this.payType = payType;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getCloseType() {
        return closeType;
    }

    public void setCloseType(Long closeType) {
        this.closeType = closeType;
    }

    public Long getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Long refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Long getProductType() {
        return productType;
    }

    public void setProductType(Long productType) {
        this.productType = productType;
    }

    public String getDeliveryCompanyName() {
        return deliveryCompanyName;
    }

    public void setDeliveryCompanyName(String deliveryCompanyName) {
        this.deliveryCompanyName = deliveryCompanyName;
    }

    public String getDeliverySn() {
        return deliverySn;
    }

    public void setDeliverySn(String deliverySn) {
        this.deliverySn = deliverySn;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(Long confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public Long getIntegrationPayFlag() {
        return integrationPayFlag;
    }

    public void setIntegrationPayFlag(Long integrationPayFlag) {
        this.integrationPayFlag = integrationPayFlag;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getRechargeMobile() {
        return rechargeMobile;
    }

    public void setRechargeMobile(String rechargeMobile) {
        this.rechargeMobile = rechargeMobile;
    }

    public String getMobileAddress() {
        return mobileAddress;
    }

    public void setMobileAddress(String mobileAddress) {
        this.mobileAddress = mobileAddress;
    }

    public Long getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(Long noticeStatus) {
        this.noticeStatus = noticeStatus;
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

    public String getTxDt() {
        return txDt;
    }

    public void setTxDt(String txDt) {
        this.txDt = txDt;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    public String getAccessSignId() {
        return accessSignId;
    }

    public void setAccessSignId(String accessSignId) {
        this.accessSignId = accessSignId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", custNo=").append(custNo);
        sb.append(", custName=").append(custName);
        sb.append(", custMobile=").append(custMobile);
        sb.append(", merchantNo=").append(merchantNo);
        sb.append(", merchantName=").append(merchantName);
        sb.append(", orderSn=").append(orderSn);
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", productSkuId=").append(productSkuId);
        sb.append(", productCount=").append(productCount);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", productCash=").append(productCash);
        sb.append(", productIntegration=").append(productIntegration);
        sb.append(", categoryId1=").append(categoryId1);
        sb.append(", categoryId2=").append(categoryId2);
        sb.append(", categoryId3=").append(categoryId3);
        sb.append(", categoryId4=").append(categoryId4);
        sb.append(", payAmount=").append(payAmount);
        sb.append(", orderCash=").append(orderCash);
        sb.append(", orderIntegration=").append(orderIntegration);
        sb.append(", freightAmount=").append(freightAmount);
        sb.append(", userCouponId=").append(userCouponId);
        sb.append(", couponType=").append(couponType);
        sb.append(", couponAmount=").append(couponAmount);
        sb.append(", payType=").append(payType);
        sb.append(", status=").append(status);
        sb.append(", closeType=").append(closeType);
        sb.append(", refundStatus=").append(refundStatus);
        sb.append(", productType=").append(productType);
        sb.append(", deliveryCompanyName=").append(deliveryCompanyName);
        sb.append(", deliverySn=").append(deliverySn);
        sb.append(", receiverName=").append(receiverName);
        sb.append(", receiverPhone=").append(receiverPhone);
        sb.append(", receiverProvince=").append(receiverProvince);
        sb.append(", receiverCity=").append(receiverCity);
        sb.append(", receiverRegion=").append(receiverRegion);
        sb.append(", receiverDetailAddress=").append(receiverDetailAddress);
        sb.append(", note=").append(note);
        sb.append(", confirmStatus=").append(confirmStatus);
        sb.append(", integrationPayFlag=").append(integrationPayFlag);
        sb.append(", paymentTime=").append(paymentTime);
        sb.append(", deliveryTime=").append(deliveryTime);
        sb.append(", receiveTime=").append(receiveTime);
        sb.append(", rechargeMobile=").append(rechargeMobile);
        sb.append(", mobileAddress=").append(mobileAddress);
        sb.append(", noticeStatus=").append(noticeStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", txDt=").append(txDt);
        sb.append(", reqUrl=").append(reqUrl);
        sb.append(", accessSignId=").append(accessSignId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}