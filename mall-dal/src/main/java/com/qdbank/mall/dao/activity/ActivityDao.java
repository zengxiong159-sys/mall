package com.qdbank.mall.dao.activity;

import com.qdbank.mall.mapper.activity.ActivityDOMapper;
import com.qdbank.mall.model.activity.ActivityDO;
import com.qdbank.mall.model.activity.ActivityDOExample;
import com.qdbank.mall.model.address.AddressDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author hongjh
 */
@Repository
@Slf4j
public class ActivityDao {

    @Autowired
    private ActivityDOMapper activityDOMapper;


    public List<ActivityDO> qryActivitys(Long status, Date date){
        ActivityDOExample example =  new ActivityDOExample();
        example.setOrderByClause("activity_level,create_time");
        ActivityDOExample.Criteria criteria =example.createCriteria();

        //活动状态
        if(status != null){
            criteria.andStatusEqualTo(status);
        }
        //有效时间
        if(date!=null){
            criteria.andStartTimeLessThanOrEqualTo(date);
            criteria.andEndTimeGreaterThanOrEqualTo(date);
        }

        return activityDOMapper.selectByExample(example);
    }

    public ActivityDO qryActivityDetailById(Long activityId){
        return activityDOMapper.selectByPrimaryKey(activityId);
    }




}