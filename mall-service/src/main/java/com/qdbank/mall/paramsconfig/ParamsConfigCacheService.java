package com.qdbank.mall.paramsconfig;

import com.qdbank.mall.model.paramsconfig.ParamsConfigDO;

public interface ParamsConfigCacheService {
    /**
     * 参数配置信息入缓存
     * @param paramsConfigDO
     */
    public void setParamsConfigDO(ParamsConfigDO paramsConfigDO);

    /**
     * 根据参数配置编号清除缓存
     * @param id
     */
    public void delParamsConfig(Long id);

    /**
     * 根据ID从缓存中获取商户信息
     * @param id
     * @return
     */
    public ParamsConfigDO getParamsConfigById(Long id);

    /**
     * 参数配置信息根据参数名称加入缓存
     * @param paramsConfigDO
     */
    public void setParamsConfigByName(ParamsConfigDO paramsConfigDO);

    /**
     * 根据参数名称获取配置
     * @param paramName
     * @return
     */
    public ParamsConfigDO getParamsConfigByName(String paramName);

    /**
     * 根据参数名称清除缓存
     * @param paramName
     */
    public void deleteParamsConfigByName(String paramName);

}
