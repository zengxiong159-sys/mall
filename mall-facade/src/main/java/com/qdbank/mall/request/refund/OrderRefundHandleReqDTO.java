package com.qdbank.mall.request.refund;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * OrderRefundHandleReqDTO
 *
 * @author shaoshihang
 * @date 2021/3/12 9:51
 * @since 1.0.0
 */
@Data
public class OrderRefundHandleReqDTO {
    /**
     * 退款流水号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款流水号")
    private String refundSerialNo;

    /**
     * 区分驳回或退款：0驳回，1退款
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "区分驳回或退款：0驳回，1退款")
    private Long identification;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date createTime;

    /**
     * 修改时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改时间",hidden = true)
    private Date updateTime;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人",hidden = true)
    private String createdBy;

    /**
     * 更新人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新人",hidden = true)
    private String updatedBy;

    /**
     * 商户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号",hidden = true)
    private Long merchantNo;
}
