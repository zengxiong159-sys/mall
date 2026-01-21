package com.qdbank.mall.request.refundsetting;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName RefundSettingReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/12 13:53
 * @Version 1.0
 **/
@Data
public class RefundSettingReqDTO {

    /**
     * 退款原因
     *
     * @mbg.generated
     */
    @NotNull
    @Length(max = 25)
    @ApiModelProperty(value = "退款原因",required = true)
    private String refundReason;
    @ApiModelProperty(value = "0 未开启 1 开启")
    private Long status;
}
