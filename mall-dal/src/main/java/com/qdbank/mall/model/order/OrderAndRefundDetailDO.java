package com.qdbank.mall.model.order;

import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.product.ProductDO;
import com.qdbank.mall.model.rechargestatusdetail.RechargeStatusDO;
import com.qdbank.mall.model.skustock.SkustockDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * OrderDetailsDO
 *
 * @author shaoshihang
 * @date 2021/3/9 16:56
 * @since 1.0.0
 */
@Data
public class OrderAndRefundDetailDO extends OrderDO{



    /**
     * 退款流水号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款流水号")
    private String refundSerial;


    /**
     * 退款总金额折算价
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款总金额折算价：退款现金+运费+积分折算人民币金额（商品为现金+积分时才有积分折算人民币）")
    private BigDecimal refundAmount;

    /**
     * "退款现金"
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'退款现金:不包含运费部分")
    private BigDecimal refundCash;

    /**
     * 运费金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运费金额")
    private BigDecimal freightAmount;

    /**
     * 退款积分：订单积分
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款积分：订单积分")
    private Long refundIntegration;

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
     * "退款状态
     0 待审核
     1 审核通过
     2 退款成功
     3审核不通过"
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'退款状态0待审核1审核通过2退款成功3审核不通过'")
    private Long refundStatus1;



    /**
     * 退款处理时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款处理时间")
    private Date handleStartTime;

    /**
     * 退款完成时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款完成时间")
    private Date handleFinishTime;

    /**
     * "处理结果：
     0 同意退款
     1 退款驳回"
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'处理结果：0同意退款1退款驳回'")
    private Long handleResult;

    /**
     * 退款原因
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款原因")
    private String reason;

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
     * 上传凭证图片url
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "上传凭证图片url")
    private String proofPics;

    /**
     * 管理员姓名(处理人姓名)
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "管理员姓名(处理人姓名)")
    private String adminName;

    /**
     * 管理员邮箱(处理人邮箱)
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "管理员邮箱(处理人邮箱)")
    private String email;

    /**
     * 退款说明
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款说明")
    private String refundNote;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款流水创建时间")
    private Date refundCreateTime;



}
