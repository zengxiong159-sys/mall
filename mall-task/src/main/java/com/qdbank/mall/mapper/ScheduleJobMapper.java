

package com.qdbank.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qdbank.mall.model.ScheduleJobEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 定时任务
 *
 * @author ningyuehuai
 */
@Mapper
public interface ScheduleJobMapper extends BaseMapper<ScheduleJobEntity> {
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
}
