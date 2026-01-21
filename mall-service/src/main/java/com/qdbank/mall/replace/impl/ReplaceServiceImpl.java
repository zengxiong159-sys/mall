package com.qdbank.mall.replace.impl;

import com.qdbank.mall.replace.ReplaceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RefreshScope
public class ReplaceServiceImpl implements ReplaceService {
    @Value(value = "${minio.domainName:}")
    private String minioUrl;
    @Value(value = "${minio.omsdomainName:}")
    private String omsdomainName;
    @Value(value = "${minio.openflag:Y}")
    private String openflag;
    @Override
    public String replaceUrl(String replaceUrl) {
        if(!"Y".equals(openflag)) return replaceUrl;//开关关闭走公网
        if(StringUtils.isBlank(replaceUrl)) return replaceUrl;
        return replaceUrl.replace(minioUrl,omsdomainName);
    }

    @Override
    public String replaceOmsUrl(String replaceUrl) {
        if(!"Y".equals(openflag)) return replaceUrl;//开关关闭走公网
        if(StringUtils.isBlank(replaceUrl)) return replaceUrl;
        return replaceUrl.replace(omsdomainName,minioUrl);
    }


}
