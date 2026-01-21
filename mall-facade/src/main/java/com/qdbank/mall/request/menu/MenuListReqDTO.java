package com.qdbank.mall.request.menu;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName MenuListReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/22 19:08
 * @Version 1.0
 **/
@Data
public class MenuListReqDTO extends PageParams {
    @NotNull
    @ApiModelProperty(value = "父菜单ID")
    private Long parentId;

}
