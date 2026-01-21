package com.qdbank.mall.request.advertisement;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @ClassName UpdateAdvertisementPicReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/1 9:13
 * @Version 1.0
 **/
@Data
public class UpdateAdvertisementPicReqDTO extends UpdateAdvertisementReqDTO {
    /**
     * 广告开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "广告开始时间")
    @DateTimeFormat(pattern= DatePattern.NORM_DATETIME_PATTERN)
    private Date startTime;

    /**
     * 广告结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "广告结束时间")
    @DateTimeFormat(pattern= DatePattern.NORM_DATETIME_PATTERN)
    private Date endTime;
    /**
     * 图片
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "图片文件")
    private MultipartFile file;

}
