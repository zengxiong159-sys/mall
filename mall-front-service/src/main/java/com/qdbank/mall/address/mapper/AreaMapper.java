package com.qdbank.mall.address.mapper;

import com.qdbank.mall.model.address.AddressDO;
import com.qdbank.mall.model.area.AreaDO;
import com.qdbank.mall.request.address.AddressReqDTO;
import com.qdbank.mall.request.address.UpdateAddressReqDTO;
import com.qdbank.mall.response.address.AddressResDTO;
import com.qdbank.mall.response.area.AreaResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AreaMapper {

    @Mappings({
            /*@Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),*/
    })
    AreaResDTO dTOToPo(AreaDO areaDO);








}
