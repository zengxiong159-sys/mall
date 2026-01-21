package com.qdbank.mall.model.order;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderStatusDO extends BaseDO implements Serializable {
    /**
     * 订单主键 
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单主键")
    private Long orderId;

    @ApiModelProperty(value = "订单前状态")
    private Long beforeStatus;

    @ApiModelProperty(value = "订单后状态")
    private Long afterStatus;

    @ApiModelProperty(value = "订单关闭前状态")
    private Long beforeCloseType;

    @ApiModelProperty(value = "订单关闭后状态")
    private Long afterCloseType;

    @ApiModelProperty(value = "订单审核前状态")
    private Long beforeApproveStatus;

    @ApiModelProperty(value = "订单审核后状态")
    private Long afterApproveStatus;



    private static final long serialVersionUID = 1L;


}