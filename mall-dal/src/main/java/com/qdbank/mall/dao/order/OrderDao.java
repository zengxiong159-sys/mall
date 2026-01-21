package com.qdbank.mall.dao.order;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.qdbank.mall.constant.payment.orderstatus.RealOrderStatus;
import com.qdbank.mall.constent.payment.RefundStatausEnum;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.mapper.orderrefund.OrderRefundDOMapper;
import com.qdbank.mall.mapper.rechargestatusdetail.RechargeStatusDOMapper;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderDOExample;
import com.qdbank.mall.model.order.OrderAndRefundDetailDO;
import com.qdbank.mall.model.order.OrderStatusDO;
import com.qdbank.mall.model.orderrefund.OrderRefundWithOrderDetailDO;
import com.qdbank.mall.model.rechargestatusdetail.RechargeStatusDO;
import com.qdbank.mall.model.rechargestatusdetail.RechargeStatusDOExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author hongjh
 */
@Repository
@Slf4j
public class OrderDao  {

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private RechargeStatusDOMapper rechargeStatusDOMapper;


    @Autowired
    OrderRefundDOMapper orderRefundDOMapper;


    /**
     * 查询用户订单列表
     * @param custNo 客户号
     * @param productType 产品类型
     * @param status 查询状态
     * @param descFlag 排序时间
     */
    public List<OrderDO> qryOrderList(String custNo ,List<Long> productType,  Long status ,boolean descFlag ){
        OrderDOExample example = new OrderDOExample();
        example.setOrderByClause("CREATE_TIME "+(descFlag?"desc":""));
        OrderDOExample.Criteria criteria=example.createCriteria();
        //订单号
        criteria.andCustNoEqualTo(Long.parseLong(custNo));
       // criteria.andCreateTimeGreaterThan(DateUtil.parse ((DateUtil.thisYear()-1)+DateUtil.today().substring(4), DatePattern.NORM_DATE_PATTERN));
        //产品类型
        criteria.andProductTypeIn(productType);
        if(status != -1){
            if(status!=5){
                //普通状态查询:非退款，退款状态未退款字段有值
                criteria.andStatusEqualTo(status);
                //涉及退款过滤查询
              /*  List<Long > refunds = new ArrayList<>();
                refunds.add(RefundStatausEnum.INIT.refundStatus);
                refunds.add(RefundStatausEnum.APPROVE_NO.refundStatus);
                refunds.add(RefundStatausEnum.APPROVE_YES.refundStatus);
                criteria.andRefundStatusIn(refunds);*/
            }else if(status==5){
                //存在退款申请的查询
                criteria.andRefundStatusNotEqualTo(RefundStatausEnum.INIT.refundStatus);
            }
        }
        List<OrderDO> orders =orderDOMapper.selectByExample(example);
        if(CollUtil.isNotEmpty(orders)){
            Iterator<OrderDO> iterator = orders.iterator();
            while (iterator.hasNext()){
                OrderDO orderDO = iterator.next();

                if((orderDO.getCloseType() == 0 || orderDO.getCloseType() == 1 || orderDO.getCloseType() == 2 || orderDO.getCloseType() == 3 || orderDO.getCloseType() == 4) && (orderDO.getCreateTime().getTime() < (DateUtil.parse ((DateUtil.thisYear()-1)+DateUtil.today().substring(4)+" 23:59:59")).getTime())  ){
                    iterator.remove();
                }
            }
        }
        return orders;
    }

    /**
     * 查询用户订单列表
     * @param custNo 客户号
     * @param descFlag 排序时间
     */
    public List<OrderRefundWithOrderDetailDO> qryRefundOrderList(String custNo  , boolean descFlag ){
       /* OrderRefundDOExample example = new OrderRefundDOExample();
        example.setOrderByClause("CREATE_TIME "+(descFlag?"desc":""));
        OrderRefundDOExample.Criteria criteria=example.createCriteria();
        criteria.andCustNoEqualTo(custNo).andRefundTypeNotEqualTo(2L);
        //订单号
        List<OrderRefundDO> orders =orderRefundDOMapper.selectByExample(example);
        return orders;*/

        return  orderRefundDOMapper.selectRefundWithOrderByExample(custNo,null);

    }


    public Integer createOrder(OrderDO order) {
        return  orderDOMapper.insert(order);
    }

    public OrderDO selectHasOrderByIntegrealOrderSn(String orderSn){
        return orderDOMapper.selectHasOrderByIntegrealOrderSn(orderSn);
    }


