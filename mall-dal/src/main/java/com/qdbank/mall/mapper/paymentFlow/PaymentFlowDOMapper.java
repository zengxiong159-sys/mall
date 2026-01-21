package com.qdbank.mall.mapper.paymentFlow;

import com.qdbank.mall.model.paymentFlow.PaymentFlowDO;
import com.qdbank.mall.model.paymentFlow.PaymentFlowDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaymentFlowDOMapper {
    long countByExample(PaymentFlowDOExample example);

    int deleteByExample(PaymentFlowDOExample example);

    int deleteByPrimaryKey(Long paymentFolwId);

    int insert(PaymentFlowDO record);

    int insertSelective(PaymentFlowDO record);

    List<PaymentFlowDO> selectByExample(PaymentFlowDOExample example);

    PaymentFlowDO selectByPrimaryKey(Long paymentFolwId);

    int updateByExampleSelective(@Param("record") PaymentFlowDO record, @Param("example") PaymentFlowDOExample example);

    int updateByExample(@Param("record") PaymentFlowDO record, @Param("example") PaymentFlowDOExample example);

    int updateByPrimaryKeySelective(PaymentFlowDO record);

    int updateByPrimaryKey(PaymentFlowDO record);
}