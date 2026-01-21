package com.qdbank.mall.request.merchant;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull;

/**
 * @ClassName UpdateMerchantStatusReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/23 10:25
 * @Version 1.0
 **/
@Data
public class UpdateMerchantStatusReqDTO {
    @NotNull
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号")
    private Long merchantNo;
    @NotNull
    @ApiModelProperty(value = "商户状态：0->禁用；1->启用",required = true)
    private Long status;
    }
