package com.qdbank.mall.request.dept;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class DeptReqDTO {
    @Length(max = 20)
    @NotNull
    @ApiModelProperty(value = "部门名称", required = true)
    private String name;
    @Length(max = 50)
    @NotNull
    @ApiModelProperty(value = "部门描述", required = true)
    private String description;
}
