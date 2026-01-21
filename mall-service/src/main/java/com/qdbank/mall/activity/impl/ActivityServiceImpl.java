package com.qdbank.mall.activity.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.activity.ActivityService;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.api.StatusEnum;
import com.qdbank.mall.coupon.AsyncInsertCouponService;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.activity.ActivityDOMapper;
import com.qdbank.mall.model.activity.ActivityDO;
import com.qdbank.mall.model.activity.ActivityDOExample;
import com.qdbank.mall.replace.ReplaceService;
import com.qdbank.mall.request.activity.ActivityLikeQueryReqDTO;
import com.qdbank.mall.request.activity.ActivityReqDTO;
import com.qdbank.mall.request.activity.UpdateActivityReqDTO;
import com.qdbank.mall.request.activity.UpdateActivityStatusReqDTO;
import com.qdbank.mall.response.activity.ActivityResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.StringUtil;
import com.qdbank.mall.util.TimeUtil;
import com.qdbank.mall.util.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName ActivityServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/25 15:30
 * @Version 1.0
 **/
@Service
@Slf4j
public class ActivityServiceImpl extends BaseServiceImpl implements ActivityService {
    @Autowired
    private ActivityDOMapper activityDOMapper;
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private AsyncInsertCouponService asyncInsertCouponService;
    @Autowired
    private ReplaceService replaceService;
    @Override
    public int create(ActivityReqDTO activityReqDTO) {
        try {
            //this.checkActivityExist(activityReqDTO.getActivityName());
            String [] filePath = super.uploadFile(activityReqDTO.getFile(), true);
            ActivityDO activityDO = new ActivityDO();
            BeanUtils.copyProperties(activityReqDTO,activityDO);
            activityDO.setId(super.generateId());
            activityDO.setStatus(StatusEnum.ACTIVITY_CLOSE_STATUS.getCode());
            activityDO.setGroupId(filePath[0]);
            activityDO.setPicUrl(filePath[2]);
            activityDO.setStartTime(activityReqDTO.getStartTime());
            activityDO.setEndTime(activityReqDTO.getEndTime());
            umsAdminService.injectUserValue(activityDO);
            return activityDOMapper.insert(activityDO);
        }catch (ApiException apiException){
            throw  apiException;
        } catch (Exception e){
            log.error("新建活动异常:{}"+e);
            throw new ApiException(ResultCode.BUILD_ACTIVITY_EXCEPTION);
        }
    }

    @Override
    public int update(UpdateActivityReqDTO updateActivityReqDTO, MultipartFile file) {
        String jumpUrl = updateActivityReqDTO.getJumpUrl();
        if(StringUtil.hasText(jumpUrl)) {
            String decodeJumpUrl = UrlUtil.getUrlDecoderString(jumpUrl);
            updateActivityReqDTO.setJumpUrl(decodeJumpUrl);
        }

        String[] filePath = null;
        ActivityDO activityDO = new ActivityDO();
        if(updateActivityReqDTO.getPicUpdateFlag() == 1 && file != null){//图片需要更新
            try {
                filePath = super.uploadFile(file, true);
                activityDO.setPicUrl(filePath[2]);
            }catch (ApiException apiException){
                throw  apiException;
            }catch (Exception e){
                log.error("update activity error:{}"+e);
                throw new ApiException(ResultCode.BUILD_ACTIVITY_EXCEPTION);
            }
            ActivityDO oldData = activityDOMapper.selectByPrimaryKey(updateActivityReqDTO.getId());
            if(oldData != null){
                //异步删除原始图片
                asyncInsertCouponService.deleteFile(oldData.getGroupId(),oldData.getPicUrl());
            }
        }else{
            activityDO.setPicUrl(null);
        }
        BeanUtils.copyProperties(updateActivityReqDTO,activityDO);
        umsAdminService.injectUpdateUserValue(activityDO);
        return activityDOMapper.updateByPrimaryKeySelective(activityDO);
    }

    @Override
    public ActivityResDTO detail(Long id) {
        ActivityDO activityDO = activityDOMapper.selectByPrimaryKey(id);
        ActivityResDTO activityResDTO = new ActivityResDTO();
        BeanUtils.copyProperties(activityDO,activityResDTO);
        activityResDTO.setPicUrl(replaceService.replaceUrl(activityResDTO.getPicUrl()));
        return activityResDTO;
    }

    @Override
    public int updateStatus(UpdateActivityStatusReqDTO updateActivityStatusReqDTO) {
        ActivityDO activityDO = new ActivityDO();
        BeanUtils.copyProperties(updateActivityStatusReqDTO,activityDO);
        umsAdminService.injectUpdateUserValue(activityDO);
        return activityDOMapper.updateByPrimaryKeySelective(activityDO);
    }

    @Override
    public PageInfo<ActivityResDTO> list(ActivityLikeQueryReqDTO activityLikeQueryReqDTO) {
        PageHelper.startPage(activityLikeQueryReqDTO.getPageNum(), activityLikeQueryReqDTO.getPageSize());
        ActivityDOExample activityDOExample = new ActivityDOExample();
        activityDOExample.setOrderByClause("create_time desc");
        ActivityDOExample.Criteria criteria = activityDOExample.createCriteria();
        if(StringUtils.isNotBlank(activityLikeQueryReqDTO.getActivityName())){
            criteria.andActivityNameLike("%"+activityLikeQueryReqDTO.getActivityName()+"%");
        }
        if(activityLikeQueryReqDTO.getStatus() != null){
            criteria.andStatusEqualTo(activityLikeQueryReqDTO.getStatus());
        }
        if(activityLikeQueryReqDTO.getStartTime() != null){
            criteria.andStartTimeGreaterThanOrEqualTo(TimeUtil.dateZeroChange(activityLikeQueryReqDTO.getStartTime()));
        }
        if(activityLikeQueryReqDTO.getEndTime() != null){
            criteria.andStartTimeLessThanOrEqualTo(TimeUtil.dateEndChange(activityLikeQueryReqDTO.getEndTime()));
        }
        List<ActivityDO> activityDOS = activityDOMapper.selectByExample(activityDOExample);
        List<ActivityResDTO> activityResDTOList = new ArrayList<>();
        for(ActivityDO activityDO : activityDOS){
            ActivityResDTO activityResDTO = new ActivityResDTO();
            BeanUtils.copyProperties(activityDO,activityResDTO);
            activityResDTO.setPicUrl(replaceService.replaceUrl(activityResDTO.getPicUrl()));
            activityResDTOList.add(activityResDTO);
        }
        return super.getPageInfo(activityDOS,activityResDTOList);
    }

    /**
     * 判断活动名称是否存在
     * @param activityName
     */
    public void checkActivityExist(String activityName){
        ActivityDOExample activityDOExample = new ActivityDOExample();
        activityDOExample.createCriteria().andActivityNameEqualTo(activityName);
        List<ActivityDO> list = activityDOMapper.selectByExample(activityDOExample);
        if(CollectionUtil.isNotEmpty(list)) throw new ApiException(ResultCode.ACTIVITY_NAME_EXIST);
    }

}
