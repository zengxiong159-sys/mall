package com.qdbank.mall.model.bank;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class BankNodeAddress extends BaseDO implements Serializable {
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

    private static final long serialVersionUID = 1L;

}