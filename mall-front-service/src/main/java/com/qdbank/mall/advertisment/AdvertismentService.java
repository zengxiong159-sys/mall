package com.qdbank.mall.advertisment;

import com.qdbank.mall.request.advertisement.AdvertismentLikeQueryReqDTO;
import com.qdbank.mall.request.advertisement.AdvertismentReqDTO;
import com.qdbank.mall.request.advertisement.UpdateAdvertisementReqDTO;
import com.qdbank.mall.request.advertisement.UpdateAdvertisementStatusReqDTO;
import com.qdbank.mall.response.advertisement.AdvertisementResDTO;

import java.util.List;

public interface AdvertismentService {


    /**
     * 获取广告列表
     * @param advertismentLikeQueryReqDTO
     * @return
     */
     List<AdvertisementResDTO> list(AdvertismentLikeQueryReqDTO advertismentLikeQueryReqDTO);


    /**
     * 获取广告详情
     * @param id
     * @return
     */
     AdvertisementResDTO detail(Long id);
}
