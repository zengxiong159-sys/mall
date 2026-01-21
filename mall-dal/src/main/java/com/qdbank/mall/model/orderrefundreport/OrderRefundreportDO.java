package com.qdbank.mall.model.orderrefundreport;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class OrderRefundreportDO implements Serializable {
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
     * 通联核心用户姓名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "通联核心用户姓名")
    private String custName;

    /**
     * "通联客户号对应的银行预
     留手机号"
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'通联客户号对应的银行预留手机号'")
    private Long custMobile;

    /**
     * 订单编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单编号")
    private Long orderSn;

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
     * 商品数量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品数量")
    private Long productCount;

    /**
     * 规格编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规格编号")
    private Long productSkuId;

    /**
     * 商品一级分类编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品一级分类编号")
    private Long categoryId1;

    /**
     * 商品二级分类编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品二级分类编号")
    private Long categoryId2;

    /**
     * 商品三级分类编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品三级分类编号")
    private Long categoryId3;

    /**
     * 商品四级分类编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品四级分类编号")
    private Long categoryId4;

    /**
     * 支付现金
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "支付现金：实际支付现金(商品售价-券面值金额)部分不包含运费")
    private BigDecimal orderCash;

    @ApiModelProperty(value = "优惠券金额")
    private BigDecimal couponAmount;
    /**
     * 订单积分
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单积分")
    private Long orderIntegration;

    /**
     * 退款总金额折算价
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款总金额折算价：退款现金+运费+积分折算人民币金额（商品为现金+积分时才有积分折算人民币）")
    private BigDecimal refundAmount;

    /**
     * "退款现金：
     1 退款类型为仅退款：
     订单现金
     2 退款类型为退货退款：
     订单现金-运费金额"
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
     * 商户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号")
    private Long merchantNo;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 创建时间                                           
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间                                           
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

}