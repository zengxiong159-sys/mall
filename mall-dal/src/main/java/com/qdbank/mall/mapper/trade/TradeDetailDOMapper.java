package com.qdbank.mall.mapper.trade;

import com.qdbank.mall.model.orderreport.OrderReportExportDO;
import com.qdbank.mall.model.trade.TradeDetailDO;
import com.qdbank.mall.model.trade.TradeDetailDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface TradeDetailDOMapper {
    long countByExample(TradeDetailDOExample example);

    int deleteByExample(TradeDetailDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TradeDetailDO record);

    int insertSelective(TradeDetailDO record);

    List<TradeDetailDO> selectByExample(TradeDetailDOExample example);

    TradeDetailDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TradeDetailDO record, @Param("example") TradeDetailDOExample example);

    int updateByExample(@Param("record") TradeDetailDO record, @Param("example") TradeDetailDOExample example);

    int updateByPrimaryKeySelective(TradeDetailDO record);

    int updateByPrimaryKey(TradeDetailDO record);

    List<TradeDetailDO> selectTradeDetailResult(OrderReportExportDO record);

    int deleteByCreateTime(@Param("createTime") Date createTime);
}