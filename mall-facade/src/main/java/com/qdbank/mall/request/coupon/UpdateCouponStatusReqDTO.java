package com.qdbank.mall.request.coupon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName UpdateCouponStatusReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/23 16:58
 * @Version 1.0
 **/
@Data
public class UpdateCouponStatusReqDTO {
    @NotNull
    @ApiModelProperty(value = "优惠券编号",required = true)
    private Long couponId;

    /**
     * 券商品状态
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "券商品状态:0 待上架 1 已上架 2 已下架")
    private Long productStatus;

    /**
     * 批次状态
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "批次状态:0 待发放1 已发放2 已过期 3 已作废 4 待生效 5 已生效 6 已失效")
    private Long batchStatus;


    /**
     * 优惠券类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    @NotNull
    private Long couponType;

    /**
     * 批次号
     */
    @ApiModelProperty(value = "批次号")
    private String batchNo;

    /**
     * 优惠券有效期开始时间
     */
    @ApiModelProperty(value = "优惠券有效期开始时间")
    private Date startTime;

    /**
     * 优惠券有效期结束时间
     */
    @ApiModelProperty(value = "优惠券有效期结束时间")
    private Date endTime;

    /**
     * 优惠券发放时间
     */
    @ApiModelProperty(value = "优惠券发放时间")
    private Date sendTime;
}
