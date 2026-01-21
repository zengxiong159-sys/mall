package com.qdbank.mall.prefecture;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.request.prefecture.PrefectureLikeQueryReqDTO;
import com.qdbank.mall.request.prefecture.PrefectureReqDTO;
import com.qdbank.mall.request.prefecture.UpdatePrefectureReqDTO;
import com.qdbank.mall.request.prefecture.UpdatePrefectureStatusReqDTO;
import com.qdbank.mall.response.prefecture.PrefectureDetailResDTO;
import com.qdbank.mall.response.prefecture.PrefectureResDTO;
import org.springframework.transaction.annotation.Transactional;

public interface PrefectureService {
    /**
     * 新建专区
     * @param prefectureReqDTO
     * @return
     */
    @Transactional
    public int create(PrefectureReqDTO prefectureReqDTO);

    /**
     * 获取专区列表
     * @param prefectureLikeQueryReqDTO
     * @return
     */
    public PageInfo<PrefectureResDTO> list(PrefectureLikeQueryReqDTO prefectureLikeQueryReqDTO);

    /**
     * 修改专区信息
     * @param updatePrefectureReqDTO
     * @return
     */
    @Transactional
    public int update(UpdatePrefectureReqDTO updatePrefectureReqDTO);

    /**
     * 修改专区状态
     * @param updatePrefectureStatusReqDTO
     * @return
     */
    public int updateStatus(UpdatePrefectureStatusReqDTO updatePrefectureStatusReqDTO);

    /**
     * 获取专区详情
     * @param id
     * @return
     */
    public PrefectureDetailResDTO getDetail(Long id);

    /**
     * 获取活动专区商品列表
     * @param id    专区id
     * @return  专区商品列表
     */
    PrefectureDetailResDTO activityProductList(Long id);

}
