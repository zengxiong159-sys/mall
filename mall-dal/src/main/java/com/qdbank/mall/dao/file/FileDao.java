package com.qdbank.mall.dao.file;

import com.qdbank.mall.mapper.activity.ActivityDOMapper;
import com.qdbank.mall.mapper.productpicurl.ProductPicUrlDOMapper;
import com.qdbank.mall.model.activity.ActivityDO;
import com.qdbank.mall.model.activity.ActivityDOExample;
import com.qdbank.mall.model.productpicurl.ProductPicUrlDO;
import com.qdbank.mall.model.productpicurl.ProductPicUrlDOExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author hongjh
 */
@Repository
@Slf4j
public class FileDao {

    @Autowired
    private ProductPicUrlDOMapper productPicUrlDOMapper;


    public int addFile(ProductPicUrlDO productPicUrlDO){
        return productPicUrlDOMapper.insert(productPicUrlDO);
    }

    /**
     * 删除操作
     * @param productId
     * @return
     */
    public int deleteFile(Long productId){
        ProductPicUrlDOExample example = new ProductPicUrlDOExample();
        ProductPicUrlDOExample.Criteria criteria  =example.createCriteria();
        criteria.andProductIdEqualTo(productId);
        return productPicUrlDOMapper.deleteByExample(example);
    }

    public List<ProductPicUrlDO> qryPicById(Long productId){
        ProductPicUrlDOExample example = new ProductPicUrlDOExample();
        ProductPicUrlDOExample.Criteria criteria  =example.createCriteria();
        criteria.andProductIdEqualTo(productId);
        List<ProductPicUrlDO>  list = productPicUrlDOMapper.selectByExample(example);
        return list;
    }





}