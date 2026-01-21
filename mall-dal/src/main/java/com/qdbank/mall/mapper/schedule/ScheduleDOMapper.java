package com.qdbank.mall.mapper.schedule;

import com.qdbank.mall.model.schedule.ScheduleDO;
import com.qdbank.mall.model.schedule.ScheduleDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ScheduleDOMapper {
    long countByExample(ScheduleDOExample example);

    int deleteByExample(ScheduleDOExample example);

    int deleteByPrimaryKey(Long jobId);

    int insert(ScheduleDO record);

    int insertSelective(ScheduleDO record);

    List<ScheduleDO> selectByExample(ScheduleDOExample example);

    ScheduleDO selectByPrimaryKey(Long jobId);

    int updateByExampleSelective(@Param("record") ScheduleDO record, @Param("example") ScheduleDOExample example);

    int updateByExample(@Param("record") ScheduleDO record, @Param("example") ScheduleDOExample example);

    int updateByPrimaryKeySelective(ScheduleDO record);

    int updateByPrimaryKey(ScheduleDO record);
}