package com.qdbank.mall.request.resource;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ResourceReqDTO implements Serializable {
    @ApiModelProperty(value = "资源ID",required = true)
    Long id;
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
    @ApiModelProperty(value = "资源分类表主键ID")
    private Long categoryId;

    private static final long serialVersionUID = 1L;
}
