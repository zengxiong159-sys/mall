package com.qdbank.mall.mapper.paramsconfig;

import com.qdbank.mall.model.paramsconfig.ParamsConfigDO;
import com.qdbank.mall.model.paramsconfig.ParamsConfigDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ParamsConfigDOMapper {
    long countByExample(ParamsConfigDOExample example);

    int deleteByExample(ParamsConfigDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ParamsConfigDO record);

    int insertSelective(ParamsConfigDO record);

    List<ParamsConfigDO> selectByExample(ParamsConfigDOExample example);

    ParamsConfigDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ParamsConfigDO record, @Param("example") ParamsConfigDOExample example);

    int updateByExample(@Param("record") ParamsConfigDO record, @Param("example") ParamsConfigDOExample example);

    int updateByPrimaryKeySelective(ParamsConfigDO record);

    int updateByPrimaryKey(ParamsConfigDO record);

}