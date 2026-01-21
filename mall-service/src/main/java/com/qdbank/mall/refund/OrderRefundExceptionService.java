package com.qdbank.mall.refund;

import com.qdbank.mall.model.orderrefund.OrderRefundExceptionDO;

import java.util.List;

/**
 * OrderRefundExceptionService
 *
 * @author shaoshihang
 * @date 2021/3/26 15:41
 * @since 1.0.0
 */
public interface OrderRefundExceptionService {
    List<OrderRefundExceptionDO> selectList();

    void deleteById(Long id);
}
