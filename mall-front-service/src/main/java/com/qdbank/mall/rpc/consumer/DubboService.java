package com.qdbank.mall.rpc.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;
import com.qdbank.mall.rpc.DubboError;
import com.qdbank.mall.rpc.RRException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 调用中台服务
 * @author Hongjianhua
 */
@Slf4j
@Service
public class DubboService {

    public static final String BC_FLOW_SERVICE_INTERFACE = "com.ifp.core.flow.service.BcFlowService";
    public static final String CC_FLOW_SERVICE_INTERFACE = "com.ifp.core.flow.service.CcFlowService";
    public static final String AC_FLOW_SERVICE_INTERFACE = "com.ifp.core.flow.service.AcFlowService";
    public static final String SC_FLOW_SERVICE_INTERFACE = "com.ifp.core.flow.service.ScFlowService";

    @Resource
    private ApplicationConfig applicationConfig;

    @Resource
    private RegistryConfig registryConfig;

    private String DATA_KEY="dataMap";

    private String ERROR_CODE="errorCode";

    private String ERROR_MSG = "errorMsg";

    private String SUCCESS = "0";

    @SuppressWarnings("unchecked")
    public Map<String, Object> call(String group, Map<String, Object> map) {
        Map req = new HashMap();
        //初始化
        specialDeal(map);
        req.put(DATA_KEY,map);

        GenericService service = getDubboService(group);
        log.info("dubbo req [{}]-[{}]",group, JSON.toJSONString(req));
        Map<String,Object> result =(Map<String, Object>) service.$invoke("execute", new String[]{Map.class.getCanonicalName()}, new Object[]{req});
        log.info("dubbo rsp [{}]-[{}]",group, JSON.toJSONString(result));
        if(!CollectionUtils.isEmpty(result)){
            checkException(result);

            Map<String, Object> nodeMap = (Map<String, Object>)result.get(DATA_KEY);
            log.info("是否包含errorCode:{}",!nodeMap.containsKey("errorCode"));
            if(nodeMap.get(ERROR_CODE) == null || (nodeMap.get(ERROR_CODE) != null && StringUtils.isBlank(nodeMap.get(ERROR_CODE).toString()))){
                nodeMap.put("errorCode",result.get(ERROR_CODE));
                nodeMap.put("errorMsg",result.get(ERROR_MSG));
            }
            return nodeMap;
        }
        throw new RRException(DubboError.NETWORK_ERROR);
    }

    /**
     * 报错
     * @param result
     */
    private void checkException(Map<String, Object> result) {
        String errorCode = (String) result.get(ERROR_CODE);
        if(!SUCCESS.equals(errorCode)){
            throw new RRException(errorCode,(String)result.get(ERROR_MSG));
        }
    }

    private GenericService getDubboService(String group) {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setApplication(applicationConfig);
        reference.setRegistry(registryConfig);

        if(group.startsWith("BC")){
            reference.setInterface(DubboService.BC_FLOW_SERVICE_INTERFACE);
        }else if(group.startsWith("CC")){
            reference.setInterface(DubboService.CC_FLOW_SERVICE_INTERFACE);
        } else if(group.startsWith("AC")){
            reference.setInterface(DubboService.AC_FLOW_SERVICE_INTERFACE);
        } else if(group.startsWith("SC")){
            reference.setInterface(DubboService.SC_FLOW_SERVICE_INTERFACE);
        }
        reference.setGeneric(true);
        reference.setGroup(group);
        reference.setVersion("1.0");
        reference.setTimeout(30 * 1000);
        reference.setRetries(0);
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        return cache.get(reference);
    }


    protected Map<String,Object> specialDeal(Map<String,Object> param){
        param.putIfAbsent("channel", "SPG");
        param.put("csmrId", "179510");
        param.put("custType", "99");
        param.put("tellerId", "*SPGUSER");
        param.put("userId", "*SPGUSER");
        param.put("chl", "SPG");
        param.put("prodId", "P010080001");
        param.put("transSrc", "SPG");
        param.put("transChannel", "SPG");
        param.put("appletType", "1");
//		param.put("svcId", "1800");
//		param.put("chanNo", "18");
        //param.remove("multiFileMap");
        //param.remove("org.springframework.web.servlet.HandlerMapping.bestMatchingPattern");
        //param.remove("org.springframework.web.servlet.HandlerMapping.pathWithinHandlerMapping");
        return param;
    }

}
