package com.qdbank.mall.response.refundsetting;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName RefundSettingResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/12 13:53
 * @Version 1.0
 **/
@Data
public class RefundSettingResDTO {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 退款原因
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款原因")
    private String refundReason;

    /**
     * 是否可用 0 不可用 1 可用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "是否可用 0 不可用 1 可用")
    private Long status;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
}
