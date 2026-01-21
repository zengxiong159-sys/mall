package com.qdbank.mall.model.usercoupon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class UserCouponImportDO implements Serializable {

/**
 *   reader.addHeaderAlias("通联客户号","custNo");
 *             reader.addHeaderAlias("下发批次号","batchNo");
 *             reader.addHeaderAlias("券名称","couponName");
 *             reader.addHeaderAlias("券面值","couponAmount");
 *             reader.addHeaderAlias("发放成功时间","sendTime");
 *             reader.addHeaderAlias("生效时间","startTime");
 *             reader.addHeaderAlias("失效时间","endTime");
 *             reader.addHeaderAlias("当前状态","batchStatus");
 *             reader.addHeaderAlias("状态更新时间","updateTime");
 *             reader.addHeaderAlias("关联订单号","orderSn");
 *             reader.addHeaderAlias("商品编号","productId");
 *             reader.addHeaderAlias("商品名称","productName");
 */

    /**
     * 订单编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单编号")
    private String orderSn;
    private String batchNo;
    private String couponName;
    private Date sendTime;
    private Date startTime;
    private Date endTime;
    private String batchStatus;
    private Date updateTime;
    private Long productId;
    private String productName;
    /**
     * 通联核心客户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "通联核心客户号")
    private String custNo;

//
//    /**
//     * "用户使用券状态 :0 待使用1 已使用2 已过期3 已作废"
//     *
//     * @mbg.generated
//     */
//    @ApiModelProperty(value = "'用户使用券状态:0待使用1已使用2已过期3已作废'")
//    private String status;

    private String couponType;
    @ApiModelProperty(value = "优惠券金额")
    private BigDecimal couponAmount;
    private static final long serialVersionUID = 1L;
}