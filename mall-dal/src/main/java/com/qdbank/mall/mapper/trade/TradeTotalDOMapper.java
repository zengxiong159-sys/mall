package com.qdbank.mall.mapper.trade;

import com.qdbank.mall.model.orderreport.OrderReportExportDO;
import com.qdbank.mall.model.trade.TradeTotalDO;
import com.qdbank.mall.model.trade.TradeTotalDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface TradeTotalDOMapper {
    long countByExample(TradeTotalDOExample example);

    int deleteByExample(TradeTotalDOExample example);

    int deleteByPrimaryKey(Long id);
    int insert(TradeTotalDO record);

    int insertSelective(TradeTotalDO record);

    List<TradeTotalDO> selectByExample(TradeTotalDOExample example);

    TradeTotalDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TradeTotalDO record, @Param("example") TradeTotalDOExample example);

    int updateByExample(@Param("record") TradeTotalDO record, @Param("example") TradeTotalDOExample example);

    int updateByPrimaryKeySelective(TradeTotalDO record);

    int updateByPrimaryKey(TradeTotalDO record);

    /**
     * 按指定条件查询汇总交易
     * @param record
     * @return
     */
    List<TradeTotalDO> selectTradeTotalResult(OrderReportExportDO record);

    int deleteByCreateTime(@Param("createTime") Date createTime);
}