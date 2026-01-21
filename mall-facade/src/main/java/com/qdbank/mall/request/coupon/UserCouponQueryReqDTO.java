package com.qdbank.mall.request.coupon;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author zengxiong
 * @Description 用户持券信息请求DTO
 * @Date 2021/8/19 15:01
 */
@Data
public class UserCouponQueryReqDTO extends PageParams implements Serializable {
    private static final long serialVersionUID = -4738098972304768525L;

    /**
     * 通联核心客户号
     */
    @ApiModelProperty(value = "通联核心客户号")
    private String custNo;

    /**
     * 批次号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "批次号")
    private String batchNo;

    /**
     * 优惠券名称
     */
    @ApiModelProperty(value = "优惠券名称")
    private String couponName;

    /**
     * 优惠券类型
     */
    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private Long couponType;

    /**
     * 用户使用券状态
     */
    @ApiModelProperty(value = "用户使用券状态: 0待使用 1已使用 2已过期 3已作废")
    private Long status;

    /**
     * 发放开始时间
     */
    @ApiModelProperty(value = "发放开始时间")
    private Date startSendTime;

    /**
     * 发放结束时间
     */
    @ApiModelProperty(value = "发放结束时间")
    private Date endSendTime;
}
