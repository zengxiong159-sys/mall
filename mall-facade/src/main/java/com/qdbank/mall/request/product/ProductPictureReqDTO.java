package com.qdbank.mall.request.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CreateProductDetailPictureReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/15 17:27
 * @Version 1.0
 **/
@Data
public class ProductPictureReqDTO {
    /**
     * 图片url地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "图片url地址")
    private String picUrl;

    /**
     * 主图标识：0 主图 1 非主图
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主图标识：0主图 1非主图")
    private Long mainFlag;
}
