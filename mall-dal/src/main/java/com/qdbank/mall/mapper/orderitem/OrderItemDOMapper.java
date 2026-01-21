package com.qdbank.mall.mapper.orderitem;

import com.qdbank.mall.model.orderitem.OrderItemDO;
import com.qdbank.mall.model.orderitem.OrderItemDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemDOMapper {
    long countByExample(OrderItemDOExample example);

    int deleteByExample(OrderItemDOExample example);

    int insert(OrderItemDO record);

    int insertSelective(OrderItemDO record);

    List<OrderItemDO> selectByExample(OrderItemDOExample example);

    int updateByExampleSelective(@Param("record") OrderItemDO record, @Param("example") OrderItemDOExample example);

    int updateByExample(@Param("record") OrderItemDO record, @Param("example") OrderItemDOExample example);
}