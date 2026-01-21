package com.qdbank.mall.response.third;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Response
 *
 * @author hongjh
 * @date 2021/3/23 13:50
 * @since 1.0.0
 */
public class ThirdResBody {

    @ApiModelProperty("错误号")
    private String errorCode;

    @ApiModelProperty("错误描述")
    private String errorMsg;


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
