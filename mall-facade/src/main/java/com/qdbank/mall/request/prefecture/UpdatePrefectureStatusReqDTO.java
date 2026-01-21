package com.qdbank.mall.request.prefecture;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UpdatePrefectureStatusReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/23 17:12
 * @Version 1.0
 **/
@Data
public class UpdatePrefectureStatusReqDTO {
    @ApiModelProperty(value = "专区编号",required = true)
    private Long id;
    @ApiModelProperty(value = "专区状态：0停用 1 启用")
    private Long status;
}
