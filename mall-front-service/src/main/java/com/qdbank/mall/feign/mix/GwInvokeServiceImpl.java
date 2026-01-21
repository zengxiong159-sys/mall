package com.qdbank.mall.feign.mix;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;

import com.qd.bank.pojo.allinpay.req.GwRequest;
import com.qd.bank.pojo.allinpay.resp.GwResponse;
import com.qd.bank.service.gw.dto.GwRequestDTO;
import com.qd.bank.service.gw.dto.GwResponseDTO;
import com.qd.bank.service.gw.dto.TonglianServiceDTO;
import com.qd.bank.service.gw.service.GwFacadeConverterFactory;
import com.qd.bank.service.gw.service.GwXmlService;
import com.qdbank.mall.service.impl.SnowFlakeService;
import com.qdbank.monitor.annotation.MyTimed;
import com.qdbank.monitor.enums.MetricEnums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Map;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.qd.bank.service.gw.constant.GwConstant.GW_HEADER_PREFFIX;


/**
 * @author Qdccb
 */
@Service
public class GwInvokeServiceImpl implements GwInvokeService {
private static final Logger log = LoggerFactory.getLogger(GwInvokeServiceImpl.class);

    private static final String INVOKE_TONGLIAN = "tonglian";

    private static final String INVOKE_TRANSPARENT = "transparent";


    @Autowired
    private ObjectMapper objectMapper;
    @Resource
    private SnowFlakeService snowFlakeService;
    @Resource
    private ConnectFeignService connectFeignService;
    @Autowired
    private GwFacadeConverterFactory gwFacadeConverterFactory;
    @Resource
    private GwXmlService gwXmlService;

    @Override
    public String execute(String serviceId, String req) {

        return this.invoke(INVOKE_TONGLIAN,req, serviceId,null);
    }

    @Override
    public <T> GwResponse<T> execute(GwRequest<?> gwRequest, Class<T> gwResponseBodyClass) {
        return this.execute(gwRequest, gwResponseBodyClass,null);
    }

    @Override
    @MyTimed(value = MetricEnums.GW_DURATION, type = "http", biz = "gw")
    public <T> GwResponse<T> execute(GwRequest<?> gwRequest, Class<T> gwResponseBodyClass, HttpHeaders headers) {
        if (headers == null) {
            headers = new HttpHeaders();
        }

        objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
        if (gwRequest == null || !gwRequest.valid()) {
            throw new IllegalArgumentException("invalid gwRequest");
        }
        if (gwResponseBodyClass == null) {
            throw new IllegalArgumentException("gwResponseBodyClass can not be null");
        }

        String req;
        try {
            req = objectMapper.writeValueAsString(gwRequest);
        } catch (JsonProcessingException e) {
            log.error("objectMapper writeValueAsString error", e);
            throw new IllegalArgumentException("gwRequest writeValueAsString error");
        }

        final String serviceId = gwRequest.getGwSysHeader().getSvcId();
        String responseBody = invoke(INVOKE_TONGLIAN,req, serviceId,headers);
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(GwResponse.class, gwResponseBodyClass);
        try {
            return objectMapper.readValue(responseBody, javaType);
        } catch (JsonProcessingException e) {
            log.error("objectMapper readValue error", e);
            return new GwResponse<>();
        }
    }


    private static final String CONNECT_SERVICE_INVOKE_TEMPLATE = "http://{0}/{1}";

    private String invoke(String path ,String req, String serviceId, HttpHeaders headers) {
        String result = "{}";
        final String url = MessageFormat.format(CONNECT_SERVICE_INVOKE_TEMPLATE, INVOKE_TONGLIAN,serviceId);
        final GwRequestDTO<Map<String, Object>> gwRequestDTO = gwFacadeConverterFactory.fromJson(url,req);
        modifyServiceSn(gwRequestDTO,snowFlakeService.nextId());
        final TonglianServiceDTO tonglianServiceDTO = new TonglianServiceDTO(gwRequestDTO);
        String xmlBody = gwXmlService.toXml(tonglianServiceDTO);
        headers.setContentType(MediaType.APPLICATION_XML);
        for (Map.Entry<String, Object> entry : gwRequestDTO.getGwSysHeader().entrySet()) {
            String headerName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, entry.getKey());
            headerName = GW_HEADER_PREFFIX.concat(headerName);
            headers.add(headerName,(String) entry.getValue());
        }
        HttpEntity<byte[]> request = new HttpEntity<>(xmlBody.getBytes(StandardCharsets.UTF_8),headers);
        try {
            String s = connectFeignService.invokeTlXlmService(serviceId, xmlBody);
            String originResponseBody = new String(s.getBytes(), StandardCharsets.UTF_8);
            final TonglianServiceDTO tonglianServiceResDTO = gwXmlService.fromXml(originResponseBody);
            GwResponseDTO response = new GwResponseDTO(tonglianServiceResDTO);
            final String responseBody = gwFacadeConverterFactory.toJson(url,response);
            return responseBody;
        } catch (Exception e) {
            log.error("invoke gw-connect instance failed ", e);
            return result;
        }
    }

    private void modifyServiceSn(GwRequestDTO<Map<String, Object>> gwRequestDTO, String genId) {
        if (gwRequestDTO.getGwSysHeader() == null || gwRequestDTO.getGwSysHeader().isEmpty()) {
            return;
        }
        final int length = 19;
        String sn = genId;
        if (sn.length() > length) {
            sn = sn.substring(0, length);
        }
        //16 位，加 "-" 防止elk脱敏
        if (sn.length() > 8) {
            sn = sn.substring(0, 8) +"-" + sn.substring(8);
        }
        gwRequestDTO.getGwSysHeader().put("CHL_SER_NO", sn);
    }
}