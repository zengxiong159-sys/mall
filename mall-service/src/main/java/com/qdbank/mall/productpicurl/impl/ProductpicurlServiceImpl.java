package com.qdbank.mall.productpicurl.impl;

import com.qdbank.mall.mapper.productpicurl.ProductPicUrlDOMapper;
import com.qdbank.mall.model.productpicurl.ProductPicUrlDO;
import com.qdbank.mall.model.productpicurl.ProductPicUrlDOExample;
import com.qdbank.mall.productpicurl.ProductpicurlService;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ProductpicurlServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/16 11:36
 * @Version 1.0
 **/
@Service
@Slf4j
public class ProductpicurlServiceImpl extends BaseServiceImpl implements ProductpicurlService {
    @Autowired
    private ProductPicUrlDOMapper productPicUrlDOMapper;
    @Override
    public void deletePicUrl() {
        List<ProductPicUrlDO> productPicUrlDOS = productPicUrlDOMapper.selectExpirePicUrl();
        for(ProductPicUrlDO productPicUrlDO : productPicUrlDOS){
            super.deleteFile(productPicUrlDO.getGroupId(),productPicUrlDO.getPicUrl());
            productPicUrlDOMapper.deleteByPrimaryKey(productPicUrlDO.getId());
        }
        log.info("定时任务清理图片：{}",productPicUrlDOS == null ? 0 : productPicUrlDOS.size());
    }
}
