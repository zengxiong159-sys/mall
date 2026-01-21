package com.qdbank.mall.response.order.orderdetail;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName OrderBaseDetailResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/28 9:11
 * @Version 1.0
 **/
@Data
public class OrderBaseDetailResDTO {
    /**
     * 订单主键
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
     * 优惠券面值金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券面值金额")
    private BigDecimal couponAmount;


    @ApiModelProperty(value = "订单状态：(根据商品类型区分）\" +\n" +
            "            \"实物订单：0 待支付   1 待发货 2 已发货 3已完成  4 已关闭  \" +\n" +
            "            \"话费订单：0 待支付 6 充值中  3 已完成 4 已关闭\" +\n" +
            "            \"积分券兑换订单：0 待支付 7 待使用 8 已使用 4 已关闭 5 已过期")
    private Long status;

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
     * 支付完成时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "支付完成时间")
    @JSONField(format="unixtime")
    private Date paymentTime;

    /**
     * 充值手机号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "充值账号：手机号、油卡账号、视频会员账号")
    private String rechargeMobile;

    /**
     * 订单创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单创建时间")
    private Date createTime;
    /**
     * 用户优惠券编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "用户优惠券编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userCouponId;

    /**
     * 订单关闭类型：0 用户取消关闭 1 超时自动关闭 2 支付失败关闭 3 退款成功关闭
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单关闭类型：0用户取消关闭1超时自动关闭2支付失败关闭3退款成功关闭")
    private Long closeType;

    @ApiModelProperty(value = "商品类型：0实物1话费充值2油卡充值3视频会员充值4积分兑换券")
    private Long productType;
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
     * 发货时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "发货时间")
    private Date deliveryTime;

    /**
     * 积分抵扣金额
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "积分抵扣金额")
    private BigDecimal integralDeduct;


    @ApiModelProperty(value = "撤销发货记录标识 0 无撤销数据 1 有撤销数据")
    private Integer sendReturnDataFlag = 0;

    @ApiModelProperty(value = "优惠金额")
    private BigDecimal discountAmount;

}
