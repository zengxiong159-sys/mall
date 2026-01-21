package com.qdbank.mall.request.position;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
@Data
public class PositionReqDTO {


    /**
     * 活动名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "金刚位名称")
    private String positionName;

    /**
     * 优先级
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优先级")
    private Long positionLevel;

    /**
     * 活动调换url
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "跳转 url")
    private String jumpUrl;

    /**
     * 图片url
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "图片url")
    private String picUrl;



    private static final long serialVersionUID = 1L;

    /**
     * 图片url
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "金刚位图片",required = true)
    private MultipartFile file;
}
