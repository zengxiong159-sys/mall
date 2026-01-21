package com.qdbank.mall.model.user;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class UmsResourceDO extends BaseDO implements Serializable {
    /**
     * ID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "ID")
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
    @ApiModelProperty(value = "资源分类表主键ID")
    private Long categoryId;



    private static final long serialVersionUID = 1L;


}