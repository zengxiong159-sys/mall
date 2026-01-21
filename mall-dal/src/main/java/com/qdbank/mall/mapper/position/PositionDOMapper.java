package com.qdbank.mall.mapper.position;

import com.qdbank.mall.model.position.PositionDO;
import com.qdbank.mall.model.position.PositionDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface PositionDOMapper {
    long countByExample(PositionDOExample example);

    int deleteByExample(PositionDOExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(PositionDO record);

    int insertSelective(PositionDO record);

    List<PositionDO> selectByExample(PositionDOExample example);

    PositionDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PositionDO record, @Param("example") PositionDOExample example);

    int updateByExample(@Param("record") PositionDO record, @Param("example") PositionDOExample example);

    int updateByPrimaryKeySelective(PositionDO record);

    int updateByPrimaryKey(PositionDO record);
}