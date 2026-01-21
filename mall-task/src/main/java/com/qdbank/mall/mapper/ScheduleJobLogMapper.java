

package com.qdbank.mall.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qdbank.mall.model.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 *
 * @author
 */
@Mapper
public interface ScheduleJobLogMapper extends BaseMapper<ScheduleJobLogEntity> {
	
}
