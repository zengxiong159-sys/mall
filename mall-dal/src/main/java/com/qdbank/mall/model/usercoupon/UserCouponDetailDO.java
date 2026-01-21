package com.qdbank.mall.model.usercoupon;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserCouponDetailDO implements Serializable {

    /**
     * 用户优惠券编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "用户优惠券编号")
    private Long userCouponId;

    /**
     * 优惠券类型：0 积分兑换券1 指定商品免费兑换券
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private Long couponType;

    /**
     * 订单编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    /**
     * 通联核心客户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "通联核心客户号")
    private String custNo;

    /**
     * 券商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "券商品编号")
    private Long couponId;

    /**
     * 批次号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "批次号")
    private String batchNo;

    /**
     * 积分券售价(积分值)
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分券售价(积分值)")
    private Long orderIntegration;

    /**
     * 积分券售价(积分值)
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券名称")
    private String couponName;

    /**
     * "用户使用券状态 :0 待使用1 已使用2 已过期3 已作废"
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'用户使用券状态:0待使用1已使用2已过期3已作废'")
    private Long status;



    /**
     * 本批次白名单数量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "本批次白名单数量")
    private Long allTotal;

    /**
     * 商品售价中积分量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价中积分量")
    private Long productIntegration;

    /**
     * 优惠券面值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券面值")
    private BigDecimal couponAmount;

    /**
     * 优惠券描述
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券描述")
    private String couponDescription;

    /**
     * 券商品状态：0 待上架 1 已上架
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "券商品状态：0待上架1已上架")
    private Long productStatus;

    /**
     * 批次状态:0 待发放1 已发放2 已过期 3 已作废 4 待生效 5 已生效 6 已失效
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "批次状态:0 待发放1 已发放2 已过期 3 已作废 4 待生效 5 已生效 6 已失效")
    private Long batchStatus;

    /**
     * "指定商品类型0 实物 1 话费充值 2 油卡充值 3 视频会员充值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'指定商品类型0实物1话费充值2油卡充值3视频会员充值")
    private Long productType;

    /**
     * 指定商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "指定商品编号")
    private Long productId;

    /**
     * 指定商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "指定商品名称")
    private String productName;

    /**
     * 指定商品规格编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "指定商品规格编号")
    private Long productSkuId;


    /**
     * 优惠券过期天数
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券过期天数")
    private Long expireDays;

    /**
     * 优惠券过期时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券过期时间")
    private Date expireDate;

    /**
     * 优惠券有效期开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券有效期开始时间")
    private Date startTime;

    /**
     * 优惠券有效期结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券有效期结束时间")
    private Date endTime;

    /**
     * 优惠券发放时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券发放时间")
    private Date sendTime;


    /**
     *
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券发放时间")
    private Date createTime;

    /**
     * 专区id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "专区id")
    private Long prefectureId;

    /**
     * 主活动id
     */
    @ApiModelProperty(value = "主活动id")
    private String mainActivityId;

    /**
     * 子活动id
     */
    @ApiModelProperty(value = "子活动id")
    private String subActivityId;

    /**
     * 发放方式: 0:用户兑换 1:行方发放
     */
    @ApiModelProperty(value = "发放方式: 0:用户兑换 1:行方发放")
    private Long distributeWay;

}