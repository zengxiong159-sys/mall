package com.qdbank.mall.request.address;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * @ClassName AddressReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/19 11:44
 * @Version 1.0
 **/
@Data
public class UpdateAddressReqDTO {
    @ApiModelProperty(value = "地址编号")
    @NotNull
    private Long id;
    /**
     * 收件人姓名
     *
     * @mbg.generated
     */
    @NotNull
    @Length(max = 15)
    @ApiModelProperty(value = "收件人姓名")
    private String userName;

    /**
     * 收件人手机号
     *
     * @mbg.generated
     */
    @NotNull
    @Pattern(regexp = "^1[3|4|5|6|7|8|9][0-9]\\d{4,8}$",message = "请填写正确的手机号码")
    @ApiModelProperty(value = "收件人手机号")
    private String mobile;

    /**
     * 省份/直辖市
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "省份/直辖市")
    private String province;

    /**
     * 城市/直辖区
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "城市/直辖区")
    private String city;

    /**
     * 县/区
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "县/区")
    private String regionArea;


    /**
     * 详细地址
     *
     * @mbg.generated
     */
    @NotNull
    @Length(max = 50)
    @ApiModelProperty(value = "详细地址")
    private String detailAddress;

    /**
     * 默认地址标识 0 默认地址 1 非默认地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "默认地址标识 0 默认地址 1 非默认地址")
    private Long defaultFlag;
}
