package com.qdbank.mall.model.product;

import com.qdbank.mall.model.productpicurl.ProductPicUrlDO;
import com.qdbank.mall.model.skustock.SkustockDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ProductSkuDO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/6 21:44
 * @Version 1.0
 **/
@Data
public class ProductSkuDO extends ProductDO{
    @ApiModelProperty(value = "规格属性")
    List<SkustockDO> skustocks;
    @ApiModelProperty(value = "商品图册")
    List<ProductPicUrlDO> picUrls;
}
