package com.qdbank.mall.mapper.trade;

import com.qdbank.mall.model.orderreport.OrderReportExportDO;
import com.qdbank.mall.model.trade.TradeIntegrationDO;
import com.qdbank.mall.model.trade.TradeIntegrationDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface TradeIntegrationDOMapper {
    long countByExample(TradeIntegrationDOExample example);

    int deleteByExample(TradeIntegrationDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TradeIntegrationDO record);

    int insertSelective(TradeIntegrationDO record);

    List<TradeIntegrationDO> selectByExample(TradeIntegrationDOExample example);

    TradeIntegrationDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TradeIntegrationDO record, @Param("example") TradeIntegrationDOExample example);

    int updateByExample(@Param("record") TradeIntegrationDO record, @Param("example") TradeIntegrationDOExample example);

    int updateByPrimaryKeySelective(TradeIntegrationDO record);

    int updateByPrimaryKey(TradeIntegrationDO record);

    List<TradeIntegrationDO> selectTradeIntegrationResult(OrderReportExportDO orderReportExportDO);

    int deleteByCreateTime(@Param("createTime") Date createTime);
}