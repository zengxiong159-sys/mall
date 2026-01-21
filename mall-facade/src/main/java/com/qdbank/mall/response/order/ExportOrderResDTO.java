package com.qdbank.mall.response.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ExportOrderResDTO
 *
 * @author shaoshihang
 * @date 2021/3/16 15:47
 * @since 1.0.0
 */
@Data
public class ExportOrderResDTO {
    /**
     * 商品类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品类型")
    private String productType;
    /**
     * 订单编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单编号")
    private String orderSn;
    /**
     * 客户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "客户号")
    private String custNo;
    /**
     * 客户手机
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "客户手机")
    private String custMobile;
    /**
     * 订单创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单创建时间")
    private String createTime;
    /**
     * 支付完成时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "支付完成时间")
    private String paymentTime;
    /**
     * 收货人电话
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "收货人电话")
    private String receiverPhone;
    /**
     * 收货人姓名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "收货人姓名")
    private String receiverName;
    /**
     * 收货地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "收货地址")
    private String receiverDetailAddress;
    /**
     * 订单状态
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单状态")
    private String status;
    /**
     * 订单实付款折算金额（元）
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单实付款折算金额（元）")
    private String payAmount;
    /**
     * 订单积分
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单积分")
    private String orderIntegration;
    /**
     * 运费（元）
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运费（元）")
    private String freightAmount;
    /**
     * 优惠券面值（元）
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券面值（元）")
    private String couponAmount;
    /**
     * 商品类目
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品类目")
    private String ctateGoryName;
    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品编号")
    private String productId;
    /**
     * 商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;
    /**
     * 商品规格
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品规格")
    private String productSpData;
    /**
     * 商品数量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品数量")
    private String productCount;
    /**
     * 商品售价现金金额（元）
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价现金金额（元）")
    private String productCash;
    /**
     * 商品售价积分量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价积分量")
    private String productIntegration;
    /**
     * 商户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号")
    private String merchantNo;
    /**
     * 商户名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    /**
     * 关联退款流水号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "关联退款流水号")
    private String refundSerial;
}
