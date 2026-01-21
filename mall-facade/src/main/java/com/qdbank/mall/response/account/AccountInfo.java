package com.qdbank.mall.response.account;

import com.qdbank.mall.response.card.CardInfo;
import lombok.Data;

import java.util.List;

@Data
public class AccountInfo {

    /**账户类型*/
    private String accountType;

    /**锁定码*/
    private String blockCode;

    /**账户层异常信息*/
    private String blockCodeMsg;

    /**可用额度*/
    private String acctOtb;

    /**核心客户号*/
    private String custId;

    /**卡片列表*/
    private List<CardInfo> cardInfoList;

}
