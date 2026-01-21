package com.qdbank.mall.request.prefecture;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import sun.nio.cs.ext.MacArabic;

import javax.validation.constraints.NotBlank;
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
public class PrefectureReqDTO implements Serializable {

    /**
     * 专区名称
     *
     * @mbg.generated
     */
    @NotBlank
    @Length(max = 12,min = 1)
    @ApiModelProperty(value = "专区名称",required = true)
    private String prefectureName;

    /**
     * 专区描述
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "专区描述")
    @Length(max = 100)
    private String prefectureDecription;

    /**
     * 优先级 数字越小级别越高
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优先级 数字越小级别越高",required = true)
    private Long prefectureLevel;

    /**
     * 专区启用时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "专区启用时间",required = true)
    private Date startTime;

    /**
     * 专区结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "专区结束时间",required = true)
    private Date endTime;

    /**
     * 商品关联id
     */
    @NotBlank
    @ApiModelProperty(value = "商品关联id",required = true)
    String ids;//前端用逗号分隔。
    @ApiModelProperty(value = "专区类型：默认0:常规专区 1:达标专区")
    private Integer prefectureType;
}
