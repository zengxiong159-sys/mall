package com.qdbank.mall.request.schedule;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ScheduleJobLogLikeQueryReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/31 9:53
 * @Version 1.0
 **/
@Data
public class ScheduleJobLogLikeQueryReqDTO extends PageParams {
    @ApiModelProperty("任务ID")
    private Long jobId;
}
