package com.qdbank.mall.request.userwhite;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserWhiteQueryReqDTO extends PageParams {
    @ApiModelProperty(value = "用户编号")
    private Long id;
    /**
     * 姓名： 0 男 1 女
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "姓名")
    private String userName;


    /**
     * 状态 0 启用 1 停用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态0 停用 1启用")
    private String status;

    /**
     * 提交时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 提交时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
}
