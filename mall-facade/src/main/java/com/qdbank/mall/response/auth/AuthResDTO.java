package com.qdbank.mall.response.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName AreaResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/21 9:49
 * @Version 1.0
 **/
@Data
public class AuthResDTO {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private String name;

    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private String phone;


    /**
     * 主键
     *
     * @mbg.generated
     */
   /* @ApiModelProperty(value = "联合id")
    private String unionId;*/



    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "是否绑卡：1绑卡 0 未绑卡")
    private String bindFlag;


    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "是否绑卡：1授权 0 未授权")
    private String authFlag;


    /**
     *
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "授权token")
    private String token;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    private String custId;

    /**
     * 微信头像url
     */
    @ApiModelProperty(value = "微信头像url")
    private String avatarUrl;

}
