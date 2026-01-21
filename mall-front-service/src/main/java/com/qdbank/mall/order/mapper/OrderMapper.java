package com.qdbank.mall.order.mapper;

import com.qdbank.mall.model.activity.ActivityDO;
import com.qdbank.mall.model.address.AddressDO;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.request.address.AddressReqDTO;
import com.qdbank.mall.request.address.UpdateAddressReqDTO;
import com.qdbank.mall.response.activity.ActivityResDTO;
import com.qdbank.mall.response.order.OrderResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mappings({
            /*@Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),*/
    })
    OrderResDTO poToDo(OrderDO orderdo);



    List<OrderResDTO> poToDTOList(List<OrderDO> orders);





}
