package com.qdbank.mall.response.resource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResourceResDTO implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "资源编号")
    private Long id;
    /**
     * 资源URL
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "资源URL")
    private String resourceUrl;

    /**
     * 资源名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "资源名称")
    private String resourceName;

    /**
     * 描述
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "描述")
    private String resourceDescription;

    /**
     * 资源分类表主键ID
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "资源分类表主键ID")
    private Long categoryId;

    private static final long serialVersionUID = 1L;
}
