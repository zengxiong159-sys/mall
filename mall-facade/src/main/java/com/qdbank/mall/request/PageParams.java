package com.qdbank.mall.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName PageParams
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/11 10:17
 * @Version 1.0
 **/
@Data
public class PageParams {
    @NotNull
    @ApiModelProperty(value = "每页显示条数，默认显示10条",required = true)
    private Integer pageSize = 10;
    @NotNull
    @ApiModelProperty(value = "页码",required = true)
    private Integer pageNum;
    @ApiModelProperty(value = "是否分页",hidden = true)
    private String isPage;
}
