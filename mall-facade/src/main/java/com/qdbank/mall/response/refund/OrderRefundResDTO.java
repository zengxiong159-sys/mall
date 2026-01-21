package com.qdbank.mall.response.refund;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName OrderRefundResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/17 19:24
 * @Version 1.0
 **/
@Data
public class OrderRefundResDTO {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 订单id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;

    /**
     * 收货地址id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "收货地址id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long companyAddressId;

    /**
     * 商品id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productId;

    /**
     * 规格编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规格编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productSkuId;

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

    @ApiModelProperty(value = "0L:待审核, 1L:审核通过,2L:退款成功,3L:审核不通过,4L:退款申请撤销")
    private Long status;

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
     * 退款原因
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款原因")
    private String reasonDesc;

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

    @ApiModelProperty(value = "退款流水号")
    private String refundSerialNo;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 商品属性:JSON格式
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品属性:JSON格式")
    private String productSpData;

    /**
     * 商户名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

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
     * 订单实付款(折算价)
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单实付款(折算价)")
    private BigDecimal payAmount;



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
     * 商户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号")
    private Long merchantNo;

    @ApiModelProperty(value = "实付金额")
    private BigDecimal orderCash;
    @ApiModelProperty(value = "积分")
    private Long orderIntegration;
    @ApiModelProperty(value = "支付类型")
    private Long payType;


    @ApiModelProperty(value = "商品图片url")
    private String picUrl;

    @ApiModelProperty(value = "商品规格")
    private String spData;

    /**
     * 商品售价中现金金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "单个商品售价中现金金额")
    private BigDecimal singleProductCash;

    /**
     * 商品售价中积分量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "单个商品售价中积分量")
    private Long singleProductIntegration;

    @ApiModelProperty(value = "退款失败状态 0:失败，1:正常")
    private String errorStatus;

    /**
     * 积分抵扣金额
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "积分抵扣金额")
    private BigDecimal integralDeduct;

}
