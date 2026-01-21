package com.qdbank.mall.advertisment.impl;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.advertisment.AdvertismentService;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.api.StatusEnum;
import com.qdbank.mall.coupon.AsyncInsertCouponService;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.advertisement.AdvertisementDOMapper;
import com.qdbank.mall.model.advertisement.AdvertisementDO;
import com.qdbank.mall.model.advertisement.AdvertisementDOExample;
import com.qdbank.mall.replace.ReplaceService;
import com.qdbank.mall.request.advertisement.AdvertismentLikeQueryReqDTO;
import com.qdbank.mall.request.advertisement.AdvertismentReqDTO;
import com.qdbank.mall.request.advertisement.UpdateAdvertisementReqDTO;
import com.qdbank.mall.request.advertisement.UpdateAdvertisementStatusReqDTO;
import com.qdbank.mall.response.advertisement.AdvertisementResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.StringUtil;
import com.qdbank.mall.util.TimeUtil;
import com.qdbank.mall.util.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AdvertismentServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/27 9:47
 * @Version 1.0
 **/
@Service
@Slf4j
public class AdvertismentServiceImpl extends BaseServiceImpl implements AdvertismentService {
    @Autowired
    private AdvertisementDOMapper advertisementDOMapper;
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private AsyncInsertCouponService asyncInsertCouponService;
    @Autowired
    private ReplaceService replaceService;
    @Override
    public int create(AdvertismentReqDTO advertismentReqDTO) {

            //this.checkAdvertisementNameExist(advertismentReqDTO.getAdvertiseName());
            String[] filePath = super.uploadFile( advertismentReqDTO.getFile(), true);
            AdvertisementDO advertisementDO = new AdvertisementDO();
            BeanUtils.copyProperties(advertismentReqDTO,advertisementDO);
            advertisementDO.setId(super.generateId());
            umsAdminService.injectUserValue(advertisementDO);
        try {
            advertisementDO.setGroupId(filePath[0]);
            advertisementDO.setPicUrl(filePath[2]);
            advertisementDO.setStatus(StatusEnum.ADVERTISMENT_CLOSE_STATUS.getCode());
            return advertisementDOMapper.insert(advertisementDO);
        }catch (ApiException apiException){
            deleteFile(filePath[0],filePath[2]);
            throw apiException;
        }catch (Exception e){
            log.error("新建广告异常:"+e);
            deleteFile(filePath[0],filePath[2]);
            throw new ApiException(ResultCode.BUILD_ADVERTISE_EXCEPTION);
        }
    }

    @Override
    public PageInfo<AdvertisementResDTO> list(AdvertismentLikeQueryReqDTO advertismentLikeQueryReqDTO) {
        PageHelper.startPage(advertismentLikeQueryReqDTO.getPageNum(), advertismentLikeQueryReqDTO.getPageSize());
        AdvertisementDOExample advertisementDOExample = new AdvertisementDOExample();
        advertisementDOExample.setOrderByClause("create_time desc");
        AdvertisementDOExample.Criteria criteria = advertisementDOExample.createCriteria();
        if(StringUtils.isNotBlank(advertismentLikeQueryReqDTO.getAdvertiseName())){
            criteria.andAdvertiseNameLike("%"+advertismentLikeQueryReqDTO.getAdvertiseName()+"%");
        }
        if(advertismentLikeQueryReqDTO.getModuleId() != null){
            criteria.andModuleIdEqualTo(advertismentLikeQueryReqDTO.getModuleId());
        }
        if(advertismentLikeQueryReqDTO.getStatus() != null){
            criteria.andStatusEqualTo(advertismentLikeQueryReqDTO.getStatus());
        }
        if(advertismentLikeQueryReqDTO.getStartTime() != null){
            criteria.andStartTimeGreaterThanOrEqualTo(TimeUtil.dateZeroChange(advertismentLikeQueryReqDTO.getStartTime()));
        }
        if(advertismentLikeQueryReqDTO.getEndTime() != null){
            criteria.andStartTimeLessThanOrEqualTo(TimeUtil.dateEndChange(advertismentLikeQueryReqDTO.getEndTime()));
        }
        List<AdvertisementDO> advertisementDOS = advertisementDOMapper.selectByExample(advertisementDOExample);
        List<AdvertisementResDTO> advertisementResDTOList = new ArrayList<>();
        for(AdvertisementDO advertisementDO : advertisementDOS){
            AdvertisementResDTO advertisementResDTO = new AdvertisementResDTO();
            BeanUtils.copyProperties(advertisementDO,advertisementResDTO);
            advertisementResDTO.setPicUrl(replaceService.replaceUrl(advertisementResDTO.getPicUrl()));
            advertisementResDTOList.add(advertisementResDTO);
        }
        return super.getPageInfo(advertisementDOS,advertisementResDTOList);
    }

