package com.qdbank.mall.mapper.trade;

import com.qdbank.mall.model.trade.TradeFileDataDO;
import com.qdbank.mall.model.trade.TradeFileDataDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface TradeFileDataDOMapper {
    long countByExample(TradeFileDataDOExample example);

    int deleteByExample(TradeFileDataDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TradeFileDataDO record);

    int insertSelective(TradeFileDataDO record);

    List<TradeFileDataDO> selectByExample(TradeFileDataDOExample example);

    TradeFileDataDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TradeFileDataDO record, @Param("example") TradeFileDataDOExample example);

    int updateByExample(@Param("record") TradeFileDataDO record, @Param("example") TradeFileDataDOExample example);

    int updateByPrimaryKeySelective(TradeFileDataDO record);

    int updateByPrimaryKey(TradeFileDataDO record);

    int deleteByCreateTime(@Param("createTime")Date createTime);
}