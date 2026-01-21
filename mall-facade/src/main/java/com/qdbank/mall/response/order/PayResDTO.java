package com.qdbank.mall.response.order;

import lombok.Data;

/**
 * @ClassName PayResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/8/9 9:45
 * @Version 1.0
 **/
@Data
public class PayResDTO {
    /**错误码**/
    private String errorCode;
    /**错误消息**/
    private String errorMsg;
    /**银联账务日期**/
    private String cupdDt;
    /**积分余额**/
    private String ptBal;
    /**检索参考号**/
    private String retRefNo;
    /**流水号**/
    private String id;
    /**
     * 支付状态
     */
    private String status;
}
