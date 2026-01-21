package com.qdbank.mall.advertisment;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.request.advertisement.AdvertismentLikeQueryReqDTO;
import com.qdbank.mall.request.advertisement.AdvertismentReqDTO;
import com.qdbank.mall.request.advertisement.UpdateAdvertisementReqDTO;
import com.qdbank.mall.request.advertisement.UpdateAdvertisementStatusReqDTO;
import com.qdbank.mall.response.advertisement.AdvertisementResDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdvertismentService {
    /**
     * 创建广告
     * @param advertismentReqDTO
     * @return
     */
    public int create(AdvertismentReqDTO advertismentReqDTO);

    /**
     * 获取广告列表
     * @param advertismentLikeQueryReqDTO
     * @return
     */
    public PageInfo<AdvertisementResDTO> list(AdvertismentLikeQueryReqDTO advertismentLikeQueryReqDTO);

    /**
     * 修改广告信息
     * @param updateAdvertisementReqDTO
     * @return
     */
    public int update(UpdateAdvertisementReqDTO updateAdvertisementReqDTO, MultipartFile file);

    /**
     * 更新广告状态
     * @param advertisementStatusReqDTO
     * @return
     */
    public int updateStatus(UpdateAdvertisementStatusReqDTO advertisementStatusReqDTO);

    /**
     * 获取广告详情
     * @param id
     * @return
     */
    public AdvertisementResDTO detail(Long id);
}
