package com.qdbank.mall.advertisment.mapper;

import com.qdbank.mall.model.advertisement.AdvertisementDO;
import com.qdbank.mall.response.advertisement.AdvertisementResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdvertismentMapper {

    @Mappings({
            /*@Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),*/
    })
    AdvertisementResDTO dTOToPo(AdvertisementDO addressReqDTO);



    List<AdvertisementResDTO> poToDTOList(List<AdvertisementDO> addressDO);





}
