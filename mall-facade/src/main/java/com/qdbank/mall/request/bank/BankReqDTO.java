package com.qdbank.mall.request.bank;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BankReqDTO extends PageParams {


    /**
     * 城市名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "城市名称")
    private String cityName;

    /**
     * 机构编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "机构编号")
    private String branchNo;

    /**
     * 机构名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "机构名称")
    private String branchName;

    /**
     * 机构地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "机构地址")
    private String branchAddress;


}
