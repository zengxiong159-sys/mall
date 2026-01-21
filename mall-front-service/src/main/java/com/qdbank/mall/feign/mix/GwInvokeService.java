package com.qdbank.mall.feign.mix;



import com.qd.bank.pojo.allinpay.req.GwRequest;
import com.qd.bank.pojo.allinpay.resp.GwResponse;
import org.springframework.http.HttpHeaders;

/**
 * @author Qdccb
 */
public interface GwInvokeService {
    /**
     * 需要考虑异常处理
     *
     * @param serviceId 通联接口号
     * @param req       请求报文
     * @return json result
     */
    String execute(String serviceId, String req);


    /**
     *
     * @param gwRequest           通联请求dto
     * @param gwResponseBodyClass 请求体 class
     * @return GwResponse
     */
    <T> GwResponse<T> execute(GwRequest<?> gwRequest, Class<T> gwResponseBodyClass);

    /**
     * 调用通联接口
     * @param gwRequest           通联请求dto
     * @param gwResponseBodyClass 请求体 class
     * @headers HttpHeaders
     * @return GwResponse
     */
    <T> GwResponse<T> execute(GwRequest<?> gwRequest, Class<T> gwResponseBodyClass, HttpHeaders headers);


}
