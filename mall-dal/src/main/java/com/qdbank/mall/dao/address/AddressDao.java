package com.qdbank.mall.dao.address;

import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.address.AddressDOMapper;
import com.qdbank.mall.model.address.AddressDO;
import com.qdbank.mall.model.address.AddressDOExample;
import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.usercoupon.UserCouponDetailDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author hongjh
 */
@Repository
@Slf4j
public class AddressDao {

    @Autowired
    private AddressDOMapper addressDOMapper;


    public int createAddress(AddressDO dto){
        return addressDOMapper.insert(dto);
    }

    public int updateAddress(AddressDO dto){
        AddressDOExample example = new AddressDOExample();
        AddressDOExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(dto.getId());
        return addressDOMapper.updateByExampleSelective(dto,example);
    }

    public int setAddressDefaultFlag(String custNo, boolean setNullFlag, Long addressId){
        AddressDO dto  = new AddressDO();
        AddressDOExample example = new AddressDOExample();
        AddressDOExample.Criteria criteria = example.createCriteria();

        //客户号
        criteria.andCustNoEqualTo(Long.parseLong(custNo));

        dto.setUpdateTime(new Date());
        //置为空
        if(setNullFlag){
            dto.setDefaultFlag(0L);
            //原为默认的标识
            criteria.andDefaultFlagEqualTo(1L);
        }else{
            dto.setDefaultFlag(1L);
            //地址号
            if(addressId==null){
                throw new ApiException(ResultCode.ADDRESS_NOT_FOUND_ERROR);
            }
            criteria.andIdEqualTo(addressId);
        }
        return addressDOMapper.updateByExampleSelective(dto,example);
    }

    public int deleteAddressById(Long id){
        return addressDOMapper.deleteByPrimaryKey(id);
    }


    public List<AddressDO> qryUserAddressDo(String custNo,String addressId){
        AddressDOExample example =  new AddressDOExample();
        example.setOrderByClause("DEFAULT_FLAG desc,create_time desc");
        AddressDOExample.Criteria criteria =example.createCriteria();
        if(StringUtils.hasText(custNo)){
            criteria.andCustNoEqualTo(Long.parseLong(custNo));
        }
        if(StringUtils.hasText(addressId)){
            criteria.andIdEqualTo(Long.parseLong(addressId));
        }
        return addressDOMapper.selectByExample(example);
    }




}