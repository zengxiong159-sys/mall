package com.qdbank.mall.request.external;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author sunhaoran
 * @Date 2022/5/13 10:20
 * @Version 1.0
 */
@Data
public class ReviAgreeReqDTO {

    @ApiModelProperty(value = "渠道编码",required = true)
    @NotBlank(message ="渠道编码不能为空" )
    private String channelId;

    @ApiModelProperty(value = "场景编码",required = true)
    @NotBlank(message ="场景编码不能为空" )
    private String sceneId;

}
