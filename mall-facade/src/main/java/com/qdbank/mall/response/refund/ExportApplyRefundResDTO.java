package com.qdbank.mall.response.refund;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ExportApplyRefundResDTO
 *
 * @author shaoshihang
 * @date 2021/3/16 15:37
 * @since 1.0.0
 */
@Data
public class ExportApplyRefundResDTO {
    /**
     * "商品类型：
     0 实物
     1 话费充值
     2 油卡充值
     3 视频会员充值
     4 积分兑换券
     "
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'商品类型：0实物1话费充值2油卡充值3视频会员充值4积分兑换券'")
    private String productType;
    /**
     * 退款流水号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款流水号")
    private String refundSerial;
    /**
     * 通联客户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "通联客户号")
    private String custNo;
    /**
     * "通联客户号对应的银行预
     留手机号"
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'通联客户号对应的银行预留手机号'")
    private String custMobile;
    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    /**
     * 退款完成时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款完成时间")
    private String handleFinishTime;
    /**
     * "退款状态
     0 待审核
     1 审核通过
     2 退款成功
     3审核不通过"
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'退款状态0待审核1审核通过2退款成功3审核不通过'")
    private String refundStatus;
    /**
     * 退款总金额折算价
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款总金额折算价")
    private String refundAmount;
    /**
     * 退款积分：订单积分
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款积分：订单积分")
    private String refundIntegration;
    /**
     * "退款类型
     0 仅退款(无需退货)
     1 退货退款"
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'退款类型0仅退款(无需退货)1退货退款'")
    private String refundType;
    /**
     * 退款原因
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款原因")
    private String reason;
    /**
     * ctateGoryName
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "ctateGoryName")
    private String ctateGoryName;
    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品编号")
    private String productId;
    /**
     * 商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;
    /**
     * 商品规格
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品规格")
    private String productSpData;
    /**
     * 商品数量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品数量")
    private String productCount;
    /**
     * 商品售价现金金额（元）
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价现金金额（元）")
    private String productCash;
    /**
     * 商品售价积分量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价积分量")
    private String productIntegration;
    /**
     * 商户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号")
    private String merchantNo;
    /**
     * 商户名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    /**
     * 关联订单编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "关联订单编号")
    private String orderSn;







}
