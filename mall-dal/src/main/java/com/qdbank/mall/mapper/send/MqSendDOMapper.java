package com.qdbank.mall.mapper.send;

import com.qdbank.mall.model.send.MqSendDO;
import com.qdbank.mall.model.send.MqSendDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface MqSendDOMapper {
    long countByExample(MqSendDOExample example);

    int deleteByExample(MqSendDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MqSendDO record);

    int insertSelective(MqSendDO record);

    List<MqSendDO> selectByExample(MqSendDOExample example);

    MqSendDO selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") MqSendDO record, @Param("example") MqSendDOExample example);

    int updateByExample(@Param("record") MqSendDO record, @Param("example") MqSendDOExample example);

    int updateByPrimaryKeySelective(MqSendDO record);

    int updateByPrimaryKey(MqSendDO record);
}