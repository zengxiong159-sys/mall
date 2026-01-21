package com.qdbank.mall.request.gwheader;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName GwHeader
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/3/23 14:53
 * @Version 1.0
 **/
@Data
public class GwHeader {
    private String gwVer;
    private String svcId;
    private String tellerId;
    private String requestTime = DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN);
}
