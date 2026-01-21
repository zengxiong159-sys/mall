package com.qdbank.mall.response.advertisement;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName AdvertisementResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/11 10:08
 * @Version 1.0
 **/
@Data
public class AdvertisementResDTO {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 广告模块编号
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "广告模块编号")
    private Long moduleId;

    /**
     * 广告名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "广告名称")
    private String advertiseName;

    /**
     * 广告描述
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "广告描述")
    private String advertiseDescription;

    /**
     * 优先级
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优先级")
    private Long advertismentLevel;

    /**
     * 广告开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "广告开始时间")
    private Date startTime;

    /**
     * 广告结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "广告结束时间")
    private Date endTime;

    /**
     * 广告跳转链接
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "广告跳转链接")
    private String jumpUrl;

    /**
     * 图片url
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "图片url")
    private String picUrl;

    /**
     * 广告状态 0 停用 1 启用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "广告状态 0 停用 1 启用")
    private Long status;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    /**
     * 修改人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
}
