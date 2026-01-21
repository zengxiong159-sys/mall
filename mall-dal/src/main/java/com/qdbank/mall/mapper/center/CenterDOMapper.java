package com.qdbank.mall.mapper.center;

import com.qdbank.mall.model.center.CenterDO;
import com.qdbank.mall.model.center.CenterDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface CenterDOMapper {
    long countByExample(CenterDOExample example);

    int deleteByExample(CenterDOExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(CenterDO record);

    int insertSelective(CenterDO record);

    List<CenterDO> selectByExampleWithBLOBs(CenterDOExample example);

    List<CenterDO> selectByExample(CenterDOExample example);

    CenterDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CenterDO record, @Param("example") CenterDOExample example);

    int updateByExampleWithBLOBs(@Param("record") CenterDO record, @Param("example") CenterDOExample example);

    int updateByExample(@Param("record") CenterDO record, @Param("example") CenterDOExample example);

    int updateByPrimaryKeySelective(CenterDO record);

    int updateByPrimaryKeyWithBLOBs(CenterDO record);

    int updateByPrimaryKey(CenterDO record);
}