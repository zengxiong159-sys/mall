package com.qdbank.mall.request.resource;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ResourceListReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/22 19:13
 * @Version 1.0
 **/
@Data
public class ResourceListReqDTO extends PageParams {
    @ApiModelProperty(value = "资源分类ID",required = true)
    private Long categoryId;
    @ApiModelProperty(value = "资源名称",required = true)
    private String resourceName;
    @ApiModelProperty(value = "资源URL",required = true)
    private String resourceUrl;
}
