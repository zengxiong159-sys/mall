package com.qdbank.mall.model.menu;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class UmsMenuDO extends BaseDO implements Serializable {
    /**
     * 主键ID
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 父级菜单id
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "父级菜单id")
    private Long parentId;

    /**
     * 菜单级数
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "菜单级数")
    private Long menuLevel;

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


    private static final long serialVersionUID = 1L;

}