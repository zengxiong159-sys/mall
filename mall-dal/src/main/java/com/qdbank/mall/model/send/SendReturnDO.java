package com.qdbank.mall.model.send;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class SendReturnDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 订单号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单号")
    private String orderSn;

    /**
     * 物流公司名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "物流公司名称")
    private String deliveryCompanyName;

    /**
     * 物流单号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "物流单号")
    private String deliverySn;

    /**
     * 撤销原因
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "撤销原因")
    private String returnReason;

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
     * 状态：0 已发货 1 撤销发货
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态：0已发货1撤销发货")
    private Long returnStatus;

    private static final long serialVersionUID = 1L;

}