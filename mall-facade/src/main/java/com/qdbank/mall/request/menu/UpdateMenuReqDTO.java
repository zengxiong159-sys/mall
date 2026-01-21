package com.qdbank.mall.request.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName UpdateMenuReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/7 14:10
 * @Version 1.0
 **/
@Data
public class UpdateMenuReqDTO implements Serializable {
    @NotNull
    @ApiModelProperty(value = "菜单编号")
    Long id;
    /**
     * 父级菜单id
     *
     * @mbg.generated
     */
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

}
