package com.qdbank.mall.center.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.qdbank.mall.center.CenterServcie;
import com.qdbank.mall.mapper.center.CenterDOMapper;
import com.qdbank.mall.model.center.CenterDO;
import com.qdbank.mall.model.center.CenterDOExample;
import com.qdbank.mall.replace.ReplaceService;
import com.qdbank.mall.request.center.CenterLikeReqDTO;
import com.qdbank.mall.request.center.CenterReqDTO;
import com.qdbank.mall.request.center.UpdateStatusCenterReqDTO;
import com.qdbank.mall.response.center.CenterResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CenterServcieImpl extends BaseServiceImpl implements CenterServcie {
    @Autowired
    private CenterDOMapper centerDOMapper;
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private ReplaceService replaceService;
    @Override
    public int create(CenterReqDTO centerReqDTO) {
        CenterDO centerDO = new CenterDO();
        BeanUtils.copyProperties(centerReqDTO,centerDO);
        centerDO.setId(super.generateId());
        centerDO.setStatus("1");
        umsAdminService.injectUserValue(centerDO);
        if(StringUtil.isNotEmpty(centerDO.getContentDetail())){
            centerDO.setContentDetail(replaceService.replaceOmsUrl(centerReqDTO.getContentDetail()));
        }
        return centerDOMapper.insert(centerDO);
    }

    @Override
    public int updateStatus(UpdateStatusCenterReqDTO updateStatusCenterReqDTO) {
        CenterDO centerDO = new CenterDO();
        centerDO.setStatus(updateStatusCenterReqDTO.getStatus());
        centerDO.setId(updateStatusCenterReqDTO.getId());
        return centerDOMapper.updateByPrimaryKeySelective(centerDO);
    }

    @Override
    public int update(CenterReqDTO centerReqDTO) {
        CenterDO centerDO = new CenterDO();
        BeanUtils.copyProperties(centerReqDTO,centerDO);
        umsAdminService.injectUpdateUserValue(centerDO);
        if(StringUtils.isNotBlank(centerReqDTO.getContentDetail())){
            centerDO.setContentDetail(replaceService.replaceOmsUrl(centerReqDTO.getContentDetail()));
        }
        return centerDOMapper.updateByPrimaryKeySelective(centerDO);
    }

    @Override
    public PageInfo<CenterResDTO> list(CenterLikeReqDTO centerLikeReqDTO) {
        PageHelper.startPage(centerLikeReqDTO.getPageNum(), centerLikeReqDTO.getPageSize());
        CenterDOExample centerDOExample = new CenterDOExample();
        centerDOExample.setOrderByClause("create_time desc");
       CenterDOExample.Criteria criteria = centerDOExample.createCriteria();
       if(centerLikeReqDTO.getConfigyType() != null){
           criteria.andConfigyTypeEqualTo(centerLikeReqDTO.getConfigyType());
       }
       if(StringUtils.isNotBlank(centerLikeReqDTO.getTitle())){
           criteria.andTitleLike(centerLikeReqDTO.getTitle());
       }
       if(StringUtils.isNotBlank(centerLikeReqDTO.getStatus())){
           criteria.andStatusEqualTo(centerLikeReqDTO.getStatus());
       }
        if(centerLikeReqDTO.getStartTime() != null){
            criteria.andCreateTimeBetween(TimeUtil.dateZeroChange(centerLikeReqDTO.getStartTime()),TimeUtil.dateEndChange(centerLikeReqDTO.getEndTime()));
        }
        List<CenterDO> centerDOList = centerDOMapper.selectByExample(centerDOExample);
        List<CenterResDTO> centerResDTOList = new ArrayList<>();
        for(CenterDO centerDO : centerDOList){
            CenterResDTO centerResDTO = new CenterResDTO();
            BeanUtils.copyProperties(centerDO,centerResDTO);
            centerResDTO.setContentDetail(replaceService.replaceUrl(centerResDTO.getContentDetail()));
            centerResDTOList.add(centerResDTO);
        }
        return super.getPageInfo(centerDOList,centerResDTOList);
    }

    @Override
    public CenterResDTO detail(Long id) {
        CenterDO centerDO = centerDOMapper.selectByPrimaryKey(id);
        if(centerDO == null) return null;
        CenterResDTO centerResDTO = new CenterResDTO();
        BeanUtils.copyProperties(centerDO,centerResDTO);
        return centerResDTO;
    }
}
