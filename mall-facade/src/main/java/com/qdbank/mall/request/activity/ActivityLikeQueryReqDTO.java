package com.qdbank.mall.request.activity;

import cn.hutool.core.date.DatePattern;
import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.ldap.PagedResultsControl;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName ActivityReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/11 16:52
 * @Version 1.0
 **/
@Data
public class ActivityLikeQueryReqDTO extends PageParams {

    @ApiModelProperty(value = "活动名称",required = true)
    private String activityName;


    @ApiModelProperty("活动时间：开始时间")
    @DateTimeFormat(pattern= DatePattern.NORM_DATETIME_PATTERN)
    private Date startTime;
    @ApiModelProperty("活动时间：结束时间")
    @DateTimeFormat(pattern=DatePattern.NORM_DATETIME_PATTERN)
    private Date endTime;

    /**
     * 活动状态
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态 0 停用 1启用")
   private Long status;
}
