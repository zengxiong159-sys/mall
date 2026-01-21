package com.qdbank.mall.request.refund;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * OrderTypeReqDTO
 *
 * @author shaoshihang
 * @date 2021/3/25 10:53
 * @since 1.0.0
 */
@Data
public class OrderTypeReqDTO {
    /**
     * 请求标志
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "请求标志")
    private String flag;
    /**
     * 订单号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单号")
    private String ordId;
    /**
     * 流水号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "流水号")
    private String id;
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
    private String accessSignFlag ;
    /**
     * 准入标志
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "准入标志")
    private String accessSign;
    /**
     * 支付类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "支付类型")
    private String payFlage;
}
