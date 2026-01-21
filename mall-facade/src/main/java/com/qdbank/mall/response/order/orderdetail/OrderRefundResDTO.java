package com.qdbank.mall.response.order.orderdetail;

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
 * @Date 2021/4/28 10:01
 * @Version 1.0
 * 实物订单中退款信息
 **/
@Data
public class OrderRefundResDTO {
    /**
     * 退款流水号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款流水号")
    @JsonSerialize(using = ToStringSerializer.class)
    private String refundSerial;

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
     * "退款类型
     0 仅退款(无需退货)
     1 退货退款"
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'退款类型0仅退款(无需退货)1退货退款'")
    private Long refundType;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
