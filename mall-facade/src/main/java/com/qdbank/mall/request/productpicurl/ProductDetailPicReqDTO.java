package com.qdbank.mall.request.productpicurl;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ProductDetailPicReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/16 9:41
 * @Version 1.0
 **/
@Data
public class ProductDetailPicReqDTO {
    /**
     * 图片url地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品详情图片url地址")
    private String picUrl;
}
