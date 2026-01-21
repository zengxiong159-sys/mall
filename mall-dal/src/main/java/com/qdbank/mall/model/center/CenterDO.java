package com.qdbank.mall.model.center;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class CenterDO extends BaseDO implements Serializable {
    private Long id;

    /**
     * 配置类型： 1 新闻中心 2 小程序隐私协议 3 商城隐私协议
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "配置类型：1新闻中心2小程序隐私协议3商城隐私协议")
    private Long configyType;

    /**
     * 标题
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 状态 0 停用 1 启用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态0停用1启用")
    private String status;

    /**
     * 生效开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "生效开始时间")
    private Date startTime;

    /**
     * 结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 内容详情
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "内容详情")
    private String contentDetail;

    private static final long serialVersionUID = 1L;


}