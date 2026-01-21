package com.qdbank.mall.mapper.schedule;

import com.qdbank.mall.model.schedule.ScheduleJobLogDO;
import com.qdbank.mall.model.schedule.ScheduleJobLogDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ScheduleJobLogDOMapper {
    long countByExample(ScheduleJobLogDOExample example);

    int deleteByExample(ScheduleJobLogDOExample example);

    int deleteByPrimaryKey(Long logId);

    int insert(ScheduleJobLogDO record);

    int insertSelective(ScheduleJobLogDO record);

    List<ScheduleJobLogDO> selectByExample(ScheduleJobLogDOExample example);

    ScheduleJobLogDO selectByPrimaryKey(Long logId);

    int updateByExampleSelective(@Param("record") ScheduleJobLogDO record, @Param("example") ScheduleJobLogDOExample example);

    int updateByExample(@Param("record") ScheduleJobLogDO record, @Param("example") ScheduleJobLogDOExample example);

    int updateByPrimaryKeySelective(ScheduleJobLogDO record);

    int updateByPrimaryKey(ScheduleJobLogDO record);

    int deleteJogLog();
}