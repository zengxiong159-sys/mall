package com.qdbank.mall.controller.external;

import com.alibaba.fastjson.JSON;
import com.qdbank.mall.external.ExternalService;
import com.qdbank.mall.model.external.ExternalDO;
import com.qdbank.mall.request.external.ReviAgreeReqDTO;
import com.qdbank.mall.request.product.ProductIdsReqDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author sunhaoran
 * @Date 2022/5/13 09:55
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/external")
@Api(tags = "ExternalController")
public class ExternalController {

    @Autowired
    ExternalService externalService;

    /**
     * MCI产品
     */
    public static final String MGT_MCI_PRODUCT_SERVICE="mgtMciPictureService";
    /**
     * mci产品请求url
     */
    private static final String MGT_COMMON_URL = "gateway/service";

    /**
     * 成功响应码
     */
    private static final String SUCCESS_CODE = "0";

    /**
     * 证件号码不校验，上送默认值
     */
    private static final String ID_NO = "123";

    /**
     * 商品下架
     */
    public static final String PRODUCT_DOWN_SERVICE="mgtMallProductService";
    @ApiOperation(value = "商城协议配置")
    @PostMapping("/configPro")
    public Map<String,Object> configPro(@Validated @RequestBody ReviAgreeReqDTO reviAgreeReqDTO){
        Map<String, Object> dataMap = new HashMap<>();
        Map<String,Object> result = new HashMap<>();
        dataMap.put("channelId", reviAgreeReqDTO.getChannelId());
        dataMap.put("sceneId", reviAgreeReqDTO.getSceneId());

        String jsonString = JSON.toJSONString(new HashMap<String, Object>() {{
            put("idNo",ID_NO);
            put("service", MGT_MCI_PRODUCT_SERVICE);
            put("dataMap", dataMap);
        }});

        ExternalDO externalDO = externalService.callMgtService(jsonString, MGT_COMMON_URL);
        if(null != externalDO && SUCCESS_CODE.equals(externalDO.getCode())){
            result.put("errorCode", SUCCESS_CODE);
            result.put("dataMap", externalDO.getData());
        }

        return result;
    }

    @ApiOperation(value = "商品下架通知北斗",hidden = true)
    @RequestMapping("/productDown")
    public Map<String,Object> productDown(@RequestBody ProductIdsReqDTO productIdsReqDTO){
        Map<String, Object> dataMap = new HashMap<>();
        Map<String,Object> result = new HashMap<>();

        String jsonString = JSON.toJSONString(new HashMap<String, Object>() {{
            put("idNo",productIdsReqDTO.getProductIds());
            put("service", PRODUCT_DOWN_SERVICE);
            put("dataMap", dataMap);
        }});

        ExternalDO externalDO = externalService.callMgtService(jsonString, MGT_COMMON_URL);
        if(null != externalDO && SUCCESS_CODE.equals(externalDO.getCode())){
            result.put("errorCode", SUCCESS_CODE);
            result.put("dataMap", externalDO.getData());
        }

        return result;
    }
}
