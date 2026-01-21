package com.qdbank.mall.response.order;

import lombok.Data;

/**
 * @ClassName OrderQueryResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/8/9 14:43
 * @Version 1.0
 **/
@Data
public class OrderQueryResDTO {
    private String errorCode;
    private String errorMsg;
    /**订单号**/
    private String orderid;
    /**订单金额**/
    private String transAmt;
    /**积分调整值**/
    private String transe;
    /**调整方向**/
    private String trxndirect;
    /**商户编号**/
    private String merchantNumber;
    /**商品名称**/
    private String tradeName;
    /**请求标志**/
    private String flag;
    /**查询类型**/
    private String queryType;
    private String id;
    /**商户名称**/
    private String nameBusiness;
    /**商品编号**/
    private String commodityCode;
    /**回跳页面**/
    private String url;
    /**订单状态**/
    private String status;
}
