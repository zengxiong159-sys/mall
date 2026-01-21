package com.qdbank.mall.freighttemplate;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.request.freighttemplate.*;
import com.qdbank.mall.response.freighttemplate.FreightTemplateResDTO;

import java.util.List;

/**
 * FreightTemplateService
 *
 * @author shaoshihang
 * @date 2021/3/4 18:02
 * @since 1.0.0
 */
public interface FreightTemplateService {

    public PageInfo<FreightTemplateResDTO> list(AreaFreightTemplateListReqDTO areaFreightTemplateListReqDTO);

    /**
     * 新建模板
     * @param freightTemplateReqDTO
     * @return
     */
    public int create(FreightTemplateReqDTO freightTemplateReqDTO);

    /**
     * 编辑模板
     * @param updateFreightTemplateReqDTO
     * @return
     */
    public int update(UpdateFreightTemplateReqDTO updateFreightTemplateReqDTO);

    /**
     * 修改模板状态
     * @param updateFreightStatusReqDTO
     * @return
     */
    public int updateStatus(UpdateFreightStatusReqDTO updateFreightStatusReqDTO);

    /**
     * 查询模板详情
     * @return
     */
    public FreightTemplateResDTO detail(Long id);
}
