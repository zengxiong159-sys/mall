package com.qdbank.mall.mapper.advertisement;


import com.qdbank.mall.model.advertisement.AdvertisementDO;
import com.qdbank.mall.model.advertisement.AdvertisementDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdvertisementDOMapper {
    long countByExample(AdvertisementDOExample example);

    int deleteByExample(AdvertisementDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdvertisementDO record);

    int insertSelective(AdvertisementDO record);

    List<AdvertisementDO> selectByExample(AdvertisementDOExample example);

    AdvertisementDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdvertisementDO record, @Param("example") AdvertisementDOExample example);

    int updateByExample(@Param("record") AdvertisementDO record, @Param("example") AdvertisementDOExample example);

    int updateByPrimaryKeySelective(AdvertisementDO record);

    int updateByPrimaryKey(AdvertisementDO record);

    /**
     * 定时任务对状态自动过期
     * @return
     */
    int updateExpireStatus();
}