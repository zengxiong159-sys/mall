package com.qdbank.mall.paramsconfig.impl;

import cn.hutool.core.collection.CollUtil;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.paramsconfig.ParamsConfigDOMapper;
import com.qdbank.mall.model.paramsconfig.ParamsConfigDO;
import com.qdbank.mall.model.paramsconfig.ParamsConfigDOExample;
import com.qdbank.mall.paramsconfig.ParamsConfigCacheService;
import com.qdbank.mall.paramsconfig.ParamsConfigService;
import com.qdbank.mall.request.paramsconfig.ParamsConfigReqDTO;
import com.qdbank.mall.request.paramsconfig.UpdateParamsConfigReqDTO;
import com.qdbank.mall.response.paramsconfig.ParamsConfigResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ParamsConfigServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/1 13:46
 * @Version 1.0
 **/
@Service
public class ParamsConfigServiceImpl extends BaseServiceImpl implements ParamsConfigService {
    @Autowired
    private ParamsConfigDOMapper paramsConfigDOMapper;
    @Autowired
    private ParamsConfigCacheService paramsConfigCacheService;
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @Override
    public int create(ParamsConfigReqDTO paramsConfigReqDTO) {
        ParamsConfigDO paramsConfigDO = new ParamsConfigDO();
        BeanUtils.copyProperties(paramsConfigReqDTO,paramsConfigDO);
        paramsConfigDO.setId(super.generateId());
        umsAdminService.injectUserValue(paramsConfigDO);
        ParamsConfigDOExample paramsConfigDOExample = new ParamsConfigDOExample();
        paramsConfigDOExample.createCriteria().andParamNameEqualTo(paramsConfigReqDTO.getParamName());
        List<ParamsConfigDO> configDOList = paramsConfigDOMapper.selectByExample(paramsConfigDOExample);
        if(CollUtil.isNotEmpty(configDOList)) throw new ApiException(ResultCode.PARAM_NAME_EXIST);
        return paramsConfigDOMapper.insert(paramsConfigDO);
    }

    @Override
    public List<ParamsConfigResDTO> list() {
        List<ParamsConfigResDTO> paramsConfigResDTOList = new ArrayList<>();
        List<ParamsConfigDO> configDOList = paramsConfigDOMapper.selectByExample(null);
        for(ParamsConfigDO paramsConfigDO : configDOList){
            ParamsConfigResDTO paramsConfigResDTO = new ParamsConfigResDTO();
            BeanUtils.copyProperties(paramsConfigDO,paramsConfigResDTO);
            paramsConfigResDTOList.add(paramsConfigResDTO);
        }
        return paramsConfigResDTOList;
    }


    @Override
    public List<ParamsConfigResDTO> qryByType(long type) {
        List<ParamsConfigResDTO> paramsConfigResDTOList = new ArrayList<>();
        ParamsConfigDOExample example = new ParamsConfigDOExample();
        example.setOrderByClause("id");
        ParamsConfigDOExample.Criteria criteria = example.createCriteria();
        criteria.andParamTypeEqualTo(type);
        List<ParamsConfigDO> configDOList = paramsConfigDOMapper.selectByExample(example);
        for(ParamsConfigDO paramsConfigDO : configDOList){
            ParamsConfigResDTO paramsConfigResDTO = new ParamsConfigResDTO();
            BeanUtils.copyProperties(paramsConfigDO,paramsConfigResDTO);
            paramsConfigResDTOList.add(paramsConfigResDTO);
        }
        return paramsConfigResDTOList;
    }

    @Override
    public ParamsConfigResDTO detail(Long id) {
        ParamsConfigDO paramsConfigDO = null;
        paramsConfigDO = paramsConfigCacheService.getParamsConfigById(id);
        if(paramsConfigDO == null){
            paramsConfigDO = paramsConfigDOMapper.selectByPrimaryKey(id);
            if(paramsConfigDO != null){
                paramsConfigCacheService.setParamsConfigDO(paramsConfigDO);
            }
        }
        ParamsConfigResDTO paramsConfigResDTO = new ParamsConfigResDTO();
        BeanUtils.copyProperties(paramsConfigDO,paramsConfigResDTO);
        return paramsConfigResDTO;
    }

    @Override
    public int update(UpdateParamsConfigReqDTO paramsConfigReqDTO) {
        ParamsConfigDO paramsConfigDO = new ParamsConfigDO();
        BeanUtils.copyProperties(paramsConfigReqDTO,paramsConfigDO);
        int count = paramsConfigDOMapper.updateByPrimaryKeySelective(paramsConfigDO);
        if(count > 0){
            paramsConfigCacheService.delParamsConfig(paramsConfigReqDTO.getId());
            if(StringUtils.isNotBlank(paramsConfigReqDTO.getParamName())){
                paramsConfigCacheService.deleteParamsConfigByName(paramsConfigReqDTO.getParamName());
            }
        }
        return count;
    }

    @Override
    public int delete(Long id) {
        paramsConfigCacheService.delParamsConfig(id);
        return paramsConfigDOMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ParamsConfigResDTO queryByParamName(String paramName) {
        ParamsConfigResDTO paramsConfigResDTO = new ParamsConfigResDTO();
        ParamsConfigDOExample paramsConfigDOExample = new ParamsConfigDOExample();
        paramsConfigDOExample.createCriteria().andParamNameEqualTo(paramName);
        List<ParamsConfigDO> configDOList = paramsConfigDOMapper.selectByExample(paramsConfigDOExample);
        if(CollUtil.isEmpty(configDOList)) throw new ApiException(ResultCode.PARAM_NAME_NOT_EXIST);
        BeanUtils.copyProperties(configDOList.get(0),paramsConfigResDTO);
        return paramsConfigResDTO;
    }
}
