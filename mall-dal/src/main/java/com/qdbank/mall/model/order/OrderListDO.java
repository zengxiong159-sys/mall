package com.qdbank.mall.model.order;

import com.qdbank.mall.model.skustock.SkustockDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * OrderListDO
 *
 * @author shaoshihang
 * @date 2021/3/10 14:10
 * @since 1.0.0
 */
@Data
public class OrderListDO extends OrderDO{
    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private SkustockDO skustockDO;
}
