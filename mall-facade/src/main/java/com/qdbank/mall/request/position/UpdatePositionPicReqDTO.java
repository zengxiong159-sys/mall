package com.qdbank.mall.request.position;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
@Data
public class UpdatePositionPicReqDTO extends UpdatePositionReqDTO {
    /**
     * 图片url
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "金刚位图片",required = true)
    private MultipartFile file;
}
