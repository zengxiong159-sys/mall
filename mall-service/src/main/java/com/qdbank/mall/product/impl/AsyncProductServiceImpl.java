package com.qdbank.mall.product.impl;

import com.qdbank.mall.feign.MallFeignService;
import com.qdbank.mall.product.AsyncProductService;
import com.qdbank.mall.request.product.ProductIdsReqDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;


/**
 * AsyncProductServiceImpl
 *
 * @author shaoshihang
 * @date 2021/3/18 15:49
 * @since 1.0.0
 */
@EnableAsync
@Service
@Slf4j
public class AsyncProductServiceImpl extends BaseServiceImpl implements AsyncProductService {
    @Autowired
    private MallFeignService mallFeignService;
    @Override
    @Async
    public void deletePicture(String groupName, String filePath) {
        if (StringUtils.isNotEmpty(groupName) && StringUtils.isNotEmpty(filePath)){
            super.deleteFile(groupName,filePath);
        }
    }

    @Override
    @Async
    public void productDownNoticeMgt(Long productId) {
        ProductIdsReqDTO productIdsReqDTO = new ProductIdsReqDTO();
        productIdsReqDTO.setProductIds(productId+"");
        log.info("异步通知北斗商品下架通知:{}",productId);
        mallFeignService.productDown(productIdsReqDTO);
    }
}
