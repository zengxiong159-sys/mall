package com.qdbank.mall.model.refundsetting;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class RefundsettingDO extends BaseDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 退款原因
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款原因")
    private String refundReason;

    /**
     * 是否可用 0 不可用 1 可用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "是否可用 0 不可用 1 可用")
    private Long status;



    private static final long serialVersionUID = 1L;


}