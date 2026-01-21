package com.qdbank.mall.request.refund;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderRefundExportReqDTO
 *
 * @author shaoshihang
 * @date 2021/3/19 10:41
 * @since 1.0.0
 */
@Data
public class OrderRefundExportReqDTO {
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
    private Long refundSerial;

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
     * 提交时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "开始日期",required = true)
    @DateTimeFormat(pattern= DatePattern.NORM_DATE_PATTERN)
    private Date startTime;

    /**
     * 提交时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "结束日期",required = true)
    @DateTimeFormat(pattern= DatePattern.NORM_DATE_PATTERN)
    private Date endTime;

    /**
     * 商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;

    /**
     * 商品品牌
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品品牌")
    private String productBrand;

    /**
     * 商品属性：容量： 3L 颜色： 红色
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品属性：容量： 3L 颜色： 红色")
    private String productAttr;

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
     * 上传凭证图片url
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "上传凭证图片url")
    private String proofPics;

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
    @ApiModelProperty(value = "下载编号",hidden = true)
    private Long downloadId;
    @ApiModelProperty(value = "文件本地路径",hidden = true)
    private String fileLocalPath;
    @ApiModelProperty(value = "创建人",hidden = true)
    private String createdBy;
}
