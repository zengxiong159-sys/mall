package com.qdbank.mall.order.business;


import com.qdbank.mall.bo.VirtualConfig;
import com.qdbank.mall.config.BsnConfig;
import com.qdbank.mall.constant.ProductEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VirtualConfigHandler {

    @Autowired
    BsnConfig bsnConfig;

    public VirtualConfig getVirtualConfig(ProductEnum productEnum1){
        List<VirtualConfig> products = bsnConfig.getProducts();
        for(VirtualConfig config : products){
            ProductEnum productEnum = ProductEnum.valueOf(config.getType());
            if(productEnum == productEnum1){
                return config;
            }
        }
        return null;
    }
}
