package com.qdbank.mall.center;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.request.center.CenterLikeReqDTO;
import com.qdbank.mall.request.center.CenterReqDTO;
import com.qdbank.mall.request.center.UpdateStatusCenterReqDTO;
import com.qdbank.mall.response.center.CenterResDTO;

import java.util.List;

public interface CenterServcie {
    /**
     * 新增配置
     * @param centerReqDTO
     * @return
     */
    public int create(CenterReqDTO centerReqDTO);

    /**
     * 修改状态
     * @param updateStatusCenterReqDTO
     * @return
     */
    public int updateStatus(UpdateStatusCenterReqDTO updateStatusCenterReqDTO);

    /**
     * 修改配置
     * @param centerReqDTO
     * @return
     */
    public int update(CenterReqDTO centerReqDTO);

    /**
     * 列表查询
     * @param centerLikeReqDTO
     * @return
     */
    public PageInfo<CenterResDTO> list(CenterLikeReqDTO centerLikeReqDTO);

    /**
     * 获取详情
     * @param id
     * @return
     */
    public CenterResDTO detail(Long id);
}
