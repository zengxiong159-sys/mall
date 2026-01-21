package com.qdbank.mall.mapper.province;

import com.qdbank.mall.model.province.ProvinceDO;
import com.qdbank.mall.model.province.ProvinceDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProvinceDOMapper {
    long countByExample(ProvinceDOExample example);

    int deleteByExample(ProvinceDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProvinceDO record);

    int insertSelective(ProvinceDO record);

    List<ProvinceDO> selectByExample(ProvinceDOExample example);

    ProvinceDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProvinceDO record, @Param("example") ProvinceDOExample example);

    int updateByExample(@Param("record") ProvinceDO record, @Param("example") ProvinceDOExample example);

    int updateByPrimaryKeySelective(ProvinceDO record);

    int updateByPrimaryKey(ProvinceDO record);
}