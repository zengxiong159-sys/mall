package com.qdbank.mall.integral.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qd.bank.pojo.allinpay.req.GwRequest;
import com.qd.bank.pojo.allinpay.req.GwRequestBody17070;
import com.qd.bank.pojo.allinpay.resp.AcctPoints;
import com.qd.bank.pojo.allinpay.resp.GwResponse;
import com.qd.bank.pojo.allinpay.resp.GwResponseBody17070;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.feign.GatewayFeignService;
import com.qdbank.mall.feign.mix.GwInvokeService;
import com.qdbank.mall.integral.IntegralService;
import com.qdbank.mall.mapper.skustock.SkustockDOMapper;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.request.gatawayrequest.GatawayReqDTO;
import com.qdbank.mall.request.gatawayrequest.GwHeader;
import com.qdbank.mall.request.integral.IntegralBalanceReqDTO;
import com.qdbank.mall.request.integral.IntegralQueryReqDTO;
import com.qdbank.mall.response.feign.FeignResponse;
import com.qdbank.mall.response.feign.integral.IntegralDetail;
import com.qdbank.mall.response.feign.integral.IntegralResDTO;
import com.qdbank.mall.response.integral.IntegralBalanceResDTO;
import com.qdbank.mall.spg.SpgService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName IntegralServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/3/24 17:55
 * @Version 1.0
 **/
@Service
@Slf4j
public class IntegralServiceImpl implements IntegralService {
    @Autowired
    private SpgService spgService;
    @Autowired
    private GatewayFeignService gatewayFeignService;
    @Autowired
    private SkustockDOMapper skustockDOMapper;

    @Value(value = "${gwConnectSwitch:Y}")
    private String gwConnectSwitch;

    @Resource
    GwInvokeService gwInvokeService;

    @Override
    public CommonResult<IntegralBalanceResDTO> queryPointBal(IntegralQueryReqDTO integralQueryReqDTO) {
        try {
            IntegralBalanceReqDTO integralBalanceReqDTO = new IntegralBalanceReqDTO();
            BeanUtils.copyProperties(integralQueryReqDTO,integralBalanceReqDTO);
            IntegralDetail integralDetail = this.getPointBal(integralBalanceReqDTO);
            IntegralBalanceResDTO integralBalanceResDTO = new IntegralBalanceResDTO();
            if(integralDetail == null){
                integralBalanceResDTO.setHideFlag(0);
            }else{
                integralBalanceResDTO.setPointBal(integralDetail.getPointBal());
                queryIntegralDeduct(integralQueryReqDTO.getProductSkuId(),integralBalanceResDTO);
            }
            integralBalanceResDTO.setSignFlag(signQuery(integralQueryReqDTO.getSessionKey(),integralQueryReqDTO.getCustId()));
            return CommonResult.success(integralBalanceResDTO);
        }catch (ApiException apiException){
            throw  apiException;
        }catch (Exception e){
            log.error("调用外联获取积分服务异常:{}",e);
            return CommonResult.failed(ResultCode.SYSTEM_EXCEPTION);
        }
    }

    @Override
    public IntegralDetail getPointBal(IntegralBalanceReqDTO integralBalanceReqDTO) {
        try {
            GatawayReqDTO gatawayReqDTO = new GatawayReqDTO();
            GwHeader gwHeader = new GwHeader();
            gwHeader.setSvcId("17070");
            gatawayReqDTO.setGwSysHeader(gwHeader);
            gatawayReqDTO.setReqBody(integralBalanceReqDTO);
            Long start = System.currentTimeMillis();
            // 直接調用connect
            if("Y".equalsIgnoreCase(gwConnectSwitch)){
                GwRequestBody17070 gwRequestBody17070 = new GwRequestBody17070();
                gwRequestBody17070.setCustId(integralBalanceReqDTO.getCustId());
                GwRequest<GwRequestBody17070> gwRequest = new GwRequest<>("17070", gwRequestBody17070);
                GwResponse<GwResponseBody17070> response= gwInvokeService.execute(gwRequest, GwResponseBody17070.class);
                log.info("积分余额查询请求参数:{},响应结果:{} 耗时", JSON.toJSONString(gwRequest), JSON.toJSONString(response),(System.currentTimeMillis() - start));
                if (response==null || !response.success() || null==response.getRespBody()){
                    throw new ApiException(response != null ? response.msg() : ResultCode.SYSTEM_EXCEPTION.getMessage());
                }
                GwResponseBody17070 respBody = response.getRespBody();
                List<AcctPoints> acctPoints = respBody.getAcctPoints();
                if(CollectionUtil.isNotEmpty(acctPoints)){
                    acctPoints.stream().filter(p->{
                        return "22".equals(p.getAcctType());
                    }).collect(Collectors.toList());
                }
                if (CollectionUtil.isNotEmpty(acctPoints)){
                    AcctPoints acctPoints1 = acctPoints.get(0);
                    IntegralDetail integralDetail=new IntegralDetail();
                    BeanUtils.copyProperties(acctPoints1,integralDetail);
                    return integralDetail;
                }
                return null;
            }else {
                FeignResponse<IntegralResDTO> feignResponse = gatewayFeignService.getIntegralByCustId(gatawayReqDTO);
                log.info("积分余额查询请求参数:{},响应结果:{} 耗时", JSON.toJSONString(integralBalanceReqDTO), JSON.toJSONString(feignResponse),(System.currentTimeMillis() - start));
                if(feignResponse == null || !feignResponse.success() || feignResponse.getRespBody() == null){
                    throw new ApiException(feignResponse != null ? feignResponse.getErrorMsg() : ResultCode.SYSTEM_EXCEPTION.getMessage());
                }
                IntegralResDTO integralResDTO = feignResponse.getRespBody();
                List<IntegralDetail> integralDetails = integralResDTO.getAcctPoints();
                if(CollectionUtil.isNotEmpty(integralDetails)){
                    //过滤22账户下的积分数据
                    List<IntegralDetail> integralDetailList = integralDetails.stream().filter(integralDetail -> {
                        return "22".equals(integralDetail.getAcctType());
                    }).collect(Collectors.toList());
                    if(CollectionUtil.isNotEmpty(integralDetailList)){
                        IntegralDetail integralDetail = integralDetailList.get(0);
                        return integralDetail;
                    }
                }
                return  null;
            }



        }catch (Exception e){
            log.info("客户号:{} 调用外联积分余额查询接口异常:{}",integralBalanceReqDTO.getCustId(),e);
            throw new ApiException(ResultCode.INTEGRAL_QUERY_ERROR);
        }
    }

