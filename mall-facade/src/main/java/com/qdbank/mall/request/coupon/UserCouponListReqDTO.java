package com.qdbank.mall.request.coupon;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName UserCouponListReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/5/20 17:50
 * @Version 1.0
 **/
@Data
public class UserCouponListReqDTO extends UserCouponReqDTO{
    @ApiModelProperty(value = "通联客户号")
    @NotNull(message = "通联客户号不能为空")
    private String custNo;
    /**
     * 有效期开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern= DatePattern.NORM_DATETIME_PATTERN)
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private Date startTime;

    /**
     * 有效期结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern= DatePattern.NORM_DATETIME_PATTERN)
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private Date endTime;
}
