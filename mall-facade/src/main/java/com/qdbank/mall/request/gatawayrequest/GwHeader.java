package com.qdbank.mall.request.gatawayrequest;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName GwHeader
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/3/23 16:20
 * @Version 1.0
 **/
@Data
public class GwHeader {
    private String gwVer = "1";
    private String svcId;
    private String tellerId = "*SPGUSER";
    private String requestTime = DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN);
}
