package com.qdbank.mall.request.position;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateStatusPositionReqDTO {
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    Long id;
    @ApiModelProperty(value = "金刚位状态0停用1启用")
    Long status;
}
