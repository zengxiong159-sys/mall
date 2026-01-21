package com.qdbank.mall.model.orderrefund;

import com.qdbank.mall.model.skustock.SkustockDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderRefundExportDO
 *
 * @author shaoshihang
 * @date 2021/3/10 15:09
 * @since 1.0.0
 */
@Data
public class OrderRefundExportDO extends OrderRefundDO{
    /**
     * 商品售价中现金金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价中现金金额")
    private BigDecimal productCash;

    /**
     * 商品售价中积分值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价中积分值")
    private Long productIntegration;

    /**
     * 商品属性:JSON格式
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品属性:JSON格式")
    private String productSpData;
}
