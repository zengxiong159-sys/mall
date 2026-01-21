package com.qdbank.mall.mapper.send;

import com.qdbank.mall.model.send.SendReturnDO;
import com.qdbank.mall.model.send.SendReturnDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface SendReturnDOMapper {
    long countByExample(SendReturnDOExample example);

    int deleteByExample(SendReturnDOExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(SendReturnDO record);

    int insertSelective(SendReturnDO record);

    List<SendReturnDO> selectByExample(SendReturnDOExample example);

    SendReturnDO selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") SendReturnDO record, @Param("example") SendReturnDOExample example);

    int updateByExample(@Param("record") SendReturnDO record, @Param("example") SendReturnDOExample example);

    int updateByPrimaryKeySelective(SendReturnDO record);

    int updateByPrimaryKey(SendReturnDO record);
}