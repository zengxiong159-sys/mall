package com.qdbank.mall.enums;

public enum WechatBindStatus {

    BIND("1","已绑定"),
    NONE_BIND("0","未绑定"),
    ;

    String status;
    String desc;

    WechatBindStatus(String status,String desc){
        this.status=status;
        this.desc=desc;
    }

    public String getStatus() {
        return status;
    }
}
