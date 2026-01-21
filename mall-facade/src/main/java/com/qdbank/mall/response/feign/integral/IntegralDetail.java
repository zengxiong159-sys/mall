package com.qdbank.mall.response.feign.integral;

import lombok.Data;

/**
 * @ClassName IntegralDetail
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/3/24 18:25
 * @Version 1.0
 **/
@Data
public class IntegralDetail {
    /**积分余额**/
    private String pointBal;
    /**当期调整积分**/
    private String ctdAdjPoints;
    /**当期兑换积分**/
    private String ctdDisbPoints;
    /**当期新增积分**/
    private String ctdEarnedPoints;
    /**期初积分余额**/
    private String pointBeginBal;
    /**信用卡账号**/
    private String acctNo;
    /**积分账户类型**/
    private String acctType;
    /**积分账户描述  M 美团积分账户类型  B标卡积分账户类型**/
    private String acctDesc;
}
