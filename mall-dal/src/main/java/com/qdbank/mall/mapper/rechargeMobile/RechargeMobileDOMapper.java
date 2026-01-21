package com.qdbank.mall.mapper.rechargeMobile;

import com.qdbank.mall.model.rechargeMobile.RechargeMobileDO;
import com.qdbank.mall.model.rechargeMobile.RechargeMobileDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RechargeMobileDOMapper {
    long countByExample(RechargeMobileDOExample example);

    int deleteByExample(RechargeMobileDOExample example);

    int deleteByPrimaryKey(Long rechargeMobileId);

    int insert(RechargeMobileDO record);

    int insertSelective(RechargeMobileDO record);

    List<RechargeMobileDO> selectByExample(RechargeMobileDOExample example);

    RechargeMobileDO selectByPrimaryKey(Long rechargeMobileId);

    int updateByExampleSelective(@Param("record") RechargeMobileDO record, @Param("example") RechargeMobileDOExample example);

    int updateByExample(@Param("record") RechargeMobileDO record, @Param("example") RechargeMobileDOExample example);

    int updateByPrimaryKeySelective(RechargeMobileDO record);

    int updateByPrimaryKey(RechargeMobileDO record);
}