package com.qdbank.mall.request.center;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateStatusCenterReqDTO {

    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 状态 0 停用 1 启用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态0停用1启用")
    private String status;
}
