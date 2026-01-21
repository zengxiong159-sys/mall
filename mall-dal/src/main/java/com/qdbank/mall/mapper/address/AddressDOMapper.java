package com.qdbank.mall.mapper.address;

import com.qdbank.mall.model.address.AddressDO;
import com.qdbank.mall.model.address.AddressDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressDOMapper {
    long countByExample(AddressDOExample example);

    int deleteByExample(AddressDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AddressDO record);

    int insertSelective(AddressDO record);

    List<AddressDO> selectByExample(AddressDOExample example);

    AddressDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AddressDO record, @Param("example") AddressDOExample example);

    int updateByExample(@Param("record") AddressDO record, @Param("example") AddressDOExample example);

    int updateByPrimaryKeySelective(AddressDO record);

    int updateByPrimaryKey(AddressDO record);
}