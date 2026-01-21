package com.qdbank.mall.address.mapper;

import com.qdbank.mall.model.address.AddressDO;
import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.usercoupon.UserCouponDetailDO;
import com.qdbank.mall.request.address.AddressReqDTO;
import com.qdbank.mall.request.address.UpdateAddressReqDTO;
import com.qdbank.mall.response.address.AddressResDTO;
import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.response.coupon.UserCouponResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mappings({
            @Mapping(source = "province",target = "receiverProvince"),
            @Mapping(source = "city",target = "receiverCity"),
            @Mapping(source = "regionArea",target = "receiverRegion"),
            @Mapping(source = "detailAddress",target = "receiverDetailAddress"),
            @Mapping(source = "mobile",target = "receiverMobile"),
            @Mapping(source = "userName",target = "receiverName"),
    })
    AddressDO dTOToPo(AddressReqDTO addressReqDTO);


    @Mappings({
            @Mapping(source = "province",target = "receiverProvince"),
            @Mapping(source = "city",target = "receiverCity"),
            @Mapping(source = "regionArea",target = "receiverRegion"),
            @Mapping(source = "detailAddress",target = "receiverDetailAddress"),
            @Mapping(source = "mobile",target = "receiverMobile"),
            @Mapping(source = "userName",target = "receiverName"),
    })
    AddressDO updateDtoToPo(UpdateAddressReqDTO updateAddressReqDTO);

    @Mappings({
            @Mapping(target = "province",source = "receiverProvince"),
            @Mapping(target = "city",source = "receiverCity"),
            @Mapping(target = "regionArea",source = "receiverRegion"),
            @Mapping(target = "detailAddress",source = "receiverDetailAddress"),
            @Mapping(target = "mobile",source = "receiverMobile"),
            @Mapping(target = "userName",source = "receiverName"),
    })
    AddressResDTO poToDTO(AddressDO addressDO);



    List<AddressResDTO> poToDTOList(List<AddressDO> addressDO);





}
