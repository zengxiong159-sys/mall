package com.qdbank.mall.request.schedule;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ScheduleLikeQueryDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/30 16:05
 * @Version 1.0
 **/
@Data
public class ScheduleLikeQueryReqDTO extends PageParams {
    @ApiModelProperty("bean名称")
    private String beanName;
}
