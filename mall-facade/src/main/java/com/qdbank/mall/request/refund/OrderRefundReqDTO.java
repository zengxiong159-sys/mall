package com.qdbank.mall.request.refund;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName OrderRefundReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/16 16:26
 * @Version 1.0
 **/
@Data
public class OrderRefundReqDTO {
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
     * 商品图片url
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品图片url")
    private String productPic;

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
    private MultipartFile files;

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


}
