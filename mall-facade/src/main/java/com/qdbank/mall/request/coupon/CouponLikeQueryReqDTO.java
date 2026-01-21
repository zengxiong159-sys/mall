package com.qdbank.mall.request.coupon;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName CouponLikeQueryReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/12 15:04
 * @Version 1.0
 **/
@Data
public class CouponLikeQueryReqDTO extends PageParams {
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
    @ApiModelProperty(value = "优惠券类型")
    private Long couponType;

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
     * 券商品状态：0 待上架 1 已上架 2 已下架
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "券商品状态：0待上架 1已上架 2 已下架")
    private Long productStatus;

    /**
     * 批次状态:0 待发放1 已发放2 已过期 3 已作废 4 待生效 5 已生效 6 已失效
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "批次状态:0 待发放1 已发放2 已过期 3 已作废 4 待生效 5 已生效 6 已失效 ")
    private Long batchStatus;

    /**
     * 开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "开始时间")
    private Date startCreateTime;
    /**
     * 结束
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "结束时间")
    private Date endCreateTime;

    /**
     * 关联积分券商品ID
     */
    @ApiModelProperty(value = "关联积分券商品ID")
    private Long relCouponId;

    /**
     * 发放方式: 0:用户兑换 1:行方发放
     */
    @NotNull
    @ApiModelProperty(value = "发放方式: 0:用户兑换 1:行方发放")
    private Long distributeWay;
}
