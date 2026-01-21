package com.qdbank.mall.spg;

public interface SpgService {
    /**
     * 请求小程序接口
     * @param sessionKey
     * @param actionid
     * @return
     */
    public String decryptData(String sessionKey,String actionid,String custId);
}
