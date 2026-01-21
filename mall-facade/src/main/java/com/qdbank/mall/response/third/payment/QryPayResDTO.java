package com.qdbank.mall.response.third.payment;

import com.qdbank.mall.response.third.ThirdResBody;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @ClassName QryPayResDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/2/23 16:30
 * @Version 1.0
 **/
@Data
public class QryPayResDTO extends ThirdResBody {


    @ApiModelProperty("")
    private String ordId;

    @ApiModelProperty("")
    private String id;

    @ApiModelProperty("")
    private String integral;

    @ApiModelProperty("")
    private String cash;

    @ApiModelProperty("")
    private String modePayment;

    @ApiModelProperty("")
    private String merchantNumber;

    @ApiModelProperty("")
    private String commodityCode;

    @ApiModelProperty("")
    private String origChlSerNo;

    @ApiModelProperty("")
    private String origChlDt;

    @ApiModelProperty("")
    private String respStatus;

    @ApiModelProperty("")
    private String acctType;

    @ApiModelProperty("")
    private String reqId;

    private String status;










}
