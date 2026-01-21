package bsn;

import com.qdbank.mall.config.ThirdgroupTemplateConfig;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.third.impl.VirsualBankProxySendServiceImpl;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class OilTest {

    public static void main(String[] args) {
        VirsualBankProxySendServiceImpl proxySendService = new VirsualBankProxySendServiceImpl();

        proxySendService.setUserName("yyqykcs");
        proxySendService.setPassWord("030454109f1c43604c082b8fcbf60a36");
        proxySendService.setPid("vip20200409173423621");

        proxySendService.setDomain("http://103.254.77.27:8181");

     //   ThirdgroupTemplateConfig config = new ThirdgroupTemplateConfig();



        proxySendService.setRestTemplate(get());

        OrderDO orderDO = new OrderDO();
        orderDO.setOrderSn("QD202108041926040010");
        orderDO.setRechargeMobile(123123123+"");
 //       proxySendService.chargeOrderProcess(orderDO,"ZSY100YJYKZCDX20190801094915783");

        proxySendService.orderFind("QD202108041926040010");



    }


    public static RestTemplate get() {
        RestTemplate restTemplate = new RestTemplate();

        CloseableHttpClient client = HttpClientBuilder
                .create()
                // ���ӳع�����PoolingHttpClientConnectionManager��������ز���
                .setRedirectStrategy(LaxRedirectStrategy.INSTANCE)
                .evictExpiredConnections()
                // ����ʹ��SystemProperties,{@link HttpClientBuilder}
                // �Ա����ʹ��http.proxyHost/https.proxyPort/http.proxyHost/http.proxyPort�����ԡ�
                .useSystemProperties()
                .build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
        restTemplate.setRequestFactory(factory);

        /**
         * ������Ӧ����
         */
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_HTML));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        return restTemplate;
    }


}
