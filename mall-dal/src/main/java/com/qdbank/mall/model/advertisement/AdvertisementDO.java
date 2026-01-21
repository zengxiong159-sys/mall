package com.qdbank.mall.model.advertisement;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class AdvertisementDO extends BaseDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 广告模块编号
     *
     * @mbg.generated
     */
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

    @ApiModelProperty(value = "文件组ID")
    private String groupId;
    private static final long serialVersionUID = 1L;

}