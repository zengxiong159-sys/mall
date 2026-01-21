package com.qdbank.mall.request.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ProductIdsReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/7/22 10:44
 * @Version 1.0
 **/
@Data
public class ProductIdsReqDTO {
    @ApiModelProperty(value = "商品id组合，使用逗号分割")
    private String productIds;
}
