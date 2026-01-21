package com.qdbank.mall.request.merchant;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName MerchantLikeQueryReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/13 13:43
 * @Version 1.0
 **/
@Data
public class MerchantLikeQueryReqDTO extends PageParams implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号")
    private Long merchantNo;

    /**
     * 商户名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 商户状态 0 未启用 1已启用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户状态 0 未启用 1已启用")
    private Long status;

    /**
     * 合同有效期开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "合同有效期开始时间")
    private Date startTime;

    /**
     * 合同有效期结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "合同有效期结束时间")
    private Date endTime;


    /**
     * 创建开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建开始时间")
    private Date createStartTime;
    /**
     * 创建结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建结束时间")
    private Date createEndTime;

}
