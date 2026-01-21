package com.qdbank.mall.third.impl;

import com.qdbank.mall.api.IErrorCode;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.third.MallFrontService;
import com.qdbank.mall.util.HttpUtils;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.StringUtil;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商城前置
 */
@Service
@Slf4j
public class MallFrontServiceImpl implements MallFrontService {


    @Override
    public  Map sendFront(String url, Object params){
        log.info("before send url:{} 参数:[{}]",url,params!=null? JsonUtil.toJSONString(params):null);
        String  response = null;
        try{
            response = HttpUtils.getRestTemplate().postForObject(url, params, String.class);
            log.info("after send..[{}]",response);
        }catch (Exception e){
            log.error("访问C端前置异常",e);
            throw new ApiException(ResultCode.THIRD_SYSTEM_ERROR);
        }

        if(StringUtil.hasText(response)){
            Map result=JsonUtil.parseObject(response,Map.class);
            Integer code = (Integer) result.get("code");
            if(200 == code){
                return result;
            }
            ResultCode resultCode =ResultCode.getResultCode(code.longValue());
            if(resultCode==null){
                resultCode = ResultCode.SYSTEM_EXCEPTION;
            }
            throw new ApiException(resultCode);
        }

        throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
    }
}
