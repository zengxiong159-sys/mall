package com.qdbank.mall.request.position;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PositionLikeReqDTO {

    /**
     * 活动名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "金刚位名称")
    private String positionName;

    /**
     * 活动状态 0 停用 1 启用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "金刚位状态0停用1启用")
    private Long status;

}
