package com.qdbank.mall.response.feign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ServerCommonResp
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/3/24 10:21
 * @Version 1.0
 **/
@Data
public class ServerCommonResp {
    private String status;
    private String code;
    private String desc;
}
