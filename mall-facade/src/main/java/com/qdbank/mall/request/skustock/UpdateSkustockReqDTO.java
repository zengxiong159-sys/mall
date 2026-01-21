package com.qdbank.mall.request.skustock;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName UpdateSkustockReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/5 10:20
 * @Version 1.0
 **/
@Data
public class UpdateSkustockReqDTO {

    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品编号")
    private Long productId;

    /**
     * 商品售价中现金金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价中现金金额")
    private BigDecimal productCash;

    /**
     * 商品售价中积分值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价中积分值")
    private Long productIntegration;



    /**
     * 限购开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "限购开始时间")
    private Date promotionStartTime;

    /**
     * 限购结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "限购结束时间")
    private Date promotionEndTime;

    /**
     * 每人限购数量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "每人限购数量")
    private Long promotionPerLimit;

    /**
     * 积分结算标识：0 不结算 1 需要结算
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分结算标识：0不结算1需要结算")
    private Long integrationPayFlag;
}
