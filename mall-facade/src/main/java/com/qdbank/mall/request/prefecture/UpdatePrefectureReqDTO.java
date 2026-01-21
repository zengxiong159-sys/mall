package com.qdbank.mall.request.prefecture;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
public class UpdatePrefectureReqDTO implements Serializable {
    @NotNull
    @ApiModelProperty(value = "专区编号",required = true)
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
     * 专区启用时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "专区启用时间")
    private Date startTime;
    /**
     * 专区结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "专区结束时间")
    private Date endTime;
    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;

    /**
     * 商品关联id
     */
    @NotBlank(message = "商品关联关系,不能为空!")
    @ApiModelProperty(value = "商品关联id",required = true)
    String ids;//前端用逗号分隔
}
