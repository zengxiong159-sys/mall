package com.qdbank.mall.address.impl;

import com.qdbank.mall.address.AddressService;
import com.qdbank.mall.address.mapper.AddressMapper;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.bo.UserDetails;
import com.qdbank.mall.dao.address.AddressDao;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.model.address.AddressDO;
import com.qdbank.mall.model.usercoupon.UserCouponDetailDO;
import com.qdbank.mall.request.address.AddressReqDTO;
import com.qdbank.mall.request.address.SetDefaultAddressReqDTO;
import com.qdbank.mall.request.address.UpdateAddressReqDTO;
import com.qdbank.mall.request.coupon.UserCouponReqDTO;
import com.qdbank.mall.response.address.AddressResDTO;
import com.qdbank.mall.response.coupon.UserCouponResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.service.impl.SnowFlakeService;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName AddressServiceImpl
 * @Description TODO
 * @Author hongjh
 * @Date 2021/3/17 19:41
 * @Version 1.0
 **/
@Service
@Slf4j
public class AddressServiceImpl extends BaseServiceImpl implements AddressService {

    @Autowired
    AddressDao addressDao;

    @Autowired
    private AddressMapper addressMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddressDO createAddress(AddressReqDTO req) {
        UserDetails userDetails = UserContextHolder.getUserDetails();

        AddressDO dto = addressMapper.dTOToPo(req);
        dto.setCustNo(Long.parseLong(userDetails.getCustNo()));
        dto.setCustName(userDetails.getCustName());
        dto.setId(super.generateId());
        dto.setCreateTime(new Date());
        dto.setUpdateTime(new Date());

        if(req.getDefaultFlag()==1L){
            log.info("同时设置为默认地址[{}]",userDetails.getCustNo());
            addressDao.setAddressDefaultFlag(userDetails.getCustNo(),true,null);
        }
        int count = addressDao.createAddress(dto);
        return dto;
    }


    @Override
    public List<AddressResDTO> qryUserAddresss(String custNo) {
        if(StringUtils.isEmpty(custNo)) return new ArrayList<AddressResDTO>();
        List<AddressDO> result=addressDao.qryUserAddressDo(custNo,null);
        List<AddressResDTO> qresult=addressMapper.poToDTOList(result);
        return qresult;
    }

    @Override
    public int updateUserAdress(String custNo, UpdateAddressReqDTO req) {
        List<AddressDO> result=addressDao.qryUserAddressDo(custNo,req.getId()+"");
        if(CollectionUtils.isEmpty(result)){
            throw new ApiException(ResultCode.ADDRESS_NOT_FOUND_ERROR);
        }
        AddressDO dto = addressMapper.updateDtoToPo(req);
        dto.setCustNo(Long.parseLong(custNo));
        if(req.getDefaultFlag()==1L){
            log.info("同时设置为默认地址[{}]",custNo);
            addressDao.setAddressDefaultFlag(custNo,true,null);
        }
        dto.setUpdateTime(new Date());
        log.info("执行更新操作[{}]--[{}]",new Object[]{custNo, JsonUtil.toJSONString(dto) });
        return addressDao.updateAddress(dto);
    }

    @Override
    public int deleteAddressById(String custNo, Long addressId) {
        List<AddressDO> result=addressDao.qryUserAddressDo(custNo,addressId+"");
        if(CollectionUtils.isEmpty(result)){
            throw new ApiException(ResultCode.ADDRESS_NOT_FOUND_ERROR);
        }

        log.info("执行更新操作[{}]--[{}]",new Object[]{custNo, addressId });
        return addressDao.deleteAddressById(addressId);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setDefaultAddress(String custNo,SetDefaultAddressReqDTO setDefaultAddressReqDTO) {
        Long oldId = setDefaultAddressReqDTO.getOldId();
        Long newId =setDefaultAddressReqDTO.getNewId();

        if(newId.equals(oldId)){
            throw new ApiException(ResultCode.ADDRESS_OLD_NEW_SSAME_ERROR);
        }

        //当前用户先全部处理为非默认
        log.info("执行原数据默认标识清0");
        addressDao.setAddressDefaultFlag(custNo,true,null);
        log.info("执行默认地址更新[{}]",newId);
        addressDao.setAddressDefaultFlag(custNo,false,newId);
    }




}
