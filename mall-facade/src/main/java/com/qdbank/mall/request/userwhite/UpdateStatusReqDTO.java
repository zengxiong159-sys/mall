package com.qdbank.mall.request.userwhite;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateStatusReqDTO {

    @ApiModelProperty(value = "用户编号")
    private Long id;

    @ApiModelProperty(value = "状态0 停用 1启用")
    private String status;
}
