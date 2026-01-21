package com.qdbank.mall.model.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * OrderRefuntDO
 *
 * @author shaoshihang
 * @date 2021/3/9 9:46
 * @since 1.0.0
 */
@Data
public class OrderExportDO extends OrderDO implements Serializable {
    /**
     * 退款流水号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款流水号")
    private String refundSerial;

    /**
     * 商品属性:JSON格式
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品属性:JSON格式")
    private String productSpData;

    @ApiModelProperty(value = "商品标识：默认0:常规商品 1:达标专属礼")
    private Integer  identification;
}
