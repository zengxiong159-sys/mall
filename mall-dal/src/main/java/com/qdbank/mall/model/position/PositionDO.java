package com.qdbank.mall.model.position;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class PositionDO extends BaseDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 活动名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "金刚位名称")
    private String positionName;

    /**
     * 优先级
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优先级")
    private Long positionLevel;

    /**
     * 活动调换url
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "跳转 url")
    private String jumpUrl;

    /**
     * 图片url
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "图片url")
    private String picUrl;

    /**
     * 活动状态 0 停用 1 启用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "金刚位状态0停用1启用")
    private Long status;

    /**
     * 文件组名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "文件组名")
    private String groupId;



    private static final long serialVersionUID = 1L;


}