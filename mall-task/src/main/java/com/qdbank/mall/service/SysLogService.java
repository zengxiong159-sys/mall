

package com.qdbank.mall.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.qdbank.mall.model.SysLogEntity;
import com.qdbank.mall.utils.PageUtils;

import java.util.Map;


/**
 * 系统日志
 *
 * @author ningyuehuai
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
