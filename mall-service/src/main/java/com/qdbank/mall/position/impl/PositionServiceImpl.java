package com.qdbank.mall.position.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.coupon.AsyncInsertCouponService;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.position.PositionDOMapper;
import com.qdbank.mall.model.position.PositionDO;
import com.qdbank.mall.model.position.PositionDOExample;
import com.qdbank.mall.position.PositionService;
import com.qdbank.mall.replace.ReplaceService;
import com.qdbank.mall.request.position.PositionLikeReqDTO;
import com.qdbank.mall.request.position.PositionReqDTO;
import com.qdbank.mall.request.position.UpdatePositionReqDTO;
import com.qdbank.mall.request.position.UpdateStatusPositionReqDTO;
import com.qdbank.mall.response.position.PositionResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.StringUtil;
import com.qdbank.mall.util.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PositionServiceImpl extends BaseServiceImpl implements PositionService {
    @Autowired
    private PositionDOMapper positionDOMapper;
    @Autowired
    @Qualifier("umsAdminServiceImpl")
    private UmsAdminService umsAdminService;
    @Autowired
    private AsyncInsertCouponService asyncInsertCouponService;
    @Autowired
    private ReplaceService replaceService;
    @Override
    public int create(PositionReqDTO positionReqDTO) {
        String [] filePath = super.uploadFile(positionReqDTO.getFile(), true);
        PositionDO positionDO = new PositionDO();
        BeanUtils.copyProperties(positionReqDTO,positionDO);
        positionDO.setId(super.generateId());
        positionDO.setStatus(0L);
        positionDO.setGroupId(filePath[0]);
        positionDO.setPicUrl(filePath[2]);
        umsAdminService.injectUserValue(positionDO);
        return positionDOMapper.insert(positionDO);
    }

    @Override
    public List<PositionResDTO> list(PositionLikeReqDTO positionLikeReqDTO) {
        PositionDOExample positionDOExample = new PositionDOExample();
        positionDOExample.setOrderByClause("create_time asc");
        PositionDOExample.Criteria criteria = positionDOExample.createCriteria();
        if(StringUtils.isNotBlank(positionLikeReqDTO.getPositionName())){
            criteria.andPositionNameLike("%"+positionLikeReqDTO.getPositionName() + "%");
        }
        if(positionLikeReqDTO.getStatus() != null){
            criteria.andStatusEqualTo(positionLikeReqDTO.getStatus());
        }
        List<PositionDO> positionDOS = positionDOMapper.selectByExample(positionDOExample);
        if(CollectionUtil.isNotEmpty(positionDOS)){
            List<PositionResDTO> positionResDTOS = new ArrayList<>();
            for(PositionDO positionDO : positionDOS){
                PositionResDTO positionResDTO = new PositionResDTO();
                BeanUtils.copyProperties(positionDO,positionResDTO);
                positionResDTO.setPicUrl(replaceService.replaceUrl(positionDO.getPicUrl()));
                positionResDTOS.add(positionResDTO);
            }
            return positionResDTOS;
        }
        return null;
    }

    @Override
    public int update(UpdatePositionReqDTO updatePositionReqDTO, MultipartFile file) {
        String[] filePath = null;
        PositionDO positionDO = new PositionDO();
        if(updatePositionReqDTO.getPicUpdateFlag() == 1 && file != null){//图片需要更新
            try {
                filePath = super.uploadFile(file, true);
                positionDO.setPicUrl(filePath[2]);
            }catch (ApiException apiException){
                throw  apiException;
            }catch (Exception e){
                log.error("update position error:{}"+e);
                throw new ApiException(ResultCode.BUILD_ACTIVITY_EXCEPTION);
            }
            PositionDO oldPosition = positionDOMapper.selectByPrimaryKey(updatePositionReqDTO.getId());
            if(oldPosition != null){
                //异步删除原始图片
                asyncInsertCouponService.deleteFile(oldPosition.getGroupId(),oldPosition.getPicUrl());
            }
        }else{
            positionDO.setPicUrl(null);
        }
        BeanUtils.copyProperties(updatePositionReqDTO,positionDO);
        if(StringUtils.isNotBlank(positionDO.getPicUrl())){
            positionDO.setPicUrl(replaceService.replaceOmsUrl(positionDO.getPicUrl()));
        }
        umsAdminService.injectUpdateUserValue(positionDO);
        return positionDOMapper.updateByPrimaryKeySelective(positionDO);
    }

    @Override
    public int updateStatus(UpdateStatusPositionReqDTO updateStatusPositionReqDTO) {
        PositionDO positionDO = new PositionDO();
        BeanUtils.copyProperties(updateStatusPositionReqDTO,positionDO);
        umsAdminService.injectUpdateUserValue(positionDO);
        return positionDOMapper.updateByPrimaryKeySelective(positionDO);
    }

    @Override
    public PositionResDTO detail(Long id) {
        PositionDO positionDO = positionDOMapper.selectByPrimaryKey(id);
        if(positionDO != null){
            PositionResDTO positionResDTO = new PositionResDTO();
            BeanUtils.copyProperties(positionDO,positionResDTO);
            return positionResDTO;
        }
        return null;
    }
}
