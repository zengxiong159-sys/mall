package com.qdbank.mall.activity.mapper;

import com.qdbank.mall.model.activity.ActivityDO;
import com.qdbank.mall.model.address.AddressDO;
import com.qdbank.mall.request.address.AddressReqDTO;
import com.qdbank.mall.request.address.UpdateAddressReqDTO;
import com.qdbank.mall.response.activity.ActivityResDTO;
import com.qdbank.mall.response.address.AddressResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    @Mappings({
            /*@Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),*/
    })
    AddressDO dTOToPo(AddressReqDTO addressReqDTO);


    @Mappings({
            /*@Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),*/
    })
    AddressDO updateDtoToPo(UpdateAddressReqDTO updateAddressReqDTO);

    @Mappings({
            /*@Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),*/
    })
    ActivityResDTO poToDTO(ActivityDO activityDO);



    List<ActivityResDTO> poToDTOList(List<ActivityDO> addressDO);





}