    @Override
    public int update(UpdateAdvertisementReqDTO updateAdvertisementReqDTO, MultipartFile file) {
        String jumpUrl = updateAdvertisementReqDTO.getJumpUrl();
        if(StringUtil.hasText(jumpUrl)) {
            String decodeJumpUrl = UrlUtil.getUrlDecoderString(jumpUrl);
            updateAdvertisementReqDTO.setJumpUrl(decodeJumpUrl);
        }

        String[] filePath = null;
        AdvertisementDO advertisementDO = new AdvertisementDO();
        if(updateAdvertisementReqDTO.getPicUpdateFlag() == 1 && file != null){//图片需要更新
            try {
                filePath = super.uploadFile(file, true);
                advertisementDO.setPicUrl(filePath[2]);
            }catch (ApiException apiException){
                throw  apiException;
            }catch (Exception e){
                log.error("update advertise error:{}"+e);
                throw new ApiException(ResultCode.BUILD_ADVERTISE_EXCEPTION);
            }
            AdvertisementDO oldData = advertisementDOMapper.selectByPrimaryKey(updateAdvertisementReqDTO.getId());
            if(oldData != null){
                asyncInsertCouponService.deleteFile(oldData.getGroupId(),oldData.getPicUrl());
            }
        }else{
            advertisementDO.setPicUrl(null);
        }
        BeanUtils.copyProperties(updateAdvertisementReqDTO,advertisementDO);
        umsAdminService.injectUpdateUserValue(advertisementDO);
        return advertisementDOMapper.updateByPrimaryKeySelective(advertisementDO);
    }

    @Override
    public int updateStatus(UpdateAdvertisementStatusReqDTO advertisementStatusReqDTO) {
        AdvertisementDO advertisementDO = new AdvertisementDO();
        BeanUtils.copyProperties(advertisementStatusReqDTO,advertisementDO);
        umsAdminService.injectUpdateUserValue(advertisementDO);
        return advertisementDOMapper.updateByPrimaryKeySelective(advertisementDO);
    }

    @Override
    public AdvertisementResDTO detail(Long id) {
        AdvertisementDO advertisementDO = advertisementDOMapper.selectByPrimaryKey(id);
        AdvertisementResDTO advertisementResDTO = new AdvertisementResDTO();
        BeanUtils.copyProperties(advertisementDO,advertisementResDTO);
        return advertisementResDTO;
    }

    /**
     * 校验广告名称是否存在
     * @param advertisementName
     */
    public void checkAdvertisementNameExist(String advertisementName){
        AdvertisementDOExample advertisementDOExample = new AdvertisementDOExample();
        advertisementDOExample.createCriteria().andAdvertiseNameEqualTo(advertisementName);
        List<AdvertisementDO> list = advertisementDOMapper.selectByExample(advertisementDOExample);
        if(CollectionUtil.isNotEmpty(list)) throw new ApiException(ResultCode.ADVERTISE_NAME_EXIST);
    }
}
