package com.qdbank.mall.request.activity;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName UploadActivityPicReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/31 18:45
 * @Version 1.0
 **/
@Data
public class UploadActivityPicReqDTO extends UpdateActivityReqDTO{
    /**
     * 图片url
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "活动图片")
    @NotNull
    private MultipartFile file;

}
