package com.qdbank.mall.request.refund;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * OrderApplyRefundReqDTO
 *
 * @author shaoshihang
 * @date 2021/3/12 11:22
 * @since 1.0.0
 */
@Data
public class ApplyRefundReqDTO {
    /**
     * 读卡方式
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "读卡方式")
    private String inptTpye;
    /**
     * 交易金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "交易金额")
    private String transAmt;
    /**
     * 币种
     *
     * @mbg.generated
     */
    @NotBlank
    @ApiModelProperty(value = "币种")
    private String currNum;
    /**
     * 核准代码
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "核准代码")
    private String apprvCd;
    /**
     * 订单号
     *
     * @mbg.generated
     */
    @NotBlank
    @ApiModelProperty(value = "订单号")
    private String ordId;
    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @NotBlank
    @ApiModelProperty(value = "商品编号")
    private String prodId;
    /**
     * 交易类型
     *
     * @mbg.generated
     */
    @NotBlank
    @ApiModelProperty(value = "交易类型")
    private String getType;
    /**
     * 准入标志流水号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "准入标志流水号")
    private String accessSignid;
    /**
     * 第三方标识
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "第三方标识")
    private String channel;
    /**
     * 存储标识
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "存储标识")
    private String accessSignFlag;
    /**
     * 准入标志
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "准入标志")
    private String accessSign;
    /**
     * 渠道流水号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "渠道流水号")
    private String oriTransSer;
    /**
     * 渠道日期
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "渠道日期")
    private String oriTransDt;
    /**
     * 通联客户号
     *
     * @mbg.generated
     */
    @NotBlank
    @ApiModelProperty(value = "通联客户号")
    private String custId;
    /**
     * 积分账户类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分账户类型")
    private String acctType;

    /**
     * 支付类型
     *
     * @mbg.generated
     */
    @NotBlank
    @ApiModelProperty(value = "支付类型")
    private String queryType;
    /**
     * 积分调整值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分调整值")
    private String transe;
    /**
     * 商户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户号")
    private String merchantNumber;

    /**
     * 请求标志
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "请求标志")
    private String flag;

    /**
     * 支付页面完成后跳转第三方url
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "支付页面完成后跳转第三方url")
    private String reqURL;

    /**
     * "退款状态
     0 待审核
     1 审核通过
     2 退款成功
     3审核不通过"
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'退款状态0待审核1审核通过2退款成功3审核不通过'",hidden = true)
    private Long refundStatus;
}
