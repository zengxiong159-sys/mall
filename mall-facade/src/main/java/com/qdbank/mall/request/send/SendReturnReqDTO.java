package com.qdbank.mall.request.send;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName SendReturnReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/4/12 13:32
 * @Version 1.0
 **/
@Data
public class SendReturnReqDTO {

    /**
     * 订单号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单主键")
    private Long orderId;
    /**
     * 撤销原因
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "撤销原因")
    private String returnReason;
}
