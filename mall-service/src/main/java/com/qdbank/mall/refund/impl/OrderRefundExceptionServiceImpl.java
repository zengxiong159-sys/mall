package com.qdbank.mall.refund.impl;

import com.qdbank.mall.mapper.orderrefund.OrderRefundExceptionDOMapper;
import com.qdbank.mall.model.orderrefund.OrderRefundExceptionDO;
import com.qdbank.mall.refund.OrderRefundExceptionService;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * OrderRefundExceptionServiceImpl
 *
 * @author shaoshihang
 * @date 2021/3/26 15:41
 * @since 1.0.0
 */
@Service
@Slf4j
public class OrderRefundExceptionServiceImpl extends BaseServiceImpl implements OrderRefundExceptionService {

    @Resource
    private OrderRefundExceptionDOMapper orderRefundExceptionDOMapper;

    @Override
    public List<OrderRefundExceptionDO> selectList() {
        return orderRefundExceptionDOMapper.selectList();
    }

    @Override
    public void deleteById(Long id) {
        orderRefundExceptionDOMapper.deleteById(id);
    }
}
