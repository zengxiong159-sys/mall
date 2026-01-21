package com.qdbank.mall.model.orderrefund;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderRefundExportResDO
 *
 * @author shaoshihang
 * @date 2021/3/11 9:17
 * @since 1.0.0
 */
@Data
public class OrderRefundExportResDO {
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
    private Long productType;

    /**
     * 退款流水号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款流水号")
    private Long refundSerial;

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
    private Long custMobile;

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
    private Long refundStatus;
    /**
     * 退款总金额折算价
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款总金额折算价")
    private BigDecimal refundAmount;
    /**
     * 退款积分：订单积分
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款积分：订单积分")
    private Long refundIntegration;
    /**
     * "退款类型
     0 仅退款(无需退货)
     1 退货退款"
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'退款类型0仅退款(无需退货)1退货退款'")
    private Long refundType;
    /**
     * 退款原因
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款原因")
    private String reason;

    /**
     * 商品类目
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品类目")
    private String ctateGoryName;

    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品编号")
    private Long productId;
    /**
     * 商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;
    /**
     * 商品属性:JSON格式
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品属性:JSON格式")
    private String productSpData;

    /**
     * 商品数量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品数量")
    private Long productCount;
    /**
     * 商品售价中现金金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价中现金金额")
    private BigDecimal productCash;

    /**
     * 商品售价中积分值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价中积分值")
    private Long productIntegration;

    /**
     * 商户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号")
    private Long merchantNo;
    /**
     * 商户名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 订单编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单编号")
    private String orderSn;


}
