package com.qdbank.mall.model.productskuattrrelation;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author zengxiong
 * @Description 商品规格和规格属性关系
 * @Date 2021/6/7 17:31
 */
@Data
public class ProductSkuAttrRelationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -3816441382961949688L;

    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private Long productId;

    /**
     * 规格信息id
     */
    @ApiModelProperty(value = "规格信息id")
    private Long skuStockId;

    /**
     * 规格属性id
     */
    @ApiModelProperty(value = "规格属性id")
    private Long attributeId;
}