    public List<OrderDO> qryOrderByOrderStatusSn(String orderSn,String status){
        OrderDOExample example = new OrderDOExample();
        OrderDOExample.Criteria criteria=example.createCriteria();
        //订单号
        criteria.andOrderSnEqualTo(orderSn);
        criteria.andNoticeStatusEqualTo(Long.parseLong(status));
        List<OrderDO> orders =orderDOMapper.selectByExample(example);
       return orders;
    }

    public List<OrderDO> qryOrderBySn(String orderSn){
        OrderDOExample example = new OrderDOExample();
        OrderDOExample.Criteria criteria=example.createCriteria();
        //订单号
        criteria.andOrderSnEqualTo(orderSn);
        List<OrderDO> orders =orderDOMapper.selectByExample(example);
        return orders;
    }

    /**
     * 获取用户已限购规格数量
     * @param custNo
     * @param productId
     * @param skuId
     * @param promotionStartTime
     * @param promotionEndTime
     * @return
     */
    public Long  countHasBuyProduct_0(String custNo, Long productId, Long skuId, Date promotionStartTime,Date promotionEndTime){

        Long[] _status = {RealOrderStatus.Status.PREPARE_PAY.status,RealOrderStatus.Status.STAY_DELIVER_GOODS.status,
                RealOrderStatus.Status.DELIVER_GOODS.status,RealOrderStatus.Status.HAS_FINISH.status};

        List<Long> status = Arrays.asList(_status);

        OrderDOExample example = new OrderDOExample();
        OrderDOExample.Criteria criteria=example.createCriteria();
        //客户号
        criteria.andCustNoEqualTo(Long.parseLong(custNo));
        //商品id
        criteria.andProductIdEqualTo(productId);
        //规格id
        criteria.andProductSkuIdEqualTo(skuId);
        //限制日期
        criteria.andCreateTimeBetween(promotionStartTime,promotionEndTime);
        //状态
        criteria.andStatusIn(status);
        return orderDOMapper.countProductByExample(example);
    }

  /*  public Long  countHasBuyProduct_1(String custNo, Long productId, Long skuId,Date promotionStartTime,Date promotionEndTime){
        OrderDOExample example = new OrderDOExample();
        OrderDOExample.Criteria criteria=example.createCriteria();
        //客户号
        criteria.andCustNoEqualTo(Long.parseLong(custNo));
        //商品id
        criteria.andProductIdEqualTo(productId);
        //规格id
        criteria.andProductSkuIdEqualTo(skuId);
        //限制日期
        criteria.andCreateTimeBetween(promotionStartTime,promotionEndTime);
        //支付时间
        criteria.andPaymentTimeBetween(promotionStartTime,promotionEndTime);
        criteria.andRefundStatusNotEqualTo(2L);
        return orderDOMapper.countProductByExample(example);
    }*/

    /**
     * 通过客户号 订单号状态更新处理
     * @param custNo
     * @param orderSn
     * @param orderStatusDO
     * @return
     */
    public int updateUserOrderStatus(OrderDO orderDO,String custNo, String orderSn, OrderStatusDO orderStatusDO){
        //配合状态更新，新的字段
        if(orderDO==null){
            orderDO = new OrderDO();
        }

        Long beforeStatus = orderStatusDO.getBeforeStatus();
        Long afterStatus = orderStatusDO.getAfterStatus();

        orderDO.setUpdateTime(new Date());

        orderDO.setStatus(afterStatus);
        orderDO.setRefundStatus(orderStatusDO.getAfterApproveStatus());
        orderDO.setCloseType(orderStatusDO.getAfterCloseType());

        OrderDOExample example = new OrderDOExample();
        OrderDOExample.Criteria criteria =example.createCriteria();

        criteria.andCustNoEqualTo(Long.parseLong(custNo));
        criteria.andOrderSnEqualTo(orderSn);

        if(beforeStatus != null){
            criteria.andStatusEqualTo(beforeStatus);
        }else{
            criteria.andStatusIsNull();
        }

     //   if(orderStatusDO.getBeforeApproveStatus() != null){
            criteria.andRefundStatusEqualTo(orderStatusDO.getBeforeApproveStatus());
      /*  }else{
            criteria.andRefundStatusIsNull();
        }*/

   //     if(orderStatusDO.getBeforeCloseType() != null){
            criteria.andCloseTypeEqualTo(orderStatusDO.getBeforeCloseType());
       /* }else{
            criteria.andCloseTypeIsNull();
        }*/

        return orderDOMapper.updateByExampleSelectiveForMachineStatus(orderDO,example);
    }

