package com.qdbank.mall.mapper.orderrefund;

import com.qdbank.mall.model.orderrefund.OrderRefundExceptionDO;

import java.util.List;

/**
 * OrderRefundExceptionDOMapper
 *
 * @author shaoshihang
 * @date 2021/3/26 15:00
 * @since 1.0.0
 */
public interface OrderRefundExceptionDOMapper {
    List<OrderRefundExceptionDO> selectList();

    void deleteById(Long id);

    void insert(OrderRefundExceptionDO orderRefundExceptionDO);

    void updateById(OrderRefundExceptionDO orderRefundExceptionDO);
}
