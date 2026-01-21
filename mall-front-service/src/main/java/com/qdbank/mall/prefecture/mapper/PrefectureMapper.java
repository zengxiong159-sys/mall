package com.qdbank.mall.prefecture.mapper;

import com.qdbank.mall.model.prefecture.PrefectureDO;
import com.qdbank.mall.model.prefecture.PrefectureInfoDO;
import com.qdbank.mall.model.product.ProductSkuDO;
import com.qdbank.mall.response.prefecture.PrefectureResDTO;
import com.qdbank.mall.response.product.ProductResDTO;
import com.qdbank.mall.response.product.ProductSkuResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrefectureMapper {

    @Mappings({
            /*@Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),*/
    })
    PrefectureResDTO poToDTO(PrefectureDO prefectureDO);


    List<PrefectureResDTO> poToDtoList(List<PrefectureDO> dos);


    @Mappings({
            /*@Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),*/
    })
    ProductSkuResDTO proPoToDTO(ProductSkuDO productSkuDO);

    @Mappings({
            /*@Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),*/
    })
    List<ProductSkuResDTO> proPoToListDTO(List<ProductSkuDO> productSkuDO);


    @Mappings({
            /*@Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),*/
    })
    PrefectureResDTO poToDTO2(PrefectureInfoDO prefectureDO);


    @Mappings({
            /*@Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),*/
    })
    List<PrefectureResDTO> poToDTOList2(List<PrefectureInfoDO> prefectureDO);





}
