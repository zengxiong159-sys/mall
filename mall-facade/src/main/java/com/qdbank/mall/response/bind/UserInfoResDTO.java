package com.qdbank.mall.response.bind;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserInfoResDTO {
    @ApiModelProperty(value = "用户编号")
    private String userId;
    @ApiModelProperty(value = "行员标识：0 是 1否")
    private String isBank = "1";

}
