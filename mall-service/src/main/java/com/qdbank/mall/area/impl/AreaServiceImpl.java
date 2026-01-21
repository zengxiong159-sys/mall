package com.qdbank.mall.area.impl;

import com.qdbank.mall.area.AreaService;
import com.qdbank.mall.mapper.area.AreaDOMapper;
import com.qdbank.mall.model.area.AreaDO;
import com.qdbank.mall.response.area.AreaResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.specificationattribute.SpecificationattributeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * AreaServiceImpl
 *
 * @author shaoshihang
 * @date 2021/3/26 10:56
 * @since 1.0.0
 */
@Service
@Slf4j
public class AreaServiceImpl extends BaseServiceImpl implements AreaService {
    @Resource
    private AreaDOMapper areaDOMapper;

    @Override
    public List<AreaResDTO> selectProvince() {
        List<AreaDO>  areaDOList = areaDOMapper.selectProvince();
        List<AreaResDTO> areaResDTOS = new ArrayList<>();
        areaDOList.forEach(item->{
            AreaResDTO areaResDTO = new AreaResDTO();
            BeanUtils.copyProperties(item,areaResDTO);
            areaResDTOS.add(areaResDTO);
        });
        return areaResDTOS;
    }
}
