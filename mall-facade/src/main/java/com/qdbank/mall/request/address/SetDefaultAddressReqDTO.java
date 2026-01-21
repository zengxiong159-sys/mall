package com.qdbank.mall.request.address;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName SetDefaultAddress
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/19 14:26
 * @Version 1.0
 **/
@Data
public class SetDefaultAddressReqDTO {
    @ApiModelProperty(value = "旧默认地址id")
    private Long oldId;
    @NotNull
    @ApiModelProperty(value = "新默认地址id")
    private Long newId;
}
