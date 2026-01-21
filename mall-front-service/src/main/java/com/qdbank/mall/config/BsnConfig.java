package com.qdbank.mall.config;

import com.qdbank.mall.bo.VirtualConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 用于配置白名单资源路径
 * Created by ningyuehuai on 2020/11/5.
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "bsn")
public class BsnConfig {
    private List<VirtualConfig> products;
}
