package com.qdbank.mall.mapper.activity;

import com.qdbank.mall.model.activity.ActivityDO;
import com.qdbank.mall.model.activity.ActivityDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ActivityDOMapper {
    long countByExample(ActivityDOExample example);

    int deleteByExample(ActivityDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityDO record);

    int insertSelective(ActivityDO record);

    List<ActivityDO> selectByExample(ActivityDOExample example);

    ActivityDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityDO record, @Param("example") ActivityDOExample example);

    int updateByExample(@Param("record") ActivityDO record, @Param("example") ActivityDOExample example);

    int updateByPrimaryKeySelective(ActivityDO record);

    int updateByPrimaryKey(ActivityDO record);

    int updateExpireStatus();
}