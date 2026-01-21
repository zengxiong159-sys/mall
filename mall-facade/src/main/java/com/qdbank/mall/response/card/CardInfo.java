package com.qdbank.mall.response.card;

import lombok.Data;

@Data
public class CardInfo {

    /**卡号*/
    private String cardNo;

    /**卡片层异常信息*/
    private String cardMsg;

    /**产品号*/
    private String prodCd;

    /**卡产品名称*/
    private String prodName;

}
