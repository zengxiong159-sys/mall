package com.qdbank.mall.response.send;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName SendReturnResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/4/12 13:35
 * @Version 1.0
 **/
@Data
public class SendReturnResDTO {
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
     * 状态：0 已发货 1 撤销发货
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态：0已发货1撤销发货")
    private Long returnStatus;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
