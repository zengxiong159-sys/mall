package com.qdbank.mall.request.gatawayrequest;

import lombok.Data;


/**
 * @ClassName GatawayReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/3/23 16:03
 * @Version 1.0
 **/
@Data
public class GatawayReqDTO {
    private GwHeader gwSysHeader;
    private Object  reqBody;
}
