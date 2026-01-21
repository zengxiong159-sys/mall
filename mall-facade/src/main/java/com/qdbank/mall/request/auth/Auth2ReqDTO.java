package com.qdbank.mall.request.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName AreaReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/21 9:49
 * @Version 1.0
 **/
@Data
public class Auth2ReqDTO {

    /**
     * sessionKey
     *
     * @mbg.generated
     */
    @NotBlank
    @ApiModelProperty(value = "sessionKey")
    private String sessionKey;

}
