package com.qdbank.mall.request.productpicurl;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ProductPicUrlReqDTO
 * @Description 商品图片URL
 * @Author ningyuehuai
 * @Date 2020/12/12 10:16
 * @Version 1.0
 **/
@Data
public class ProductPicUrlReqDTO implements Serializable {
    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品编号")
    private Long productId;

    /**
     * 主图标识：0 主图 1 非主图
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主图标识：0 主图 1 非主图")
    private Short mainFlag;

    /**
     * 图片url地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "图片url地址")
    private String picUrl;

}
