package com.qdbank.mall.model.orderitem;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class OrderItemDO implements Serializable {
    private Long id;

    /**
     * 订单id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单id")
    private Long orderId;

    /**
     * 订单编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    /**
     * 商户id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户id")
    private Long merchantId;

    /**
     * 商户名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 商品id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品id")
    private Long productId;

    /**
     * 商品图片url
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品图片url")
    private String productPic;

    /**
     * 商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;

    /**
     * 商品品牌
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品品牌")
    private String productBrand;

    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品编号")
    private String productSn;

    /**
     * 商品价格
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品价格")
    private Long productPrice;

    /**
     * 商品数量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品数量")
    private Long productQuantity;

    /**
     * 库存id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "库存id")
    private Long productSkuId;

    /**
     * 库存编码
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "库存编码")
    private String productSkuCode;

    /**
     * 商品分类id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品分类id")
    private Long productCategoryId;

    /**
     * 商品促销名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品促销名称")
    private String promotionName;

    /**
     * 商品促销金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品促销金额")
    private Long promotionAmount;

    /**
     * 优惠券金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券金额")
    private Long couponAmount;

    /**
     * 积分券金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分券金额")
    private Long integrationAmount;

    /**
     * 实际支付金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "实际支付金额")
    private Long realAmount;

    private Long giftIntegration;

    /**
     * 积分成长值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分成长值")
    private Long giftGrowth;

    /**
     * 商品属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品属性:[{'key':'颜色','value':'颜色'},{'key':'容量','value':'4G'}]")
    private String productAttr;

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

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public Long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Long getProductSkuId() {
        return productSkuId;
    }

    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
    }

    public String getProductSkuCode() {
        return productSkuCode;
    }

    public void setProductSkuCode(String productSkuCode) {
        this.productSkuCode = productSkuCode;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public Long getPromotionAmount() {
        return promotionAmount;
    }

    public void setPromotionAmount(Long promotionAmount) {
        this.promotionAmount = promotionAmount;
    }

    public Long getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Long couponAmount) {
        this.couponAmount = couponAmount;
    }

    public Long getIntegrationAmount() {
        return integrationAmount;
    }

    public void setIntegrationAmount(Long integrationAmount) {
        this.integrationAmount = integrationAmount;
    }

    public Long getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(Long realAmount) {
        this.realAmount = realAmount;
    }

    public Long getGiftIntegration() {
        return giftIntegration;
    }

    public void setGiftIntegration(Long giftIntegration) {
        this.giftIntegration = giftIntegration;
    }

    public Long getGiftGrowth() {
        return giftGrowth;
    }

    public void setGiftGrowth(Long giftGrowth) {
        this.giftGrowth = giftGrowth;
    }

    public String getProductAttr() {
        return productAttr;
    }

    public void setProductAttr(String productAttr) {
        this.productAttr = productAttr;
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
        sb.append(", orderSn=").append(orderSn);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", merchantName=").append(merchantName);
        sb.append(", productId=").append(productId);
        sb.append(", productPic=").append(productPic);
        sb.append(", productName=").append(productName);
        sb.append(", productBrand=").append(productBrand);
        sb.append(", productSn=").append(productSn);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", productQuantity=").append(productQuantity);
        sb.append(", productSkuId=").append(productSkuId);
        sb.append(", productSkuCode=").append(productSkuCode);
        sb.append(", productCategoryId=").append(productCategoryId);
        sb.append(", promotionName=").append(promotionName);
        sb.append(", promotionAmount=").append(promotionAmount);
        sb.append(", couponAmount=").append(couponAmount);
        sb.append(", integrationAmount=").append(integrationAmount);
        sb.append(", realAmount=").append(realAmount);
        sb.append(", giftIntegration=").append(giftIntegration);
        sb.append(", giftGrowth=").append(giftGrowth);
        sb.append(", productAttr=").append(productAttr);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}