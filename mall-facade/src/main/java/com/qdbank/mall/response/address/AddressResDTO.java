package com.qdbank.mall.response.address;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName AddressResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/19 11:43
 * @Version 1.0
 **/
@Data
public class AddressResDTO {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;



    /**
     * 收件人姓名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "收件人姓名")
    private String userName;

    /**
     * 收件人手机号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "收件人手机号")
    private String mobile;

    /**
     * 省份/直辖市
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "省份/直辖市")
    private String province;

    /**
     * 城市/直辖区
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "城市/直辖区")
    private String city;

    /**
     * 县/区
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "县/区")
    private String regionArea;


    /**
     * 详细地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "详细地址")
    private String detailAddress;

    /**
     * 默认地址标识 0 默认地址 1 非默认地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "默认地址标识 0 非默认地址 1 默认地址")
    private Long defaultFlag;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
}
