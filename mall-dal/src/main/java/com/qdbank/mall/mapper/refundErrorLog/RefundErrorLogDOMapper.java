package com.qdbank.mall.mapper.refundErrorLog;

import com.qdbank.mall.model.refundErrorLog.RefundErrorLogDO;
import com.qdbank.mall.model.refundErrorLog.RefundErrorLogDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RefundErrorLogDOMapper {
    long countByExample(RefundErrorLogDOExample example);

    int deleteByExample(RefundErrorLogDOExample example);

    int deleteByPrimaryKey(Long refundErrorLogId);

    int insert(RefundErrorLogDO record);

    int insertSelective(RefundErrorLogDO record);

    List<RefundErrorLogDO> selectByExample(RefundErrorLogDOExample example);

    RefundErrorLogDO selectByPrimaryKey(Long refundErrorLogId);

    int updateByExampleSelective(@Param("record") RefundErrorLogDO record, @Param("example") RefundErrorLogDOExample example);

    int updateByExample(@Param("record") RefundErrorLogDO record, @Param("example") RefundErrorLogDOExample example);

    int updateByPrimaryKeySelective(RefundErrorLogDO record);

    int updateByPrimaryKey(RefundErrorLogDO record);
}