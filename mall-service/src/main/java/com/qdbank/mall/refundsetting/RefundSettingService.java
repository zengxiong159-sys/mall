package com.qdbank.mall.refundsetting;

import com.qdbank.mall.request.refundsetting.RefundSettingReqDTO;
import com.qdbank.mall.request.refundsetting.UpdateRefundReasonReqDTO;
import com.qdbank.mall.request.refundsetting.UpdateRefundStatusReqDTO;
import com.qdbank.mall.response.refundsetting.RefundSettingResDTO;

import java.util.List;

public interface RefundSettingService {
    /**
     * 创建退款设置
     * @param refundSettingReqDTO
     * @return
     */
    public int create(RefundSettingReqDTO refundSettingReqDTO);

    /**
     * 退款设置类别
     * @return
     */
    public List<RefundSettingResDTO> list();

    /**
     * 修改退款设置原因
     * @param refundSettingReqDTO
     * @return
     */
    public int update(UpdateRefundReasonReqDTO refundSettingReqDTO);

    /**
     * 修改退款设置状态
     * @param updateRefundStatusReqDTO
     * @return
     */
    public int updateStatus(UpdateRefundStatusReqDTO updateRefundStatusReqDTO);

    /**
     *
     * @param id
     * @return
     */
    public RefundSettingResDTO detail(Long id);

    /**
     * 删除退款设置
     * @param id
     * @return
     */
    public  int delete(Long id);
}
