package com.qdbank.mall.dao.advertisment;

import com.qdbank.mall.mapper.advertisement.AdvertisementDOMapper;
import com.qdbank.mall.model.advertisement.AdvertisementDO;
import com.qdbank.mall.model.advertisement.AdvertisementDOExample;
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
public class AdvertismentDao {

    @Autowired
    private AdvertisementDOMapper advertisementDOMapper;


    public List<AdvertisementDO> activeList(Long moduleId, Long status, Date date) {
        AdvertisementDOExample advertisementDOExample = new AdvertisementDOExample();
        AdvertisementDOExample.Criteria criteria = advertisementDOExample.createCriteria();

        advertisementDOExample.setOrderByClause("advertisment_level,create_time");
        //模板id
        if(moduleId != null){
            criteria.andModuleIdEqualTo(moduleId);
        }
        //广告状态
        if(status != null){
            criteria.andStatusEqualTo(status);
        }
        //有效时间
        if(date!=null){
            criteria.andStartTimeLessThanOrEqualTo(date);
            criteria.andEndTimeGreaterThanOrEqualTo(date);
        }

        List<AdvertisementDO> advertisementDOS = advertisementDOMapper.selectByExample(advertisementDOExample);
        return advertisementDOS;
    }


    public AdvertisementDO qryAdvertisementById(Long advertisementId) {
        return  advertisementDOMapper.selectByPrimaryKey(advertisementId);
    }









}