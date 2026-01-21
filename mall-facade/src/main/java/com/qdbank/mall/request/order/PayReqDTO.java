package com.qdbank.mall.request.order;

import lombok.Data;

/**
 * @ClassName PayReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/8/8 16:46
 * @Version 1.0
 **/
@Data
public class PayReqDTO {
    /**银行卡号**/
    private String cardNo;
    /**读卡方式**/
    private String inptTpye;
    /**交易金额**/
    private String transAmt;
    /**客户号**/
    private String custId;
    /**积分账户类型**/
    private String acctType;
    /**积分调整值**/
    private String transe;
    /**trxndirect**/
    private String trxndirect;
    /**currNum**/
    private String currNum;
    /**flag**/
    private String flag;
    /**商品名称**/
    private String tradeName;
    /**商户号**/
    private String merchantNumber;
    /**商品编号**/
    private String commodityCode;
    /**商户名称**/
    private String nameBusiness;
    /**随机数**/
    private String pwdRandomNum;
    /**密码**/
    private String pwd;
    /**服务请求方id**/
    private String csmrId;
    /**渠道来源号**/
    private String transSrc;
    /**操作员号**/
    private String userId;
    /**订单编号**/
    private String ordId;
    /**子交易码**/
    private String chlSubTransCde = "99";
    /**交易机构**/
    private String brNo = "80201";
    /**优惠配套代码**/
    private String pkgCde = "971";
    /**促销活动代码**/
    private String promoCde = "981";
    /**产品代码**/
    private String prodId = "P010080001" ;
    /**检查项目**/
    private String opt = "0";

    private String thirdFlg = "青银e家商城消费";
}
