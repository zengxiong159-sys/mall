package com.qdbank.mall.request.bank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BankUpdateReqDTO extends BankReqDTO{
    @ApiModelProperty(value = "主键")
    private Long id;
}
