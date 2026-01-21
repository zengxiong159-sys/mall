package com.qdbank.mall.order.business.handler;

import com.alibaba.fastjson.JSON;
import com.qdbank.mall.api.StatusEnum;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.constant.payment.OilSupply;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.order.business.CommonPaymentAware;
import com.qdbank.mall.request.order.CommonOrderReqDTO;
import com.qdbank.mall.request.order.MobileRechargePaymenOrderReqDTO;
import com.qdbank.mall.request.order.OilRechargePaymenOrderReqDTO;
import com.qdbank.mall.response.product.ProductSkuResDTO;
import com.qdbank.mall.response.skustock.SkustockResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 虚拟商品支付
 * @ClassName OilCardPaymenServiceImpl
 * @Description TODO
 * @Author hongjh
 * @Date 2021/3/17 19:41
 * @Version 1.0
 **/
@Component("oilCardPayment")
@Slf4j
public  class OilCardPaymenHandler  extends VirtualPaymenHandler {

    @Override
    public ProductEnum getProductType() {
        return ProductEnum.OIL_CARD;
    }


    /**
     * 获取油卡 运营商产品列表
     */
    public Map<OilSupply,List<SkustockResDTO>> qryOilSkus(boolean checkStatus){
        Map<OilSupply,List<SkustockResDTO>> oil = new HashMap<>();
        List<SkustockResDTO> zsh = new ArrayList<>();
        List<SkustockResDTO> zsy = new ArrayList<>();

        oil.put(OilSupply.ZSH,zsh);
        oil.put(OilSupply.ZSY,zsy);

        //获取油卡列表集合
        List<ProductSkuResDTO> products  =this.qryVirtualProduct();

        //运营商产品区分处理
        for(ProductSkuResDTO productSku : products){
            //已上架
            if(checkStatus && !StatusEnum.PUBLIST_STATUS_UP.getCode().equals(productSku.getPublishStatus())){
                continue;
            }
            //规格处理
            for(SkustockResDTO res : productSku.getSkustocks()){
                //已上架
                if(checkStatus && 0L!=res.getStatus()){
                    continue;
                }

                String spData = res.getProductSpData();
                Map params =this.formate(spData);
                res.setSkuParams(params);
                //区分中石化和中石油
                String supper = (String) params.get("运营商");
                if(OilSupply.ZSH.name.equals(supper)){
                    zsh.add(res);
                }else if(OilSupply.ZSY.name.equals(supper)){
                    zsy.add(res);
                }
            }
        }
        return oil;
    }





    @Override
    public void paySuccess(OrderDO order) {

    }

    @Override
    public void payFail(OrderDO order) {

    }


}
