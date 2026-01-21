package com.qdbank.mall.request.userwhite;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserWhiteCreateReqDTO {
    /**
     * 姓名
     *
     * @mbg.generated
     */
    @NotBlank(message = "姓名不能为空")
    @Length(min = 1,max = 10,message = "姓名超过最大长度")
    @ApiModelProperty(value = "姓名")
    private String userName;

    /**
     * 性别
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "性别：M 男 F 女")
    @NotBlank(message = "性别不能为空")
    @Pattern(regexp = "^[MF]$",message = "性别不合法")
    private String gender;

    /**
     * 手机号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$",message = "手机号码不合法")
    private String mobile;

    /**
     * 身份证号
     *
     * @mbg.generated
     */
    @Pattern(regexp ="^([1-9]\\d{5}(18|19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}(\\d|X|x)$)|" +
            "^([1-9]\\d{5}\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{2}(\\d|X|x)?$)",message = "身份证号码不合法")
    @NotBlank(message = "身份证号不能为空")
    @ApiModelProperty(value = "身份证号")
    private String idNo;

    @ApiModelProperty(value = "备注信息")
    private String remark;
}
