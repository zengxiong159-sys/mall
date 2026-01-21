package com.qdbank.mall.request.bank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BankLikeReqDTO {
    /**
     * 机构地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "机构地址")
    @NotBlank(message = "机构地址名称不能为空")
    private String branchAddress;

    /**
     * 城市名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "城市名称")
    @NotBlank(message = "城市名称不能为空")
    private String cityName;
}
