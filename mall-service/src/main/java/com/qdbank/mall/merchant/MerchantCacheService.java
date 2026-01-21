package com.qdbank.mall.merchant;

import com.qdbank.mall.model.merchant.MerchantDO;

public interface MerchantCacheService {
    /**
     * 商户信息注入缓存
     * @param merchant
     */
    public void setMerchant(MerchantDO merchant);

    /**
     * 更加商户编号清除缓存
     * @param id
     */
    public void delMerchant(Long id);

    /**
     * 根据ID从缓存中获取商户信息
     * @param id
     * @return
     */
    public MerchantDO getMerchantById(Long id);
}
