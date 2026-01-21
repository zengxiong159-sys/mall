package com.qdbank.mall.request.prefecture;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName PrefectureReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/11 11:15
 * @Version 1.0
 **/
@Data
public class PrefectureLikeQueryReqDTO extends PageParams implements Serializable {

    /**
     * 专区id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "专区id")
    private Long prefectureId;

    /**
     * 专区名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "专区名称")
    private String prefectureName;

    /**
     * 专区启用时间
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "专区启用时间",required = true)
    private Date startTime;

    /**
     * 专区结束时间
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "专区结束时间",required = true)
    private Date endTime;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态 0 停用 1 启用")
    private Long status;

    @ApiModelProperty(value = "专区类型：默认0:常规专区 1:达标专区")
    private Integer prefectureType;
}
