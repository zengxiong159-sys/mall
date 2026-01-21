package com.qdbank.mall.response.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ProductDetailPictureResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/16 17:58
 * @Version 1.0
 **/
@Data
public class ProductDetailPictureResDTO {
    /**
     * 图片url地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品详情图片url地址")
    private String picUrl;
}
