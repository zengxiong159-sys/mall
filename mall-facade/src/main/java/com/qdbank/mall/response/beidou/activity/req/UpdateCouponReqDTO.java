package com.qdbank.mall.response.beidou.activity.req;

import cn.hutool.core.date.DatePattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author zengxiong
 * @Description 权益绑定请求DTO
 * @Date 2021/11/24 18:03
 */
@Data
public class UpdateCouponReqDTO {

    /**
     * 券id
     */
    @NotNull
    private Long couponId;

    /**
     * 主活动id
     */
    @NotEmpty
    private String mainActivityId;

    /**
     * 子活动id
     */
    @NotEmpty
    private String subActivityId;

    /**
     * 活动结束时间
     */
    @NotNull
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private Date activityEndDate;

    /**
     * 批次状态:0 待发放1 已发放2 已过期 3 已作废 4 待生效 5 已生效 6 已失效
     */
    private Long batchStatus;

}
