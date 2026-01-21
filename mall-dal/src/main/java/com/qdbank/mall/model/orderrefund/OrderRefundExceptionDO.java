package com.qdbank.mall.model.orderrefund;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * OrderRefundExceptionDO
 *
 * @author shaoshihang
 * @date 2021/3/26 14:32
 * @since 1.0.0
 */
@Data
public class OrderRefundExceptionDO implements Serializable {
    /**
     * ID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "ID")
    private Long id;
    /**
     * 请求标志
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "请求标志")
    private String flag;
    /**
     * 第三方标识
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "第三方标识")
    private String channel;
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
    private String orderSn;
    /**
     * 存储标识
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "存储标识")
    private String accessSignFlag;
    /**
     * 币种
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "币种")
    private String currNum;
    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品编号")
    private String prodId;
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
    @ApiModelProperty(value = "通联客户号")
    private String custId;
    /**
     * 通联客户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分账户类型")
    private String accType;
    /**
     * 支付类型
     *
     * @mbg.generated
     */
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
     * 错误信息
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "错误信息")
    private String errorMessage;
}
