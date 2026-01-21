package com.qdbank.mall.response.role;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class RoleResDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "角色编号", required = true)
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

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 修改时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    /**
     * 修改人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;
}
