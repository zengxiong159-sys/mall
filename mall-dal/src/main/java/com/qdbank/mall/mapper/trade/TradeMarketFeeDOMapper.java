package com.qdbank.mall.mapper.trade;

import com.qdbank.mall.model.orderreport.OrderReportExportDO;
import com.qdbank.mall.model.trade.TradeMarketFeeDO;
import com.qdbank.mall.model.trade.TradeMarketFeeDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface TradeMarketFeeDOMapper {
    long countByExample(TradeMarketFeeDOExample example);

    int deleteByExample(TradeMarketFeeDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TradeMarketFeeDO record);

    int insertSelective(TradeMarketFeeDO record);

    List<TradeMarketFeeDO> selectByExample(TradeMarketFeeDOExample example);

    TradeMarketFeeDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TradeMarketFeeDO record, @Param("example") TradeMarketFeeDOExample example);

    int updateByExample(@Param("record") TradeMarketFeeDO record, @Param("example") TradeMarketFeeDOExample example);

    int updateByPrimaryKeySelective(TradeMarketFeeDO record);

    int updateByPrimaryKey(TradeMarketFeeDO record);

    List<TradeMarketFeeDO> selectTradeMarketFeeResult(OrderReportExportDO orderReportExportDO);

    int deleteByCreateTime(@Param("createTime") Date createTime);
}