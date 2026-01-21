package com.qdbank.mall.model.order;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName OrderImportDO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/5/27 8:59
 * @Version 1.0
 **/
@Data
public class OrderImportDO extends BaseDO {
    /**
     * 订单状态：(根据商品类型区分）实物订单：0 待支付   1 待发货 2 已发货 3已完成  4 已关闭  话费订单：0 待支付 6 充值中  3 已完成 4 已关闭 积分券兑换订单：0 待支付 7 待使用 8 已使用 4 已关闭 5 已过期
     订单状态：(根据商品类型区分）实物订单：0 待支付   1 待发货 2 已发货 3已完成  4 已关闭  话费订单：0 待支付 1 充值中  3 已完成 4 已关闭 积分券兑换订单：0 待支付 2 待使用 3 已使用 4 已关闭 5 已过期
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单状态：(根据商品类型区分）实物订单：0待支付1待发货2已发货3已完成4已关闭话费订单：0待支付6充值中3已完成4已关闭积分券兑换订单：0待支付7待使用8已使用4已关闭5已过期订单状态：(根据商品类型区分）实物订单：0待支付1待发货2已发货3已完成4已关闭话费订单：0待支付1充值中3已完成4已关闭积分券兑换订单：0待支付2待使用3已使用4已关闭5已过期")
    private String status;
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
    private String custMobile;

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
    private String productCount;


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
    private String orderIntegration;

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
    private String userCouponId;

    /**
     * 用户优惠券编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分兑换优惠券编号")
    private Long exchangeCouponId;

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
    private String productType;

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
    private Date paymentTime;
    private BigDecimal payAmount;

    private String skuAttribute;
}
