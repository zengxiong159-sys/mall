package com.qdbank.mall.response.download;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName DownloadRespDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/1/13 14:15
 * @Version 1.0
 **/
@Data
public class DownloadRespDTO {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 商户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户号")
    private Long merchantNo;

    /**
     * 文件名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "文件名称")
    private String fileName;

    /**
     * 文件下载url
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "文件下载url")
    private String fileUrl;

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
    @ApiModelProperty(value = "状态：0处理中1已完成2已清理 3 文件生成失败")
    private Long status;

    /**
     * 文件分组
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "文件分组")
    private String fileGroup;

    /**
     * 文件服务器路径
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "文件服务器路径")
    private String filePath;


    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
}
