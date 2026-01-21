package com.qdbank.mall.response.paramsconfig;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName ParamsConfigResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/1 11:56
 * @Version 1.0
 **/
@Data
public class ParamsConfigResDTO {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 参数名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "参数名称")
    private String paramName;

    /**
     * 参数值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "参数值")
    private String paramValue;

    /**
     * 参数类型：0 积分值比例配置 1 自动确认收货失效配置 2 退款申请有效期配置 3 物流公司配置
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "参数类型：0 积分值比例配置 1自动确认收货失效配置 2退款申请有效期配置 3物流公司配置")
    private Long paramType;

    /**
     * 参数描述
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "参数描述")
    private String paramDescription;

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

    /**
     * 更新人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新人")
    private String updatedBy;
}
