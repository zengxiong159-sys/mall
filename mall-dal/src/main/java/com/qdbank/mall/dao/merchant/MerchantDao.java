package com.qdbank.mall.dao.merchant;

import com.qdbank.mall.mapper.merchant.MerchantDOMapper;
import com.qdbank.mall.mapper.productpicurl.ProductPicUrlDOMapper;
import com.qdbank.mall.model.merchant.MerchantDO;
import com.qdbank.mall.model.merchant.MerchantDOExample;
import com.qdbank.mall.model.productpicurl.ProductPicUrlDO;
import com.qdbank.mall.model.productpicurl.ProductPicUrlDOExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hongjh
 */
@Repository
@Slf4j
public class MerchantDao {

    @Autowired
    private MerchantDOMapper merchantDOMapper;


    /**
     * 通过商户编号获取商户信息
     * @param merchantNo
     * @return
     */
    public MerchantDO qryMerchantById(Long merchantNo){
        MerchantDO merchant =merchantDOMapper.selectByPrimaryKey(merchantNo);
        return merchant;
    }





}