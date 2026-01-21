package com.qdbank.mall.dao.prefecture;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qdbank.mall.enums.PrefectureTypeEnum;
import com.qdbank.mall.mapper.prefecture.PrefectureDOMapper;
import com.qdbank.mall.mapper.product.ProductDOMapper;
import com.qdbank.mall.mapper.skustock.SkustockDOMapper;
import com.qdbank.mall.model.prefecture.PrefectureDO;
import com.qdbank.mall.model.prefecture.PrefectureDOExample;
import com.qdbank.mall.model.prefecture.PrefectureInfoDO;
import com.qdbank.mall.model.product.ProductInfoDO;
import com.qdbank.mall.model.product.ProductInfoQueryDO;
import com.qdbank.mall.model.product.ProductSkuDO;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.model.skustock.SkustockDOExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author hongjh
 */
@Repository
public class PrefectureDao {

    @Autowired
    private PrefectureDOMapper prefectureDOMapper;

    @Autowired
    private ProductDOMapper productDOMapper;

    @Autowired
    private SkustockDOMapper skustockDOMapper;


    public List<PrefectureDO> qryPrefectures(Date date, Long status, int order){
        PrefectureDOExample pdo = new PrefectureDOExample();
        pdo.setOrderByClause("prefecture_level"+(order==-1?" desc":"")+",create_time");
        PrefectureDOExample.Criteria criteria =pdo.createCriteria();
        criteria.andStatusEqualTo(status);
        criteria.andStartTimeLessThanOrEqualTo(date);
        criteria.andEndTimeGreaterThanOrEqualTo(date);
        criteria.andPrefectureTypeEqualTo(PrefectureTypeEnum.NORMAL.getCode());
        return prefectureDOMapper.selectByExample(pdo);
    }

    public List<PrefectureInfoDO> qryProductsByPrefectureId( Long prefectureId){
        PrefectureDO prefectureDO = new PrefectureDO();
        prefectureDO.setId(prefectureId);
        return prefectureDOMapper.selectPrefectureInfoByParams(prefectureDO);
    }

    public List<ProductSkuDO> qryProductsByPrefectureId2( Long prefectureId){
        PrefectureDO prefectureDO = new PrefectureDO();
        prefectureDO.setId(prefectureId);
        return prefectureDOMapper.selectPrefectureProductInfoByParams(prefectureDO);
    }



    public List<ProductSkuDO> selectProductSkuInfo(ProductInfoQueryDO query){
        return productDOMapper.selectProductSkuInfo(query);
    }

    public List<ProductSkuDO> selectProductDetailSkuInfo(ProductInfoQueryDO query){
        return productDOMapper.selectProductDetailSkuInfo(query);
    }



    public int manageSkuStockById(Long productSkuId, Long stockNum){
        return skustockDOMapper.updateSkuStockById(stockNum,productSkuId,stockNum>0);
    }


    public int updateSkuStockStatusById(Long productSkuId, Long status){
        SkustockDO skustock  =new SkustockDO();
        skustock.setProductSkuId(productSkuId);
        skustock.setStatus(status);
        skustock.setUpdateTime(new Date());
        return skustockDOMapper.updateByProductSkuId(skustock);
    }









}