package com.qdbank.mall.activity;

import com.qdbank.mall.request.activity.ActivityLikeQueryReqDTO;
import com.qdbank.mall.request.address.AddressReqDTO;
import com.qdbank.mall.request.address.SetDefaultAddressReqDTO;
import com.qdbank.mall.request.address.UpdateAddressReqDTO;
import com.qdbank.mall.response.activity.ActivityResDTO;
import com.qdbank.mall.response.address.AddressResDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ActivityService {


    List<ActivityResDTO> qryActivitys(ActivityLikeQueryReqDTO activityLikeQueryReqDTO);

    ActivityResDTO qryActivityDetail(Long activityId);
}
