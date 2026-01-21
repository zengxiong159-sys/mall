package com.qdbank.mall.paramsconfig;

import com.qdbank.mall.request.paramsconfig.ParamsConfigReqDTO;
import com.qdbank.mall.request.paramsconfig.UpdateParamsConfigReqDTO;
import com.qdbank.mall.response.paramsconfig.ParamsConfigResDTO;

import java.util.List;

public interface ParamsConfigService {
    /**
     * 创建参数配置
     * @param paramsConfigReqDTO
     * @return
     */
    public int create(ParamsConfigReqDTO paramsConfigReqDTO);

    /**
     * 参数列表查询
     * @return
     */
    public List<ParamsConfigResDTO> list();

    List<ParamsConfigResDTO> qryByType(long type);

    /**
     * 参数配置详情
     * @param id
     * @return
     */
    public ParamsConfigResDTO detail(Long id);

    /**
     * 修改参数配置信息
     * @param updateParamsConfigReqDTO
     * @return
     */
    public int update(UpdateParamsConfigReqDTO updateParamsConfigReqDTO);

    /**
     * 删除参数配置
     * @param id
     * @return
     */
    public int delete(Long id);

    /**
     * 根据参数名称获取参数配置信息
     * @param paramName
     * @return
     */
    public ParamsConfigResDTO queryByParamName(String paramName);


}
