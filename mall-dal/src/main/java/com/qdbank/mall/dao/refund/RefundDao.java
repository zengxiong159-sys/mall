package com.qdbank.mall.dao.refund;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qdbank.mall.mapper.orderrefund.OrderRefundDOMapper;
import com.qdbank.mall.mapper.prefecture.PrefectureDOMapper;
import com.qdbank.mall.mapper.product.ProductDOMapper;
import com.qdbank.mall.mapper.refundsetting.RefundsettingDOMapper;
import com.qdbank.mall.mapper.skustock.SkustockDOMapper;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDOExample;
import com.qdbank.mall.model.orderrefund.OrderRefundWithOrderDetailDO;
import com.qdbank.mall.model.prefecture.PrefectureDO;
import com.qdbank.mall.model.prefecture.PrefectureDOExample;
import com.qdbank.mall.model.prefecture.PrefectureInfoDO;
import com.qdbank.mall.model.product.ProductInfoQueryDO;
import com.qdbank.mall.model.product.ProductSkuDO;
import com.qdbank.mall.model.refundsetting.RefundsettingDO;
import com.qdbank.mall.model.refundsetting.RefundsettingDOExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author hongjh
 */
@Repository
public class RefundDao {

    @Autowired
    RefundsettingDOMapper refundsettingDOMapper;

    @Autowired
    OrderRefundDOMapper orderRefundDOMapper;

    public OrderRefundDO qryOrderRefund(long refundSeri){
        OrderRefundDOExample example = new OrderRefundDOExample();
        OrderRefundDOExample.Criteria criteria=example.createCriteria();
        criteria.andRefundSerialEqualTo(refundSeri);
        List<OrderRefundDO> list=orderRefundDOMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    public OrderRefundWithOrderDetailDO qryOrderRefundBySeriNo(String seriNo){
        List<OrderRefundWithOrderDetailDO> orders =orderRefundDOMapper.selectRefundWithOrderByExample(null,seriNo);
        if(!CollectionUtils.isEmpty(orders)){
            return orders.get(0);
        }
        return null;
    }

    public OrderRefundDO qryNewOrderRefundByOrderSn(String  orderSn){
        OrderRefundDOExample example = new OrderRefundDOExample();
        example.setOrderByClause("create_time desc");
        OrderRefundDOExample.Criteria criteria=example.createCriteria();
        //订单号
     //   criteria.andOrderSnEqualTo(orderSn).andRefundTypeNotEqualTo(2L);
        criteria.andOrderSnEqualTo(orderSn);
        List<OrderRefundDO> orders =orderRefundDOMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(orders)){
            return orders.get(0);
        }
        return null;
    }


    /**
     * 获取退款原因列表
     * @param status
     * @return
     */
    public List<RefundsettingDO> qrySettings(Long status){
        RefundsettingDOExample example = new RefundsettingDOExample();
        RefundsettingDOExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(status);
        return refundsettingDOMapper.selectByExample(example);
    }


    /**
     * 创建退货申请
     * @param orderRefund
     * @return
     */
    public Integer createApplyRefund(OrderRefundDO orderRefund){
        return orderRefundDOMapper.insert(orderRefund);
    }


    /**
     *更新退款状态
     * @param custNo
     * @param beforeStatus
     * @param afterStatus
     * @return
     */
    public Integer updateRefundStatus(OrderRefundDO refund,String custNo,String orderSn,Long beforeStatus,Long afterStatus){
        if(refund==null){
             refund = new OrderRefundDO();
        }
        refund.setRefundStatus(afterStatus);
        refund.setUpdateTime(new Date());


        OrderRefundDOExample example = new OrderRefundDOExample();
        OrderRefundDOExample.Criteria criteria =example.createCriteria();
        criteria.andCustNoEqualTo(custNo);
        criteria.andOrderSnEqualTo(orderSn);
        if(beforeStatus != null){
            criteria.andRefundStatusEqualTo(beforeStatus);
        }else{
            criteria.andRefundStatusIsNull();
        }

        return orderRefundDOMapper.updateByExampleSelective(refund,example);
    }



    /**
     *更新退款完成时间
     * @param seriNo
     * @return
     */
    public Integer updateRefundHandlerTime(Long seriNo){
        OrderRefundDO refund = new OrderRefundDO();
        refund.setUpdateTime(new Date());
        refund.setHandleFinishTime(new Date());

        OrderRefundDOExample example = new OrderRefundDOExample();
        OrderRefundDOExample.Criteria criteria =example.createCriteria();
        criteria.andRefundSerialEqualTo(seriNo);

        return orderRefundDOMapper.updateByExampleSelective(refund,example);
    }



























}