package com.qdbank.mall.model.ods;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OdsOrderRefundDO implements Serializable {
    /**
     * 退款流水号                                          
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款流水号")
    private String refundSerial;

    /**
     * 通联客户号                                          
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "通联客户号")
    private String custNo;

    /**
     * 通联核心用户姓名                                       
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "通联核心用户姓名")
    private String custName;

    /**
     * "通联客户号对应的银行预
留手机号"                             
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'通联客户号对应的银行预留手机号'")
    private Long custMobile;

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
     * 商品数量                                           
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品数量")
    private Long productCount;

    /**
     * 规格编号                                           
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规格编号")
    private Long productSkuId;

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
     * "订单现金:包含商品售价中现金金额-优惠券金额，不包含运费"                
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'订单现金:包含商品售价中现金金额-优惠券金额，不包含运费'")
    private BigDecimal orderCash;

    /**
     * 订单积分                                           
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单积分")
    private Long orderIntegration;

    /**
     * 退款总金额折算价
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款总金额折算价")
    private BigDecimal refundAmount;

    /**
     * "退款现金，不包含运费
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'退款现金，不包含运费")
    private BigDecimal refundCash;

    /**
     * 运费金额                                           
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运费金额")
    private BigDecimal freightAmount;

    /**
     * 退款积分：订单积分                                      
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款积分：订单积分")
    private Long refundIntegration;

    /**
     * "退款状态
0 待审核
1 审核通过
2 退款成功
3审核不通过"            
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'退款状态0待审核1审核通过2退款成功3审核不通过'")
    private Long refundStatus;

    /**
     * "商品类型：
0 实物
1 话费充值
2 油卡充值
3 视频会员充值
4 积分兑换券
"   
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'商品类型：0实物1话费充值2油卡充值3视频会员充值4积分兑换券'")
    private Long productType;

    /**
     * 退款处理时间                                         
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款处理时间")
    private Date handleStartTime;

    /**
     * 退款完成时间                                         
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款完成时间")
    private Date handleFinishTime;

    /**
     * "处理结果：
0 同意退款
1 退款驳回"                          
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'处理结果：0同意退款1退款驳回'")
    private Long handleResult;

    /**
     * 退款原因                                           
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款原因")
    private String reason;

    /**
     * "退款类型
0 仅退款(无需退货)
1 退货退款"                    
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'退款类型0仅退款(无需退货)1退货退款'")
    private Long refundType;

    /**
     * 上传凭证图片url                                      
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "上传凭证图片url")
    private String proofPics;

    /**
     * 管理员姓名(处理人姓名)                                   
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "管理员姓名(处理人姓名)")
    private String adminName;

    /**
     * 管理员邮箱(处理人邮箱)                                   
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "管理员邮箱(处理人邮箱)")
    private String email;

    /**
     * 退款说明                                           
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款说明")
    private String refundNote;

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
     * 日切时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "日切时间")
    private String txDt;

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

    public String getRefundSerial() {
        return refundSerial;
    }

    public void setRefundSerial(String refundSerial) {
        this.refundSerial = refundSerial;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
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

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }

    public Long getProductSkuId() {
        return productSkuId;
    }

    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
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

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getRefundCash() {
        return refundCash;
    }

    public void setRefundCash(BigDecimal refundCash) {
        this.refundCash = refundCash;
    }

    public BigDecimal getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(BigDecimal freightAmount) {
        this.freightAmount = freightAmount;
    }

    public Long getRefundIntegration() {
        return refundIntegration;
    }

    public void setRefundIntegration(Long refundIntegration) {
        this.refundIntegration = refundIntegration;
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

    public Date getHandleStartTime() {
        return handleStartTime;
    }

    public void setHandleStartTime(Date handleStartTime) {
        this.handleStartTime = handleStartTime;
    }

    public Date getHandleFinishTime() {
        return handleFinishTime;
    }

    public void setHandleFinishTime(Date handleFinishTime) {
        this.handleFinishTime = handleFinishTime;
    }

    public Long getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(Long handleResult) {
        this.handleResult = handleResult;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getRefundType() {
        return refundType;
    }

    public void setRefundType(Long refundType) {
        this.refundType = refundType;
    }

    public String getProofPics() {
        return proofPics;
    }

    public void setProofPics(String proofPics) {
        this.proofPics = proofPics;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRefundNote() {
        return refundNote;
    }

    public void setRefundNote(String refundNote) {
        this.refundNote = refundNote;
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

    public String getTxDt() {
        return txDt;
    }

    public void setTxDt(String txDt) {
        this.txDt = txDt;
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
        sb.append(", refundSerial=").append(refundSerial);
        sb.append(", custNo=").append(custNo);
        sb.append(", custName=").append(custName);
        sb.append(", custMobile=").append(custMobile);
        sb.append(", orderSn=").append(orderSn);
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", productCount=").append(productCount);
        sb.append(", productSkuId=").append(productSkuId);
        sb.append(", categoryId1=").append(categoryId1);
        sb.append(", categoryId2=").append(categoryId2);
        sb.append(", categoryId3=").append(categoryId3);
        sb.append(", categoryId4=").append(categoryId4);
        sb.append(", orderCash=").append(orderCash);
        sb.append(", orderIntegration=").append(orderIntegration);
        sb.append(", refundAmount=").append(refundAmount);
        sb.append(", refundCash=").append(refundCash);
        sb.append(", freightAmount=").append(freightAmount);
        sb.append(", refundIntegration=").append(refundIntegration);
        sb.append(", refundStatus=").append(refundStatus);
        sb.append(", productType=").append(productType);
        sb.append(", handleStartTime=").append(handleStartTime);
        sb.append(", handleFinishTime=").append(handleFinishTime);
        sb.append(", handleResult=").append(handleResult);
        sb.append(", reason=").append(reason);
        sb.append(", refundType=").append(refundType);
        sb.append(", proofPics=").append(proofPics);
        sb.append(", adminName=").append(adminName);
        sb.append(", email=").append(email);
        sb.append(", refundNote=").append(refundNote);
        sb.append(", merchantNo=").append(merchantNo);
        sb.append(", merchantName=").append(merchantName);
        sb.append(", txDt=").append(txDt);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}