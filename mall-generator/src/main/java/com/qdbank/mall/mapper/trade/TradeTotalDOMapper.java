package com.qdbank.mall.mapper.trade;

import com.qdbank.mall.model.trade.TradeTotalDO;
import com.qdbank.mall.model.trade.TradeTotalDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
}