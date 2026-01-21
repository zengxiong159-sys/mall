package com.qdbank.mall.request.position;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdatePositionReqDTO {
    @ApiModelProperty(value = "金刚位编号")
    @JsonSerialize(using = ToStringSerializer.class)
    Long id;
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

    @ApiModelProperty("图片更新标识：0 不更新 1更新")
    private int picUpdateFlag = 0;
}
