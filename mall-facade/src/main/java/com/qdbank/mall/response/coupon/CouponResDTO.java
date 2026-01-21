package com.qdbank.mall.response.coupon;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.qdbank.mall.response.prefecture.PrefectureDetailResDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName CouponResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/12 14:30
 * @Version 1.0
 **/
@Data
public class CouponResDTO {
    /**
     * 券商品编号
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
     * 优惠券名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券名称")
    private String couponName;

    /**
     * 优惠券类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private Long couponType;

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
     * 券商品状态
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "券商品状态:0 待上架 1 已上架")
    private Long productStatus;

    /**
     * 批次状态
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "批次状态:0 待发放1 已发放2 已过期 3 已作废 4 待生效 5 已生效 6 已失效")
    private Long batchStatus;

    /**
     * "指定商品类型
     0 话费
     "
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "指定商品类型: 0 实物 1 话费充值 2 油卡充值 3 视频会员充值")
    private Long productType;

    /**
     * 指定商品编号
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "指定商品规格编号")
    private Long productSkuId;

    /**
     * 上传白名单文件名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "上传白名单文件名称")
    private String fileName;

    /**
     * 上传白名单文件URL
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "上传白名单文件URL")
    private String fileUrl;

    /**
     * 优惠券过期天数
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券过期天数")
    private Long expireDays;

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

    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    /**
     * 更新人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新人")
    private String updatedBy;

    /**
     * 已使用量
     */
    @ApiModelProperty(value = "已使用量")
    private Long usedCount;

    /**
     * 已过期量
     */
    @ApiModelProperty(value = "已过期量")
    private Long expiredCount;

    /**
     * 已兑换量
     */
    @ApiModelProperty(value = "已兑换量")
    private Long exchangedCount;

    /**
     * 商品售价
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价")
    private BigDecimal productPrice;

    /**
     * 关联积分券商品ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "关联积分券商品ID")
    private Long relCouponId;

    /**
     * 积分兑换批次券指定积分兑换券信息
     */
    @ApiModelProperty(value = "积分兑换批次券指定积分兑换券信息")
    private RelCouponInfo relCouponInfo;

    /**
     * 专区id
     */
    @ApiModelProperty(value = "专区id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long prefectureId;

    @ApiModelProperty(value = "主活动id")
    private String mainActivityId;

    @ApiModelProperty(value = "子活动id")
    private String subActivityId;

    @ApiModelProperty(value = "专区商品列表")
    private PrefectureDetailResDTO prefectureDetailResDTO;

}
