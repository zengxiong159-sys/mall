package com.qdbank.mall.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 订单支付TemplateCon配置
 * @author hongjh
 */
@Configuration
public class ThirdgroupTemplateConfig {

    /**
     * 连接超时 - 连接超时时间，毫秒
     * Set the underlying URLConnection's connect timeout (in milliseconds).
     * A timeout value of 0 specifies an infinite timeout.
     */
    private static final int CONNECT_TIMEOUT = 2000;

    /**
     * 数据读取超时时间，即SocketTimeout - 读写超时时间，毫秒
     * Set the underlying URLConnection's read timeout (in milliseconds).
     * A timeout value of 0 specifies an infinite timeout.
     */
    private static final int SOCKET_TIMEOUT = 15000;

    /**
     * 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
     */
    private static final int CONNECTION_REQUEST_TIMEOUT = 200;

    /**
     * maximum total connection value.
     * 连接池大小
     */
    private static final int MAX_CONNECT_TOTAL = 500;

    /**
     * maximum connection per route value.
     * 每个路由的最大连接数
     */
    private static final int MAX_CONNECT_PER_ROUTE = 100;
    /**
     * 持久连接最大空间时间
     * maximum time persistent connections can stay idle while kept alive
     * in the connection pool. Connections whose inactivity period exceeds this value will
     * get closed and evicted from the pool.
     */
    private static final int MAX_IDLE_TIME = 180;

    @Bean(name = "ThirdgroupTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        // 增加读取超时时间、连接超时时间、连接获取超时时间
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom()
                .setSocketTimeout(SOCKET_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT);

        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(requestConfigBuilder.build())
                // 连接池管理器PoolingHttpClientConnectionManager，设置相关参数
                .setMaxConnTotal(MAX_CONNECT_TOTAL)
                .setMaxConnPerRoute(MAX_CONNECT_PER_ROUTE)
                .setRedirectStrategy(LaxRedirectStrategy.INSTANCE)
                .evictExpiredConnections()
                .evictIdleConnections(MAX_IDLE_TIME, TimeUnit.SECONDS)
                // 增加使用SystemProperties,{@link HttpClientBuilder}
                // 以便可以使用http.proxyHost/https.proxyPort/http.proxyHost/http.proxyPort等属性。
                .useSystemProperties()
                .build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }


    @Bean(name = "mobileThirdgroupTemplate")
    public RestTemplate mobileThirdgroupTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        // 增加读取超时时间、连接超时时间、连接获取超时时间
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom()
                .setSocketTimeout(SOCKET_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT);

        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(requestConfigBuilder.build())
                // 连接池管理器PoolingHttpClientConnectionManager，设置相关参数
                .setMaxConnTotal(MAX_CONNECT_TOTAL)
                .setMaxConnPerRoute(MAX_CONNECT_PER_ROUTE)
                .setRedirectStrategy(LaxRedirectStrategy.INSTANCE)
                .evictExpiredConnections()
                .evictIdleConnections(MAX_IDLE_TIME, TimeUnit.SECONDS)
                // 增加使用SystemProperties,{@link HttpClientBuilder}
                // 以便可以使用http.proxyHost/https.proxyPort/http.proxyHost/http.proxyPort等属性。
                .useSystemProperties()
                .build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
        restTemplate.setRequestFactory(factory);

        /**
         * 网信响应处理
         */
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_HTML));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        return restTemplate;
    }

}
