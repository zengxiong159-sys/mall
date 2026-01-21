package com.qdbank.mall.activity.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qdbank.mall.activity.ActivityService;
import com.qdbank.mall.activity.mapper.ActivityMapper;
import com.qdbank.mall.dao.activity.ActivityDao;
import com.qdbank.mall.model.activity.ActivityDO;
import com.qdbank.mall.request.activity.ActivityLikeQueryReqDTO;
import com.qdbank.mall.response.activity.ActivityResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName AddressServiceImpl
 * @Description TODO
 * @Author hongjh
 * @Date 2021/3/17 19:41
 * @Version 1.0
 **/
@Service
@Slf4j
public class ActivityServiceImpl extends BaseServiceImpl implements ActivityService {

    @Autowired
    ActivityDao activityDao;


    @Autowired
    private ActivityMapper activityMapper;



    @Override
    public List<ActivityResDTO> qryActivitys(ActivityLikeQueryReqDTO req) {
        /**
         * 缓存处理？？？分页
         */
        List<ActivityDO> result=activityDao.qryActivitys(1L,new Date());
        List<ActivityResDTO> qresult=activityMapper.poToDTOList(result);
        return qresult;
    }

    @Override
    public ActivityResDTO qryActivityDetail(Long activityId) {
        ActivityDO result=activityDao.qryActivityDetailById(activityId);
        ActivityResDTO qresult=activityMapper.poToDTO(result);
        return qresult;
    }





}
