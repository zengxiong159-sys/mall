

package com.qdbank.mall.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.qdbank.mall.model.ScheduleJobLogEntity;
import com.qdbank.mall.utils.PageUtils;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author ningyuehuai
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
}
