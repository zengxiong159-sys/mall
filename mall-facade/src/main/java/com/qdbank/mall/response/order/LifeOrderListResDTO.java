package com.qdbank.mall.response.order;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName OrderResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/12 9:36
 * @Version 1.0
 **/
@Data
public class LifeOrderListResDTO {
    /**
     * 订单主键
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "订单主键")
    private Long orderId;



    /**
     * 商户编号
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "规格编号")
    private Long productSkuId;





    /**
     * 商品售价中现金金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价中现金金额")
    private BigDecimal productCash;





    /**
     * 订单实付款(折算价)
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单实付款(折算价)")
    private BigDecimal payAmount;


    /**
     * 订单现金:包含商品售价中现金金额+运费金额-优惠金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单现金:包含商品售价中现金金额+运费金额-优惠金额")
    private BigDecimal orderCash;




    /**
     * 用户优惠券编号
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
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


    @ApiModelProperty(value = "订单状态：(根据商品类型区分）\" +\n" +
            "            \"实物订单：0 待支付   1 待发货 2 已发货 3已完成  4 已关闭  \" +\n" +
            "            \"话费订单：0 待支付 6 充值中  3 已完成 4 已关闭\" +\n" +
            "            \"积分券兑换订单：0 待支付 7 待使用 8 已使用 4 已关闭 5 已过期" )

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
     * 支付完成时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "支付完成时间")
    private Date paymentTime;



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





}
