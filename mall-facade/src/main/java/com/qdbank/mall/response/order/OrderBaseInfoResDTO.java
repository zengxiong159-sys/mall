package com.qdbank.mall.response.order;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName OrderBaseInfo
 * @Description 订单基本信息
 * @Author ningyuehuai
 * @Date 2021/1/12 11:04
 * @Version 1.0
 **/
@Data
public class OrderBaseInfoResDTO {
    /**
     * 订单主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单主键")
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonSerialize(using = ToStringSerializer.class)
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
     * 订单现金:包含商品售价中现金金额+运费金额-优惠金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单现金:")
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
            "            \"积分券兑换订单：0 待支付 7 待使用 8 已使用 4 已关闭 5 已过期"

    )
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
    @JSONField(format="unixtime")
    private Date paymentTime;

    /**
     * 发货时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "发货时间")
    @JSONField(format="unixtime")
    private Date deliveryTime;

    /**
     * 确认收货时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "确认收货时间")
    @JSONField(format="unixtime")
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
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

    /**
     * 积分抵扣金额
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "积分抵扣金额")
    private BigDecimal integralDeduct;
}
