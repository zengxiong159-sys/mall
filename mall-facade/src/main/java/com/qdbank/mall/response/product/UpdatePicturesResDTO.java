package com.qdbank.mall.response.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * UpdatePicturesResDTO
 *
 * @author shaoshihang
 * @date 2021/3/4 17:52
 * @since 1.0.0
 */
@Data
public class UpdatePicturesResDTO {
    /**
     * 商品详情图片URL
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品详情图片URL")
    private String proPicUrl;

}
