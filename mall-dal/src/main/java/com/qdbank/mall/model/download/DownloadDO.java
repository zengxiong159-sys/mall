package com.qdbank.mall.model.download;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class DownloadDO extends BaseDO implements Serializable {
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
    @ApiModelProperty(value = "文件类型：0支付订单1退款订单2指定商品免费兑换券 3行发积分兑换券 4指定商品现金优惠券 5 指定专区现金优惠券")
    private Long fileType;

    /**
     * 状态：0 处理中 1 已完成 2 已清理
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态：0处理中1已完成2已清理")
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


    private static final long serialVersionUID = 1L;
}