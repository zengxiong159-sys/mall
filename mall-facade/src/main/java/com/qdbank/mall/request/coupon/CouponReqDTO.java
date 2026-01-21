package com.qdbank.mall.request.coupon;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName CouponReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/12 15:06
 * @Version 1.0
 **/
@Data
public class CouponReqDTO {
    /**
     * 优惠券名称
     *
     * @mbg.generated
     */
    @NotBlank
    @Length(max = 20)
    @ApiModelProperty(value = "优惠券名称")
    private String couponName;

    /**
     * 优惠券类型
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private Long couponType;

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
     * 0 话费
     * "
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
    @ApiModelProperty(value = "指定商品编号")
    private Long productId;

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
     * 优惠券有效期开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券有效期开始时间")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private Date startTime;

    /**
     * 优惠券有效期结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券有效期结束时间")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private Date endTime;

    @ApiModelProperty(value = "白名单文件")
    private MultipartFile file;

    /**
     * 指定积分兑换券商品编号
     */
    @ApiModelProperty(value = "指定积分兑换券商品编号")
    private Long relCouponId;

    /**
     * 指定积分兑换券商品编号
     */
    @ApiModelProperty(value = "指定专区编号")
    private Long prefectureId;

}
