package com.qdbank.mall.model.role;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class RoleDO extends BaseDO implements Serializable {
    /**
     * ID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 角色名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**
     * 描述
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "描述")
    private String roleDescription;



    /**
     * 启用状态：0->禁用；1->启用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
    private Long status;

    /**
     * 角色排序
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "角色排序")
    private Long roleSort;


    private static final long serialVersionUID = 1L;

}