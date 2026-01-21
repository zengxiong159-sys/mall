package com.qdbank.mall.response.productpicurl;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UpLoadPicturesResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/15 20:23
 * @Version 1.0
 **/
@Data
public class UpLoadPictureResDTO {
    /**
     * 图片url地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "图片url地址")
    private String picUrl;
    @ApiModelProperty(value = "图片名称")
    private String fileName;
}
