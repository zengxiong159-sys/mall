package com.qdbank.mall.position.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.qdbank.mall.mapper.position.PositionDOMapper;
import com.qdbank.mall.model.position.PositionDO;
import com.qdbank.mall.model.position.PositionDOExample;
import com.qdbank.mall.position.PositionService;
import com.qdbank.mall.response.position.PositionResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionDOMapper positionDOMapper;
    @Override
    public List<PositionResDTO> list() {
        PositionDOExample positionDOExample = new PositionDOExample();
        positionDOExample.setOrderByClause("position_level asc");
        positionDOExample.createCriteria().andStatusEqualTo(1L);
        List<PositionDO> positionDOS = positionDOMapper.selectByExample(positionDOExample);
        if(CollectionUtil.isNotEmpty(positionDOS)){
            List<PositionResDTO> positionResDTOS = new ArrayList<>();
            for(PositionDO positionDO : positionDOS){
                PositionResDTO positionResDTO = new PositionResDTO();
                BeanUtils.copyProperties(positionDO,positionResDTO);
                positionResDTO.setCreatedBy("");
                positionResDTO.setUpdatedBy("");
                positionResDTOS.add(positionResDTO);
            }
            return positionResDTOS;
        }
        return null;
    }
}
