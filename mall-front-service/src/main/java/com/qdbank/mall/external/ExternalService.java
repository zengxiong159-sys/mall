package com.qdbank.mall.external;

import com.qdbank.mall.model.external.ExternalDO;

/**
 * @Author sunhaoran
 * @Date 2022/5/13 10:58
 * @Version 1.0
 */
public interface ExternalService  {

    ExternalDO callMgtService(String commonReq, String childUrl);
}
