package com.qdbank.mall.response.area;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName AreaResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/21 9:49
 * @Version 1.0
 **/
@Data
public class AreaResDTO {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 区域名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "区域名称")
    private String addressName;



    @ApiModelProperty(value = "地区类型：0省，1市，2区")
    private String areaType;


    /**
     * 父级ID
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "父级ID")
    private Long parentId;

    /**
     * 排序
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "排序")
    private Long viewOrder;


    /**
     * 排序
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "子类")
    private List<AreaResDTO> children;
}
