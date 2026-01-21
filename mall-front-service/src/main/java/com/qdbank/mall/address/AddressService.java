package com.qdbank.mall.address;

import com.qdbank.mall.model.address.AddressDO;
import com.qdbank.mall.request.address.AddressReqDTO;
import com.qdbank.mall.request.address.SetDefaultAddressReqDTO;
import com.qdbank.mall.request.address.UpdateAddressReqDTO;
import com.qdbank.mall.response.address.AddressResDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AddressService {


    AddressDO createAddress(AddressReqDTO req);

    List<AddressResDTO> qryUserAddresss(String custNo);

    int updateUserAdress(String custNo, UpdateAddressReqDTO req);

    int deleteAddressById(String custNo, Long addressId);

    @Transactional
    void setDefaultAddress(String custNo, SetDefaultAddressReqDTO setDefaultAddressReqDTO);
}
