package com.qdbank.mall.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName CouponMQDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/5/18 19:12
 * @Version 1.0
 **/
@Data
public class CouponMQBO {
    @ApiModelProperty(value = "卡券类别 微信支付：WeChat 积分商城：Mall")
    private String couponCategory = "Mall";
    @ApiModelProperty(value = "卡券Id")
    private String couponId;
    @ApiModelProperty(value = "面额")
    private BigDecimal couponAmount;
    @ApiModelProperty(value = "使用门槛")
    private String transactionMinimum;
    @ApiModelProperty(value = "券名称")
    private String couponName;
    @ApiModelProperty(value = "券可用开始时间")
    private String availableBeginTime;
    @ApiModelProperty(value = "券可用结束时间")
    private String availableEndTime;
    @ApiModelProperty(value = "卡券状态 10 未生效 ; 20 未使用; 30 已失效;  40 已使用; 50 已作废")
    private String status;
    @ApiModelProperty(value = "显示文案（微信券）")
    private String couponNotice;
    @ApiModelProperty(value = "商城优惠券类型 0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private String mallCouponType;
    @ApiModelProperty(value = "优惠券使用说明")
    private String description;
    @ApiModelProperty(value = "通联客户号")
    private String custNo;
    @ApiModelProperty(value = "优惠券发放时间")
    private String createTime;
    @ApiModelProperty(value = "操作类型: insert 插入  delete 删除")
    private String operateType;
    @ApiModelProperty(value = "商品类型：1话费充值2油卡充值3视频会员充值")
    private String productType;
}
