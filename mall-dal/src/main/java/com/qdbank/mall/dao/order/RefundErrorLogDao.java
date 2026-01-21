package com.qdbank.mall.dao.order;

import com.qdbank.mall.mapper.paymentFlow.PaymentFlowDOMapper;
import com.qdbank.mall.mapper.rechargeMobileFlow.RechargeMobileFlowDOMapper;
import com.qdbank.mall.mapper.refundErrorLog.RefundErrorLogDOMapper;
import com.qdbank.mall.model.paymentFlow.PaymentFlowDO;
import com.qdbank.mall.model.paymentFlow.PaymentFlowDOExample;
import com.qdbank.mall.model.refundErrorLog.RefundErrorLogDO;
import com.qdbank.mall.model.refundErrorLog.RefundErrorLogDOExample;
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
public class RefundErrorLogDao {

    @Autowired
    private RefundErrorLogDOMapper refundErrorLogDOMapper;


    /**
     * 查询支付流水信息
     * @param serino
     * @return
     */
    public RefundErrorLogDO qryRefundErrorLogBySerino(String serino){
        RefundErrorLogDOExample example = new RefundErrorLogDOExample();
        RefundErrorLogDOExample.Criteria criteria=example.createCriteria();
        criteria.andRefundSerialEqualTo(Long.parseLong(serino));
        List<RefundErrorLogDO> list=this.qryRefundErrorLogByExample(example);
        if(!CollectionUtils.isEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    /**
     * 查询支付流水信息
     * @param example
     * @return
     */
    private List<RefundErrorLogDO> qryRefundErrorLogByExample(RefundErrorLogDOExample example){
        List<RefundErrorLogDO> refundErrorLogs=refundErrorLogDOMapper.selectByExample(example);
        return refundErrorLogs;
    }

    /**
     * 更新支付流水信息
     * @param dto
     * @param serino
     * @return
     */
    public int updateRefundErrorLog(RefundErrorLogDO dto, String serino){
        RefundErrorLogDOExample example = new RefundErrorLogDOExample();
        RefundErrorLogDOExample.Criteria criteria  =example.createCriteria();
        criteria.andRefundSerialEqualTo(Long.parseLong(serino));
        int count =refundErrorLogDOMapper.updateByExampleSelective(dto,example);
        return count;
    }


    /**
     * 创建退款异常日志
     * @param dto
     * @return
     */
    public int createRefundErrorLog(RefundErrorLogDO dto){
        int count =refundErrorLogDOMapper.insert(dto);
        return count;
    }



}
