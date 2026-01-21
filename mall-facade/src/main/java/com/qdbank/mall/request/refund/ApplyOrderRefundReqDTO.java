package com.qdbank.mall.request.refund;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 申请订单退款
 * @ClassName OrderRefundReqDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/1/16 16:26
 * @Version 1.0
 **/
@Data
public class ApplyOrderRefundReqDTO {



    /**
     * 订单编号
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "订单编号")
    private String orderSn;


    /**
     * 退款原因
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "退款原因")
    private String reason;

    /**
     * 退款类型 0 仅退款(无需退货) 1退货退款
     *
     * @mbg.generated
     */
    @Pattern(regexp = "0||1")
    @ApiModelProperty(value = "退款类型 0 仅退款(无需退货) 1退货退款")
    private String refundType;

    /**
     * 上传凭证图片url
     *
     * @mbg.generated
     */
 /*   @ApiModelProperty(value = "上传凭证图片url")
    private MultipartFile files;*/


    /**
     * 上传凭证图片url
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "上传凭证图片url")
    private String proofPics;



    /**
     * 退款说明
     *
     * @mbg.generated
     */
    @Length(max = 100)
    @ApiModelProperty(value = "退款说明")
    private String refundNote;




}
