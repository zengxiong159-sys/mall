package com.qdbank.mall.center.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.center.CenterService;
import com.qdbank.mall.mapper.center.CenterDOMapper;
import com.qdbank.mall.model.center.CenterDO;
import com.qdbank.mall.model.center.CenterDOExample;
import com.qdbank.mall.request.center.CenterLikeReqDTO;
import com.qdbank.mall.response.center.CenterResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CenterServiceImpl extends BaseServiceImpl implements CenterService {
    @Autowired
    private CenterDOMapper centerDOMapper;
    @Override
    public PageInfo<CenterResDTO> list(CenterLikeReqDTO centerLikeReqDTO) {
        PageHelper.startPage(centerLikeReqDTO.getPageNum(), centerLikeReqDTO.getPageSize());
        CenterDOExample centerDOExample = new CenterDOExample();
        centerDOExample.setOrderByClause(" create_time desc");
        CenterDOExample.Criteria criteria = centerDOExample.createCriteria();
        criteria.andStatusEqualTo("1").andConfigyTypeEqualTo(centerLikeReqDTO.getConfigyType());
        if(centerLikeReqDTO.getConfigyType() == 1){
            criteria.andStartTimeLessThan(new Date()).andEndTimeGreaterThan(new Date());
        }
        if(StringUtils.isNotBlank(centerLikeReqDTO.getTitle())){
            criteria.andTitleLike("%"+centerLikeReqDTO.getTitle()+"%");
        }
        List<CenterDO> centerDOList = centerDOMapper.selectByExample(centerDOExample);
        List<CenterResDTO> centerResDTOList = new ArrayList<>();
        for(CenterDO centerDO : centerDOList){
            CenterResDTO centerResDTO = new CenterResDTO();
            BeanUtils.copyProperties(centerDO,centerResDTO);
            centerResDTOList.add(centerResDTO);
        }
        return super.getPageInfo(centerDOList,centerResDTOList);
    }
}
