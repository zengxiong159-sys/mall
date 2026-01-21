package com.qdbank.mall.request.dept;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName DeptLikeQueryReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/23 10:34
 * @Version 1.0
 **/
@Data
public class DeptLikeQueryReqDTO extends PageParams {
    @ApiModelProperty(value = "部门编号")
    private Long id;
    @ApiModelProperty(value = "部门名称")
    private String name;
}
