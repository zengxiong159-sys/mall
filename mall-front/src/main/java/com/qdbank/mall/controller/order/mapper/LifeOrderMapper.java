package com.qdbank.mall.controller.order.mapper;

import com.qdbank.mall.response.order.LifeOrderListResDTO;
import com.qdbank.mall.response.order.OrderResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LifeOrderMapper {

    @Mappings({
            /*@Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),*/
    })
    LifeOrderListResDTO orderToLife(OrderResDTO orderdo);



    List<LifeOrderListResDTO> orderToLifeList(List<OrderResDTO> orders);





}
