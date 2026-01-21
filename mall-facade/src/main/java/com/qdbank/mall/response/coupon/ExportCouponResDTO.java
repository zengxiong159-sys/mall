package com.qdbank.mall.response.coupon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @ClassName ExportCouponResDTO
 * @Description 优惠券数据导出Excel
 * @Author ningyuehuai
 * @Date 2021/3/10 10:13
 * @Version 1.0
 **/
@Data
public class ExportCouponResDTO {

    /**
     * 通联核心客户号
     *
     * @mbg.generated
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
     * "优惠券类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private String couponTypeValue;

    @ApiModelProperty(value = "优惠券编号")
    private String userCouponId;
    /**
     * 优惠券名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券名称")
    private String couponName;


    /**
     * 优惠券面值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券面值")
    private String couponAmount;

    /**
     * 优惠券发放时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券发放时间")
    private String sendTime;

    /**
     *
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'用户使用券状态:0 待使用 1已使用 2已过期")
    private String statusValue;

    /**
     * 更新时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    /**
     * 订单编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单编号")
    private String orderSn;


    /**
     * 指定商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "指定商品编号")
    private String productId;

    /**
     * 指定商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "指定商品名称")
    private String productName;

}
