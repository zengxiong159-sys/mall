package com.qdbank.mall.model.usercoupon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName UserIntegrationImportDO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/7/6 11:47
 * @Version 1.0
 **/
@Data
public class UserIntegrationImportDO {
    /**
     * 用户优惠券编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "用户优惠券编号")
    private Long userCouponId;



    /**
     * 订单编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    /**
     * 积分券售价(积分值)
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分券售价(积分值)")
    private Long orderIntegration;

    private String couponName;

    /**
     * "用户使用券状态 :0 待使用1 已使用2 已过期3 已作废"
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'用户使用券状态:0待使用1已使用2已过期3已作废'")
    private String status;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 优惠券过期时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券过期时间")
    private Date expireDate;

    /**
     * 更新时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 通联核心客户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "通联核心客户号")
    private String custNo;
}
