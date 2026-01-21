package com.qdbank.mall.request.download;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName DownloadReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/1/13 14:14
 * @Version 1.0
 **/
@Data
public class DownloadReqDTO extends PageParams {
    /**
     * 文件名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "文件名称")
    private String fileName;

    /**
     * 文件类型：0 支付订单 1 退款订单 2 指定商品免费兑换券 3 行发积分兑换券 4 指定专区现金优惠券
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "文件类型：0支付订单1退款订单2指定商品免费兑换券3行发积分兑换券4指定专区现金优惠券")
    private Long fileType;

    /**
     * 状态：0 处理中 1 已完成 2 已清理
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态：0处理中1已完成2已清理")
    private Long status;
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
    /**
     * 商户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户号",hidden = true)
    private Long merchantNo;
    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人",hidden = true)
    private String createdBy;
}
