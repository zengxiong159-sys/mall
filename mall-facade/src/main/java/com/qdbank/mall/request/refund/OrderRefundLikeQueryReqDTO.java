package com.qdbank.mall.request.refund;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName OrderRefundLikeQueryReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/16 16:48
 * @Version 1.0
 **/
@Data
public class OrderRefundLikeQueryReqDTO extends PageParams {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 订单id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单id")
    private Long orderId;

    /**
     * 收货地址id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "收货地址id")
    private Long companyAddressId;

    /**
     * 商品id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品id")
    private Long productId;

    /**
     * 订单编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    /**
     * 用户姓名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "用户姓名")
    private String memberUsername;

    /**
     * 客户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "客户号")
    private String custNo;

    /**
     * 退款金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款金额")
    private Long returnAmount;

    /**
     * 退货人姓名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退货人姓名")
    private String returnName;

    /**
     * 退货人电话
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退货人电话")
    private String returnPhone;

    /**
     * 申请状态 0 已申请 1 退货中 2 已完成 3 已拒绝
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "申请状态 0 已申请 1 退货中 2 已完成 3 已拒绝")
    private Long status;

    /**
     * 处理时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "处理时间")
    private Date handleTime;


    /**
     * 商品二级分类编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品二级分类编号")
    private Long categoryId2;


    /**
     * 退款流水号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款流水号")
    private String refundSerial;

    /**
     * 退款流水号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款流水号")
    private String refundSerialNo;

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
     * 申请开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "申请开始时间")
    private Date startTime;

    /**
     * 申请结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "申请结束时间")
    private Date endTime;

    /**
     * 商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;



    /**
     * 退货数量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退货数量")
    private Long productCount;

    /**
     * 商品价格
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品价格")
    private Long productPrice;

    /**
     * 商品实际支付金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品实际支付金额")
    private Long productRealPrice;

    /**
     * 退款原因
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款原因")
    private String reason;

    /**
     * 退款类型 0 仅退款(无需退货) 1退货退款
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款类型 0 仅退款(无需退货) 1退货退款")
    private Long refundType;

    /**
     * 处理备注
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "处理备注")
    private String handleNote;

    /**
     * 处理人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "处理人")
    private String handleMan;

    /**
     * 收货人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "收货人")
    private String receiveMan;

    /**
     * 收货时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "收货时间")
    private Date receiveTime;

    /**
     * 收获备注
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "收获备注")
    private String receiveNote;

    /**
     * 退款说明
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款说明")
    private String refundNote;

    /**
     * 运费
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运费")
    private BigDecimal transferCharge;

    /**
     * 商户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号",hidden = true)
    private Long merchantNo;

    /**
     * 商户名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户名称",hidden = true)
    private String merchantName;
    /**
     * 商品类型：0 实物1 话费充值2 油卡充值3 视频会员充值4 积分兑换券
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品类型：0实物1话费充值2油卡充值3视频会员充值4积分兑换券")
    private Long productType;

    @ApiModelProperty(value = "错误标识")
    private String errorFlag;

    @ApiModelProperty(value = "退款失败状态 0:失败，1:正常")
    private String errorStatus;
}
