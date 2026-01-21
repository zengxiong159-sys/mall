package com.qdbank.mall.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName TimeDO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/5/19 15:17
 * @Version 1.0
 **/
@Data
public class TimeDO {
    /**
     * 申请开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "申请开始时间")
    private Date startTime;

    /**
     * 申请结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "申请结束时间")
    private Date endTime;
}
