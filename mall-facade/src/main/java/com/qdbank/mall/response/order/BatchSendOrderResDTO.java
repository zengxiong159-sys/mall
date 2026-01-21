package com.qdbank.mall.response.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName BatchSendOrderResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/9/16 16:26
 * @Version 1.0
 **/
@Data
public class BatchSendOrderResDTO {
    @ApiModelProperty(value = "成功笔数")
    private Long successCount;
    @ApiModelProperty(value = "失败笔数")
    private Long failCount;
}
