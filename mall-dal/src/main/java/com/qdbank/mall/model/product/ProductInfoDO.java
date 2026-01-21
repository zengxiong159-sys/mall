package com.qdbank.mall.model.product;

import com.qdbank.mall.model.productpicurl.ProductPicUrlDO;
import com.qdbank.mall.model.skustock.SkustockDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName ProductInfoDO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/2 11:55
 * @Version 1.0
 **/
@Data
public class ProductInfoDO extends ProductSkuDO{
    @ApiModelProperty(value = "模板名称")
    private String templateName;
    private BigDecimal productCashMin;
    private BigDecimal productCashMax;
    @ApiModelProperty(value = "商品图册")
    List<ProductPicUrlDO> picUrls;
    @ApiModelProperty(value = "商品标识：默认0:常规商品 1:达标专属礼")
    private Integer  identification;
}
