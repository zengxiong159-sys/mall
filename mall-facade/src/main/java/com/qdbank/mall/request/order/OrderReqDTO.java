package com.qdbank.mall.request.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName OrderReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/16 11:55
 * @Version 1.0
 **/
@Data
public class OrderReqDTO {



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
     * 订单现金:包含商品售价中现金金额+运费金额-优惠金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单现金:包含商品售价中现金金额+运费金额-优惠金额")
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
     * 商品类型：0 实物1 话费充值2 油卡充值3 视频会员充值4 积分兑换券
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品类型：0实物1话费充值2油卡充值3视频会员充值4积分兑换券")
    private Long productType;

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
}
