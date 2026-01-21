package com.qdbank.mall.request.dept;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class UpdateDeptReqDTO {
    @ApiModelProperty(value = "部门编号",required = true)
    @NotNull
    Long id;
    @Length(max = 20)
    @ApiModelProperty(value = "部门名称", required = true)
    private String name;
    @Length(max = 50)
    @ApiModelProperty(value = "部门描述", required = true)
    private String description;
}
