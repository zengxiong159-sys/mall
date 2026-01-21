package com.qdbank.mall.model.orderrefund;

import com.qdbank.mall.model.TimeDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderRefundLikeQueryDTO
 *
 * @author shaoshihang
 * @date 2021/3/12 16:13
 * @since 1.0.0
 */
@Data
public class OrderRefundLikeQueryDO extends TimeDO {

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
     * 退款类型 0 仅退款(无需退货) 1退货退款
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款类型 0 仅退款(无需退货) 1退货退款")
    private Long refundType;



    /**
     * 商户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号",hidden = true)
    private Long merchantNo;

    /**
     * 商品类型：0 实物1 话费充值2 油卡充值3 视频会员充值4 积分兑换券
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品类型：0实物1话费充值2油卡充值3视频会员充值4积分兑换券")
    private Long productType;

    /**
     * 商户名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户名称",hidden = true)
    private String merchantName;

    @ApiModelProperty(value = "退款失败状态 0:失败，1:正常")
    private String errorStatus;

}
