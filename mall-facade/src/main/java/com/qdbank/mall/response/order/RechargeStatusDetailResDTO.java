package com.qdbank.mall.response.order;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName RechargeStatusDetailResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/15 18:53
 * @Version 1.0
 **/
@Data
public class RechargeStatusDetailResDTO {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 订单编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    /**
     * 排序优先级
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "排序优先级")
    private Long detailLevel;

    /**
     * 充值状态0 待支付 1 充值中 2 已完成 3 已关闭
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "0 待支付 6 充值中  3 已完成 4 已关闭")
    private Long status;
    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
