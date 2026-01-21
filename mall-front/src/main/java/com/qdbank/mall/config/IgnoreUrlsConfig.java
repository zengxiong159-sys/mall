package com.qdbank.mall.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于配置白名单资源路径
 * Created by ningyuehuai on 2020/11/5.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "secure.ignored")
@RefreshScope
public class IgnoreUrlsConfig {

    private List<String> urls = new ArrayList<>();

}
