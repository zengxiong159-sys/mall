package com.qdbank.mall.order.mapper;

import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.model.orderrefund.OrderRefundWithOrderDetailDO;
import com.qdbank.mall.model.refundsetting.RefundsettingDO;
import com.qdbank.mall.request.refund.ApplyOrderRefundReqDTO;
import com.qdbank.mall.response.order.OrderResDTO;
import com.qdbank.mall.response.refund.OrderRefundResDTO;
import com.qdbank.mall.response.refundsetting.RefundSettingResDTO;
import io.swagger.annotations.ApiModelProperty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RefundMapper {


    OrderRefundDO orderToRefund(OrderDO order);



    List<OrderResDTO> poToDTOList(List<OrderDO> orders);



    @Mappings({
            @Mapping(source = "refundSerial",target = "refundSerialNo")
    })
    OrderRefundResDTO refundPotoDo(OrderRefundDO orderRefundDO);

    List<OrderRefundResDTO> refundPotoDoList(List<OrderRefundDO> orderRefundDO);

    RefundSettingResDTO refundSettingPoToDo(RefundsettingDO refundsettingDO);

    List<RefundSettingResDTO> refundSettingPoToDoList(List<RefundsettingDO> list);


    @Mappings({
            @Mapping(source = "refundSerial",target = "refundSerialNo"),
            @Mapping(source = "productIntegration",target = "singleProductIntegration"),
            @Mapping(source = "productCash",target = "singleProductCash"),
    })
    OrderRefundResDTO refundPotoWithOrderDo(OrderRefundWithOrderDetailDO orderRefundDO);



    List<OrderRefundResDTO> refundPotoWithOrderDoList(List<OrderRefundWithOrderDetailDO> orderRefundDO);



}
