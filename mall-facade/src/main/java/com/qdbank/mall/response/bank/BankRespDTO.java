package com.qdbank.mall.response.bank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
public class BankRespDTO {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

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

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    /**
     * 修改人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;
}
