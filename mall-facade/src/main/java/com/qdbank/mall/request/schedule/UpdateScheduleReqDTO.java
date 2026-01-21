package com.qdbank.mall.request.schedule;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UpdateScheduleReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/30 16:11
 * @Version 1.0
 **/
@Data
public class UpdateScheduleReqDTO extends ScheduleReqDTO {
    @NotNull
    @ApiModelProperty("任务id")
    private Long jobId;
    @ApiModelProperty("任务id")
    private Long status;
}
