package com.qdbank.mall.mapper.area;

import com.qdbank.mall.model.area.AreaDO;
import com.qdbank.mall.model.area.AreaDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AreaDOMapper {
    long countByExample(AreaDOExample example);

    int deleteByExample(AreaDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AreaDO record);

    int insertSelective(AreaDO record);

    List<AreaDO> selectByExample(AreaDOExample example);

    List<AreaDO> selectProvince();

    AreaDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AreaDO record, @Param("example") AreaDOExample example);

    int updateByExample(@Param("record") AreaDO record, @Param("example") AreaDOExample example);

    int updateByPrimaryKeySelective(AreaDO record);

    int updateByPrimaryKey(AreaDO record);
}