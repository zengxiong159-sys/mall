package com.qdbank.mall.dao.address;

import com.qdbank.mall.mapper.area.AreaDOMapper;
import com.qdbank.mall.mapper.areafreighttemplate.AreafreighttemplateDOMapper;
import com.qdbank.mall.mapper.freighttemplate.FreighttemplateDOMapper;
import com.qdbank.mall.model.area.AreaDO;
import com.qdbank.mall.model.area.AreaDOExample;
import com.qdbank.mall.model.areafreighttemplate.AreaFreightTemplateDetailDO;
import com.qdbank.mall.model.freighttemplate.FreighttemplateListDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hongjh
 */
@Repository
@Slf4j
public class FreightTemplateDao {

    @Resource
    private FreighttemplateDOMapper freighttemplateDOMapper;

    @Resource
    private AreafreighttemplateDOMapper areafreighttemplateDOMapper;


    /**
     * 查询模板信息
     * @param templateId
     * @return
     */
    public FreighttemplateListDO qryTemlates(Long templateId){
        AreaFreightTemplateDetailDO areaFreightTemplateDetail = new AreaFreightTemplateDetailDO();
        areaFreightTemplateDetail.setFreightTemplateId(templateId);
       return freighttemplateDOMapper.selectByIdAndMerchantNo(areaFreightTemplateDetail);
    }







}