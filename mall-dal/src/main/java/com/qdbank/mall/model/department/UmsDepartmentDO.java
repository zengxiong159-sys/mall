package com.qdbank.mall.model.department;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
@Data
public class UmsDepartmentDO extends BaseDO implements Serializable {
    /**
     * 部门编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "部门编号")
    private Long id;
    @ApiModelProperty(value = "部门名称", required = true)
    private String name;
    @ApiModelProperty(value = "部门描述", required = true)
    private String description;

    private static final long serialVersionUID = 1L;
}