package com.qdbank.mall.advertisment.impl;

import com.qdbank.mall.advertisment.AdvertismentService;
import com.qdbank.mall.advertisment.mapper.AdvertismentMapper;
import com.qdbank.mall.dao.advertisment.AdvertismentDao;
import com.qdbank.mall.model.advertisement.AdvertisementDO;
import com.qdbank.mall.request.advertisement.AdvertismentLikeQueryReqDTO;
import com.qdbank.mall.response.advertisement.AdvertisementResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName AdvertismentServiceImpl
 * @Description 广告栏位
 * @Author hongjh
 * @Date 2021/2/27 9:47
 * @Version 1.0
 **/
@Service
@Slf4j
public class AdvertismentServiceImpl extends BaseServiceImpl implements AdvertismentService {

    @Autowired
    AdvertismentDao advertismentDao;

    @Autowired
    AdvertismentMapper advertismentMapper;

    @Override
    public List<AdvertisementResDTO> list(AdvertismentLikeQueryReqDTO advertismentLikeQueryReqDTO) {
        List<AdvertisementDO> list =advertismentDao.activeList(advertismentLikeQueryReqDTO.getModuleId(),1L,new Date());
        return advertismentMapper.poToDTOList(list);
    }


    @Override
    public AdvertisementResDTO detail(Long id) {
        AdvertisementDO advertisementDO = advertismentDao.qryAdvertisementById(id);
        return advertismentMapper.dTOToPo(advertisementDO);
    }


}