    /**
     * 通过客户号 订单号状态更新处理
     * @param custNo
     * @param orderSn
     * @param noticFlag
     * @return
     */
    public int updateUserOrderStatus(String custNo, String orderSn, Long noticFlag,Date paytime){

        OrderDO orderDO = new OrderDO();
        orderDO.setUpdateTime(new Date());
        orderDO.setNoticeStatus(noticFlag);
        if(paytime!=null){
            orderDO.setPaymentTime(paytime);
        }

        OrderDOExample example = new OrderDOExample();
        OrderDOExample.Criteria criteria =example.createCriteria();

        criteria.andCustNoEqualTo(Long.parseLong(custNo));
        criteria.andOrderSnEqualTo(orderSn);

        return orderDOMapper.updateByExampleSelective(orderDO,example);
    }

    /**
     * 通过客户号 订单号支付时间处理
     * @param custNo
     * @param orderSn
     * @param paymentTime
     * @return
     */
    public int updatePaymentTime(String custNo, String orderSn,Date paymentTime){

        OrderDO orderDO = new OrderDO();
        orderDO.setUpdateTime(new Date());
        orderDO.setPaymentTime(paymentTime);

        OrderDOExample example = new OrderDOExample();
        OrderDOExample.Criteria criteria =example.createCriteria();

        criteria.andCustNoEqualTo(Long.parseLong(custNo));
        criteria.andOrderSnEqualTo(orderSn);
        criteria.andPaymentTimeIsNull();
        return orderDOMapper.updateByExampleSelective(orderDO,example);
    }


    /**
     * 查询订单详情
     * @return
     */
    public List<OrderAndRefundDetailDO> qryRefundOrder(String orderId , String orderSn){
        return orderDOMapper.selectRefundOrderList(orderId,orderSn,null);
    }


    /**
     * 查询订单详情
     * @return
     */
    public OrderAndRefundDetailDO qryRefundOrder(String refundSerial){
       List<OrderAndRefundDetailDO> list = orderDOMapper.selectRefundOrderList(null,null,refundSerial);
       if(!CollectionUtils.isEmpty(list)){
           return list.get(0);
       }
        return null;
    }



    /**
     * 增加状态数据
     * @param rechargeStatusDO
     * @return
     */
    public Integer createProcessStatus(RechargeStatusDO rechargeStatusDO){
        return rechargeStatusDOMapper.insert(rechargeStatusDO);
    }

    /**
     * 删除状态数据
     * @param orderSn
     * @param status
     * @return
     */
    public Integer rmProcessStatus(String orderSn,Long status){
        RechargeStatusDOExample example = new RechargeStatusDOExample();
        RechargeStatusDOExample.Criteria criteria=example.createCriteria();
        criteria.andOrderSnEqualTo(orderSn);
        criteria.andStatusEqualTo(status);
        return rechargeStatusDOMapper.deleteByExample(example);
    }

    /**
     * 查询状态机制
     * @param orderSn
     * @return
     */
    public List<RechargeStatusDO> qryProcessStatus(String orderSn){
        RechargeStatusDOExample example = new RechargeStatusDOExample();
        example.setOrderByClause("DETAIL_LEVEL");
        RechargeStatusDOExample.Criteria criteria=example.createCriteria();
        criteria.andOrderSnEqualTo(orderSn);
        return rechargeStatusDOMapper.selectByExample(example);
    }


    public long countOrderByProductTypeAndStatus(String custNo,Long status,Long... productTypes){
        OrderDOExample example = new OrderDOExample();
        OrderDOExample.Criteria criteria=example.createCriteria();

        //产品类型
        if(productTypes!=null && productTypes.length>0){
            criteria.andProductTypeIn(Arrays.asList(productTypes));
        }
        //状态
        if(status!=null){
            criteria.andStatusEqualTo(status);
        }

        //统计数量不处理退款--***
        List<Long > refunds = new ArrayList<>();
        refunds.add(RefundStatausEnum.PREPARE_APPROVE.refundStatus);
        refunds.add(RefundStatausEnum.APPROVE_YES.refundStatus);
        refunds.add(RefundStatausEnum.REFUND.refundStatus);
        criteria.andRefundStatusNotIn(refunds);

        //客户号
        criteria.andCustNoEqualTo(Long.parseLong(custNo));
        return orderDOMapper.countByExample(example);
    }


    public OrderDO qryOrderByRefundSerino(String refundSerino){
        return orderDOMapper.selectByRefundSerino(refundSerino);
    }





}
