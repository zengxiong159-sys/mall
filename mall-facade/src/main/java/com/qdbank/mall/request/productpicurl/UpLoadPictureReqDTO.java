package com.qdbank.mall.request.productpicurl;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * UpdatePicturesReqDTO
 *
 * @author shaoshihang
 * @date 2021/3/4 17:49
 * @since 1.0.0
 */
@Data
public class UpLoadPictureReqDTO {

    /**
     * 商品详情图片
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品详情图片",required = true)
    private MultipartFile file;

    /**
     * 文件来源 0 规格图片 1 商品图册 2 商品详情图片
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "文件来源: 0规格图片 1商品图册 2商品详情图片")
    private String fileSource;

    /**
     * 主图标识：0 主图 1 非主图
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主图标识：0主图 1非主图")
    private Long mainFlag;


}
