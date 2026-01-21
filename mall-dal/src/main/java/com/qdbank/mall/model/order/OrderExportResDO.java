package com.qdbank.mall.model.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderExportResDO
 *
 * @author shaoshihang
 * @date 2021/3/11 10:17
 * @since 1.0.0
 */
@Data
public class OrderExportResDO {
    /**
     * 商品类型：0 实物1 话费充值2 油卡充值3 视频会员充值4 积分兑换券
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品类型：0实物1话费充值2油卡充值3视频会员充值4积分兑换券")
    private Long productType;
    /**
     * 订单编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单编号")
    private String orderSn;
    /**
     * 通联客户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "通联客户号")
    private Long custNo;
    /**
     * "通联客户号对应的银行预留手机号"
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'通联客户号对应的银行预留手机号'")
    private Long custMobile;
    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
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
     * 详细地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "详细地址")
    private String receiverDetailAddress;
    /**
     * 订单状态：(根据商品类型区分）实物订单：0 待支付1 待发货2 已发货3 4 已关闭  话费订单：0 待支付1 充值中3 已完成4 已关闭 积分券兑换订单：0 待支付2 待使用3 已使用4 已关闭5 已过期
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单状态：(根据商品类型区分）实物订单：0待支付1待发货2已发货34已关闭话费订单：0待支付1充值中3已完成4已关闭积分券兑换订单：0待支付2待使用3已使用4已关闭5已过期")
    private Long status;
    /**
     * 订单实付款(折算价)
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单实付款(折算价)")
    private BigDecimal payAmount;

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
     * 优惠券面值金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券面值金额")
    private BigDecimal couponAmount;
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
    private Long productId;

    /**
     * 商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;
    /**
     * 商品属性:JSON格式
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品属性:JSON格式")
    private String productSpData;
    /**
     * 商品数量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品数量")
    private Long productCount;
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
     * 退款流水号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款流水号")
    private String refundSerial;

}
