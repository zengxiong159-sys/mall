package com.qdbank.mall.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName CommonReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/23 18:49
 * @Version 1.0
 **/
@Data
public class CommonStringIDReqDTO {
    @NotNull
    @ApiModelProperty(value = "可以传：主键/父级ID/客户号/顶通模块编号/手机号/批次号/资源编号/退款流水号/角色编号")
    private String id;
}
