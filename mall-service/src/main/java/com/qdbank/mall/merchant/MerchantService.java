package com.qdbank.mall.merchant;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.request.merchant.MerchantLikeQueryReqDTO;
import com.qdbank.mall.request.merchant.MerchantReqDTO;
import com.qdbank.mall.request.merchant.UpdateMerchantReqDTO;
import com.qdbank.mall.request.merchant.UpdateMerchantStatusReqDTO;
import com.qdbank.mall.response.merchant.MerchantResDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MerchantService {
    /**
     * 获取商户列表
     * @return
     */
    public PageInfo<MerchantResDTO> list(MerchantLikeQueryReqDTO merchantLikeQueryReqDTO);

    /**
     * 创建部门信息
     * @param merchantReqDTO
     * @return
     */
    @Transactional
    public int create(MerchantReqDTO merchantReqDTO);

    /**
     * 删除部门信息
     * @param id
     * @return
     */
    public int delete(Long id);

    /**
     * 修改部门信息
     * @param merchantReqDTO
     * @return
     */
    public int update(UpdateMerchantReqDTO merchantReqDTO);

    /**
     * 获取商户详情
     * @param id
     * @return
     */
    public MerchantResDTO getItem(Long id);

    /**
     * 获取商户编号
     * @param email
     * @return
     */
    public MerchantResDTO selectByEmail(String email);

    public int updateStatus(UpdateMerchantStatusReqDTO updateMerchantStatusReqDTO);
}
