package com.qdbank.mall.response.feign;

import lombok.Data;

/**
 * @ClassName GwSysHeader
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/3/24 10:22
 * @Version 1.0
 **/
@Data
public class GwSysHeader {
    private String svcId;
    private String gwVer;
    private String resServiceSn;
    private String resServiceTime;
    private String requestTime;
    private String tellerId;
    private String chlSerNo;
    private ServerCommonResp servResponse;
}
