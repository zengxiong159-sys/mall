package com.qdbank.mall.request.order;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName OrderExportReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/16 15:54
 * @Version 1.0
 **/
@Data
public class OrderExportReqDTO {
    /**
     * 商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;
    @ApiModelProperty(value = "商品分类一级")
    private Long categoryId1;
    @ApiModelProperty(value = "商品分类二级")
    private Long categoryId2;
    /**
     * 提交时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern= DatePattern.NORM_DATE_PATTERN)
    private Date startTime;

    /**
     * 提交时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern= DatePattern.NORM_DATE_PATTERN)
    private Date endTime;
    /**
     * 订单类型：0->实物订单；1->话费充值订单 2 积分兑换订单  3 视频充值
     *
     * @mbg.generated
     */
    /**
     * 商品类型：0 实物1 话费充值2 油卡充值3 视频会员充值4 积分兑换券
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品类型：0实物1话费充值2油卡充值3视频会员充值4积分兑换券")
    private Long productType;

    /**
     * 订单状态：0->待支付；  1->待发货；2->已发货；3->已完成；4->已关闭；5->已取消 6 充值中 7退款申请中 8 支付失败
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单状态：0->待支付；  1->待发货；2->已发货；3->已完成；4->已关闭；5->已取消 6 充值中 7退款申请中 8 支付失败")
    private Long status;


    /**
     * 充值手机号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "充值手机号")
    private String rechargeMobile;

    /**
     * 订单号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单号")
    private String orderSn;

    /**
     * "通联客户号对应的银行预留手机号"
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'通联客户号对应的银行预留手机号'")
    private Long custMobile;

    /**
     * 商户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号",hidden = true)
    private Long merchantNo;

    @ApiModelProperty(value = "下载编号",hidden = true)
    private Long downloadId;
    @ApiModelProperty(value = "文件本地路径",hidden = true)
    private String fileLocalPath;
    @ApiModelProperty(value = "创建人",hidden = true)
    private String createdBy;
}
