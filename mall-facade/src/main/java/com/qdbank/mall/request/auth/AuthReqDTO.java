package com.qdbank.mall.request.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName AreaReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/21 9:49
 * @Version 1.0
 **/
@Data
public class AuthReqDTO {


    /**
     * 加密数据
     *
     * @mbg.generated
     */
    @NotBlank
    @ApiModelProperty(value = "加密数据")
    private String encryptData;


    /**
     * 加密数据
     *
     * @mbg.generated
     */
    @NotBlank
    @ApiModelProperty(value = "加密key")
    private String encryptKey;



}
