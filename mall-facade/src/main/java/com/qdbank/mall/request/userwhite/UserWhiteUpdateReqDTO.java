package com.qdbank.mall.request.userwhite;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
public class UserWhiteUpdateReqDTO {
  @ApiModelProperty("用户编号")
  private  Long id;
  /**
   * 姓名
   *
   * @mbg.generated
   */
  @ApiModelProperty(value = "姓名")
  @Length(min = 1,max = 5,message = "用户姓名超过最大长度")
  private String userName;

  /**
   * 性别
   *
   * @mbg.generated
   */
  @ApiModelProperty(value = "性别：M男F女")
  private String gender;

  /**
   * 状态 0 启用 1 停用
   *
   * @mbg.generated
   */
  @ApiModelProperty(value = "状态0 停用 1启用")
  private String status;

  /**
   * 手机号
   *
   * @mbg.generated
   */
  @Pattern(regexp = "^1[3-9]\\d{9}$",message = "手机号码不合法")
  @ApiModelProperty(value = "手机号")
  private String mobile;

  /**
   * 身份证号
   *
   * @mbg.generated
   */
  @ApiModelProperty(value = "身份证号")
  @Pattern(regexp ="^([1-9]\\\\d{5}(18|19|20)\\\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\\\d|3[01])\\\\d{3}(\\\\d|X|x)$)|\" +\n" +
          "                        \"^([1-9]\\\\d{5}\\\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\\\d|3[01])\\\\d{2}(\\\\d|X|x)?$)",message = "身份证号码不合法")
  private String idNo;

  @ApiModelProperty(value = "备注信息")
  private String remark;
}
