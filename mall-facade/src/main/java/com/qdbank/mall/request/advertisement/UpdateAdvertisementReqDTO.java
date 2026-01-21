package com.qdbank.mall.request.advertisement;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName AdvertisementUpdateReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/11 10:42
 * @Version 1.0
 **/
@Data
public class UpdateAdvertisementReqDTO {
    @NotNull
    @ApiModelProperty(value = "广告编号")
    private Long id;
    /**
     * 优先级
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优先级")
    private Long advertismentLevel;

    /**
     * 广告开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "广告开始时间")
    private Date startTime;

    /**
     * 广告结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "广告结束时间")
    private Date endTime;

    /**
     * 广告跳转链接
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "广告跳转链接")
    private String jumpUrl;


    @ApiModelProperty("图片更新标识：0 不更新 1更新")
    private int picUpdateFlag = 0;
}
