package com.qdbank.mall.response.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * OrderStatusResDTO
 *
 * @author shaoshihang
 * @date 2021/4/7 15:00
 * @since 1.0.0
 */
@Data
public class OrderStatusResDTO {
    /**
     * 待审核
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "待审核")
    private Long reviewed;


    /**
     * 代发货
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "代发货")
    private Long consignment;
}
