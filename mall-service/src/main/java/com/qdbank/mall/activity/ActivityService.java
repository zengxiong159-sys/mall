package com.qdbank.mall.activity;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.request.activity.ActivityLikeQueryReqDTO;
import com.qdbank.mall.request.activity.ActivityReqDTO;
import com.qdbank.mall.request.activity.UpdateActivityReqDTO;
import com.qdbank.mall.request.activity.UpdateActivityStatusReqDTO;
import com.qdbank.mall.response.activity.ActivityResDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ActivityService {
    /**
     * 新建活动
     * @param activityReqDTO
     * @return
     */
    public int create(ActivityReqDTO activityReqDTO);

    /**
     * 修改活动
     * @param updateActivityReqDTO
     * @return
     */
    public int update(UpdateActivityReqDTO updateActivityReqDTO, MultipartFile file);

    /**
     * 活动详情
     * @param id 活动编号
     * @return
     */
    public ActivityResDTO detail(Long id);

    /**
     * 修改活动状态
     * @param id 活动编号
     * @return
     */
    public int updateStatus(UpdateActivityStatusReqDTO updateActivityStatusReqDTO);

    /**
     * 获取活动列表
     * @return
     */
    public PageInfo<ActivityResDTO> list(ActivityLikeQueryReqDTO activityLikeQueryReqDTO);
}