    @Override
    public CommonResult integralSign(IntegralQueryReqDTO integralBalanceReqDTO) {
        String signQueryResult = spgService.decryptData(integralBalanceReqDTO.getSessionKey(),"SPG_INTEGRAL_001",integralBalanceReqDTO.getCustId());
        log.info("请求小程序签到接口响应结果：{}",signQueryResult);
        if(StringUtils.isBlank(signQueryResult)) return CommonResult.failed(ResultCode.INTEGRAL_SIGN_ERROR);
        JSONObject jsonObject = JSON.parseObject(signQueryResult);
        if(!(jsonObject.containsKey("errorCode") && "0".equals(jsonObject.get("errorCode").toString()))) return CommonResult.failed(ResultCode.INTEGRAL_SIGN_ERROR);
        JSONObject dataMap = jsonObject.getJSONObject("dataMap");
        if(dataMap == null || !dataMap.containsKey("signStat") || !"S".equals(dataMap.get("signStat").toString()))  return CommonResult.failed(ResultCode.INTEGRAL_SIGN_ERROR);
        return CommonResult.success(ResultCode.INTEGRAL_SIGN_SUCCESS.getMessage());
    }

    /**
     * 获取积分抵扣
     * @param skuId
     * @param
     * @return
     */
    private IntegralBalanceResDTO queryIntegralDeduct (Long skuId,IntegralBalanceResDTO integralBalanceResDTO){
        SkustockDO skustockDO = skustockDOMapper.selectByPrimaryKey(skuId);
        if(skustockDO == null) throw new ApiException(ResultCode.PRODUCT_SKU_STOCK_NOT_EXIST);
        integralBalanceResDTO.setHideFlag(0);
        if(skustockDO.getMaxIntegralDeduct() != null){
            integralBalanceResDTO.setHideFlag(1);
            if(StringUtils.isBlank(integralBalanceResDTO.getPointBal())){
                integralBalanceResDTO.setDefaultIntegralCash(new BigDecimal("0.00"));
                return integralBalanceResDTO;
            }
            if(Long.valueOf(integralBalanceResDTO.getPointBal()) < skustockDO.getMaxIntegralDeduct()){
                integralBalanceResDTO.setDefaultIntegralCash(BigDecimal.valueOf(Double.valueOf(integralBalanceResDTO.getPointBal())).divide(new BigDecimal(100)));
            }else{
                integralBalanceResDTO.setDefaultIntegralCash(BigDecimal.valueOf(skustockDO.getMaxIntegralDeduct()).divide(new BigDecimal(100)));
            }
        }
        return  integralBalanceResDTO;
    }

    /**
     * 签到查询接口 判断用户是否可以签到
     * @param sessionKey
     * @return
     */
    private Integer signQuery(String sessionKey,String custId){
        try {
            String signQueryResult = spgService.decryptData(sessionKey,"CC800001_3",custId);
            log.info("请求小程序签到查询接口响应结果：{}",signQueryResult);
            if(StringUtils.isBlank(signQueryResult)) return  0;
            JSONObject jsonObject = JSON.parseObject(signQueryResult);
            if(jsonObject.containsKey("actived") && !"true".equals(jsonObject.get("actived").toString())) return 0;
            if(jsonObject.containsKey("signed") && !"true".equals(jsonObject.get("signed").toString())){
                return 1;
            }
        }catch (Exception e){
            log.info("请求小程序查询签到接口异常：{}",e);
            return 0;
        }
        return 0;
    }
}
