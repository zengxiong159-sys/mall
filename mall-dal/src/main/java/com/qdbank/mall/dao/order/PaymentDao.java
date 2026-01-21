package com.qdbank.mall.dao.order;

import com.qdbank.mall.constant.payment.PaymentStatusEnum;
import com.qdbank.mall.mapper.paymentFlow.PaymentFlowDOMapper;
import com.qdbank.mall.model.paymentFlow.PaymentFlowDO;
import com.qdbank.mall.model.paymentFlow.PaymentFlowDOExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author hongjh
 */
@Repository
@Slf4j
public class PaymentDao {

    @Autowired
    private PaymentFlowDOMapper paymentFlowDOMapper;


    /**
     * 查询支付流水信息
     * @param example
     * @return
     */
    private List<PaymentFlowDO> qryPaymentFlowByExample(PaymentFlowDOExample example){
        List<PaymentFlowDO> paymentFlows=paymentFlowDOMapper.selectByExample(example);
        return paymentFlows;
    }

    public List<PaymentFlowDO> qryPaymentFlowByOrderSn( String orderSn){
        PaymentFlowDOExample example = new PaymentFlowDOExample();
        PaymentFlowDOExample.Criteria criteria  =example.createCriteria();
        criteria.andOrderSnEqualTo(orderSn);
        example.setOrderByClause("UPDATE_TIME desc" );
        return this.qryPaymentFlowByExample(example);
    }

   /* public PaymentFlowDO qryPaymentFlowByOrderSnStatusSuccess(String orderSn , PaymentStatusEnum paymentStatus){
        PaymentFlowDOExample example = new PaymentFlowDOExample();
        PaymentFlowDOExample.Criteria criteria  =example.createCriteria();
        criteria.andOrderSnEqualTo(orderSn);
        criteria.andStatusEqualTo(PaymentStatusEnum.PAY_SUCCESS.payType);
        List<PaymentFlowDO> paymentFlows =this.qryPaymentFlowByExample(example);

    }

    public PaymentFlowDO qryPaymentFlowByOrderSnStatus(String orderSn , PaymentStatusEnum paymentStatus){
        PaymentFlowDOExample example = new PaymentFlowDOExample();
        PaymentFlowDOExample.Criteria criteria  =example.createCriteria();
        criteria.andOrderSnEqualTo(orderSn);
        criteria.andStatusEqualTo(paymentStatus.payType);
        List<PaymentFlowDO> paymentFlows =this.qryPaymentFlowByExample(example);

    }*/

    /**
     * 更新支付流水信息
     * @param dto
     * @param orderSn
     * @return
     */
    public int updatePaymentFlow(PaymentFlowDO dto, String orderSn){
        PaymentFlowDOExample example = new PaymentFlowDOExample();
        PaymentFlowDOExample.Criteria criteria  =example.createCriteria();
        criteria.andOrderSnEqualTo(orderSn);
        int count =paymentFlowDOMapper.updateByExampleSelective(dto,example);
        return count;
    }


    /**
     * 创建支付流水信息信息
     * @param dto
     * @return
     */
    public int createPaymentFlow(PaymentFlowDO dto){
        int count =paymentFlowDOMapper.insert(dto);
        return count;
    }



}
