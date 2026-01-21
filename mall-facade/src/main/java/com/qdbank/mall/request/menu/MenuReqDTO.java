package com.qdbank.mall.request.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class MenuReqDTO implements Serializable {
    @ApiModelProperty(value = "父级ID")
    private Long parentId;
    /**
     * 菜单排序
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "菜单排序")
    private Long menuSort;

    /**
     * 菜单名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    /**
     * 图标标识
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "图标标识")
    private String menuIcon;

    /**
     * 前端路由名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "前端路由名称")
    private String routeName;

}
