package com.qdbank.mall.model.prefecture;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class PrefectureDO extends BaseDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 专区名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "专区名称")
    private String prefectureName;

    /**
     * 专区描述
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "专区描述")
    private String prefectureDecription;

    /**
     * 优先级 数字越小级别越高
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优先级 数字越小级别越高")
    private Long prefectureLevel;

    /**
     * 专区启用开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "专区启用开始时间")
    private Date startTime;

    /**
     * 专区启用时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "专区启用结束时间")
    private Date endTime;

    @ApiModelProperty(value = "专区状态 0 停用 1 启用")
    private Long status;
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "专区类型：默认0:常规专区 1:达标专区")
    private Integer prefectureType;
}