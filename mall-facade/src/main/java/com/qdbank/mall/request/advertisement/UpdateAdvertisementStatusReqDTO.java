package com.qdbank.mall.request.advertisement;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UpdateAdvertisementStatusReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/23 17:05
 * @Version 1.0
 **/
@Data
public class UpdateAdvertisementStatusReqDTO {
    @ApiModelProperty(value = "广告编号")
    @NotNull
    private Long id;
    @NotNull
    @ApiModelProperty(value = "广告状态 0 停用 1 启用")
    private Long status;
}
