package com.qdbank.mall.position;

import com.qdbank.mall.request.position.PositionLikeReqDTO;
import com.qdbank.mall.request.position.PositionReqDTO;
import com.qdbank.mall.request.position.UpdatePositionReqDTO;
import com.qdbank.mall.request.position.UpdateStatusPositionReqDTO;
import com.qdbank.mall.response.position.PositionResDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PositionService {
    /**
     *
     * @param positionReqDTO
     * @return
     */
    int create(PositionReqDTO positionReqDTO);

    /**
     * 获取金刚位列表
     * @param positionLikeReqDTO
     * @return
     */
    List<PositionResDTO> list(PositionLikeReqDTO positionLikeReqDTO);

    /**
     * 边界金刚位
     * @param updatePositionReqDTO
     * @return
     */
    int update(UpdatePositionReqDTO updatePositionReqDTO, MultipartFile file);

    /**
     * 修改金刚位状态
     * @param updateStatusPositionReqDTO
     * @return
     */
    int updateStatus(UpdateStatusPositionReqDTO updateStatusPositionReqDTO);

    /**
     * 查询金刚位详情
     * @param id
     * @return
     */
    PositionResDTO detail(Long id);
}
