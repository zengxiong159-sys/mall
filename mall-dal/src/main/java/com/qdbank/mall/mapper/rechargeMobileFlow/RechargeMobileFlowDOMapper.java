package com.qdbank.mall.mapper.rechargeMobileFlow;

import com.qdbank.mall.model.rechargeMobileFlow.RechargeMobileFlowDO;
import com.qdbank.mall.model.rechargeMobileFlow.RechargeMobileFlowDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RechargeMobileFlowDOMapper {
    long countByExample(RechargeMobileFlowDOExample example);

    int deleteByExample(RechargeMobileFlowDOExample example);

    int deleteByPrimaryKey(Long rechargeMobileFolwId);

    int insert(RechargeMobileFlowDO record);

    int insertSelective(RechargeMobileFlowDO record);

    List<RechargeMobileFlowDO> selectByExample(RechargeMobileFlowDOExample example);

    RechargeMobileFlowDO selectByPrimaryKey(Long rechargeMobileFolwId);

    int updateByExampleSelective(@Param("record") RechargeMobileFlowDO record, @Param("example") RechargeMobileFlowDOExample example);

    int updateByExample(@Param("record") RechargeMobileFlowDO record, @Param("example") RechargeMobileFlowDOExample example);

    int updateByPrimaryKeySelective(RechargeMobileFlowDO record);

    int updateByPrimaryKey(RechargeMobileFlowDO record);
}