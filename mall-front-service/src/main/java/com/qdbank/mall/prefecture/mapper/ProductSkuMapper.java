package com.qdbank.mall.prefecture.mapper;

import com.qdbank.mall.model.product.ProductSkuDO;
import com.qdbank.mall.response.product.ProductResDTO;
import com.qdbank.mall.response.product.ProductSkuResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductSkuMapper {



    @Mappings({
            /*@Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),*/
    })
    ProductSkuResDTO skuPoToDTO(ProductSkuDO productSkuDO);



    @Mappings({
            /*@Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),*/
    })
    List<ProductSkuResDTO> proPoToList(List<ProductSkuDO> list);








}
