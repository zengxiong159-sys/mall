package com.qdbank.mall.coupon.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.qdbank.mall.api.StatusEnum;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.coupon.AsyncInsertCouponService;
import com.qdbank.mall.coupon.CouponMqSendService;
import com.qdbank.mall.domain.CouponMQBO;
import com.qdbank.mall.domain.submsg.CouponToAcctMsgMQBO;
import com.qdbank.mall.domain.submsg.SubMsgCommonBO;
import com.qdbank.mall.enums.SubMsgTypeEnum;
import com.qdbank.mall.mapper.coupon.CouponDOMapper;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.mapper.usercoupon.UserCouponDOMapper;
import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.coupon.CouponDOExample;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.usercoupon.UserCouponDO;
import com.qdbank.mall.model.usercoupon.UserCouponDOExample;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.service.impl.FileDfsService;
import com.qdbank.mall.submsg.SubMsgMqSendService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.qdbank.mall.util.Constant.COUPON_TO_ACCOUNT_MSG_REMARK;
import static com.qdbank.mall.util.Constant.MALL_COUPON_JUMP_URL;
import static com.qdbank.mall.util.Constant.SUB_MSG_PREFIX;

/**
 * @ClassName AsyncInsertCouponServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/9 15:07
 * @Version 1.0
 **/
@Component
@Slf4j
public class AsyncInsertCouponServiceImpl extends BaseServiceImpl implements AsyncInsertCouponService {
    @Autowired
    private UserCouponDOMapper userCouponDOMapper;
    @Autowired
    private FileDfsService fastDFSService;
    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private CouponDOMapper couponDOMapper;
    @Autowired
    private CouponMqSendService couponMqSendService;

    @Autowired
    private SubMsgMqSendService subMsgMqSendService;

    @Override
    @Async
    public void batchAnsyInsert(List<UserCouponDO> userCouponDOList) {
        Long start = System.currentTimeMillis();
        this.insertData(userCouponDOList);
        log.info("异步插入白名单数据耗时：{}",(System.currentTimeMillis() - start));
    }

    @Override
    public void batchInsert(List<UserCouponDO> userCouponDOList) {
        this.insertData(userCouponDOList);
    }


    @Override
    @Async
    public void deleteFile(String groupName, String filePath) {
        fastDFSService.deleteFile(groupName,filePath);
    }

    @Override
    public <T> List<List<T>> buildBatchList(List<T> list) {
        List<List<T>> shareList = new ArrayList<>();
        if(list.size() <= 1000){
            shareList.add(list);
            return shareList;
        }
        int batchCount = 1000;
        int batchLastIndex = batchCount;
        for (int index = 0; index < list.size(); ) {
            if (batchLastIndex >= list.size()) {
                batchLastIndex = list.size();
                shareList.add(list.subList(index, batchLastIndex));
                break;
            } else {
                shareList.add(list.subList(index, batchLastIndex));
                // 设置下一批下标
                index = batchLastIndex;
                batchLastIndex = index + (batchCount - 1);
            }
        }
        return shareList;
    }

    @Override
    public void batchUpdate(List<OrderDO> orderDOList) {
        if(CollUtil.isEmpty(orderDOList)) return;
        List<List<OrderDO>> list = buildBatchList(orderDOList);
        for(List<OrderDO> subList : list){
            orderDOMapper.batchUpdate(subList);
        }
    }

    @Override
    public void batchInsertOrder(List<OrderDO> orderDOS) {
        if(CollUtil.isEmpty(orderDOS)) return;
        List<List<OrderDO>> list = buildBatchList(orderDOS);
        for(List<OrderDO> subList : list){
            try {
                orderDOMapper.batchInsertOrder(subList);
            }catch (Exception e){
               e.printStackTrace();
            }

        }
    }

    @Override
    @Async
    public void batchAnsySendMq(String batchNo,Long status) {
        try {
            UserCouponDOExample userCouponDOExample = new UserCouponDOExample();
            userCouponDOExample.createCriteria().andBatchNoEqualTo(batchNo).andStatusEqualTo(status);
            List<UserCouponDO> userCouponDOList = userCouponDOMapper.selectByExample(userCouponDOExample);
            CouponDOExample couponDOExample = new CouponDOExample();
            couponDOExample.createCriteria().andBatchNoEqualTo(batchNo);
            CouponDO couponDO = couponDOMapper.selectByExample(couponDOExample).get(0);
            String operateType = status == 0L ? Constant.OPERATE_TYPE_INSERT : Constant.OPERATE_TYPE_DELETE;
            String mqStatus = status == 0L ? "20" : "50";
            if(CollUtil.isNotEmpty(userCouponDOList)){
                userCouponDOList.stream().forEach(u -> {
                    CouponMQBO couponMQBO = new CouponMQBO();
                    couponMQBO.setCouponId(u.getUserCouponId()+"");
                    couponMQBO.setAvailableBeginTime(DateUtil.format(couponDO.getStartTime(), DatePattern.NORM_DATETIME_PATTERN));
                    couponMQBO.setAvailableEndTime(DateUtil.format(couponDO.getEndTime(),DatePattern.NORM_DATETIME_PATTERN));
                    couponMQBO.setCouponAmount(couponDO.getCouponAmount());
                    couponMQBO.setCouponNotice("");
                    couponMQBO.setCreateTime(DateUtil.format(couponDO.getSendTime(),DatePattern.NORM_DATETIME_PATTERN));
                    couponMQBO.setCustNo(u.getCustNo());
                    couponMQBO.setDescription(couponDO.getCouponDescription());
                    couponMQBO.setMallCouponType(u.getCouponType()+"");
                    couponMQBO.setStatus(mqStatus);
                    couponMQBO.setOperateType(operateType);
                    couponMQBO.setCouponName(u.getCouponName());
                    couponMqSendService.couponMqSend(couponMQBO);
                });
            }
        }catch (Exception e){
            log.info("批次号：{} 写入MQ异常:{}",batchNo,e);
        }

    }

    /**
     * 批量发送优惠券到账提醒kafka消息
     * @param couponDO 优惠券信息
     * @param sendTime 发放时间
     */
    @Override
    @Async
    public void batchAsyncSendCouponToAcctMq(CouponDO couponDO, Date sendTime) {
        try {
            String batchNo = couponDO.getBatchNo();
            UserCouponDOExample userCouponDOExample = new UserCouponDOExample();
            userCouponDOExample.createCriteria().andBatchNoEqualTo(batchNo).andStatusEqualTo(StatusEnum.USER_COUPON_NOT_USED.getCode());
            List<UserCouponDO> userCouponDOList = userCouponDOMapper.selectByExample(userCouponDOExample);
            if(CollectionUtils.isNotEmpty(userCouponDOList)) {
                userCouponDOList.forEach(userCouponDO -> {
                    SubMsgCommonBO subMsgCommonBO = new SubMsgCommonBO();
                    subMsgCommonBO.setType(SubMsgTypeEnum.COUPON_TO_ACCOUNT_MSG.getCode());
                    subMsgCommonBO.setMsgCode(SUB_MSG_PREFIX + super.generateId());
                    subMsgCommonBO.setCustNo(userCouponDO.getCustNo());

                    CouponToAcctMsgMQBO couponToAcctMsgMQBO = new CouponToAcctMsgMQBO();
                    couponToAcctMsgMQBO.setCouponName(couponDO.getCouponName());
                    couponToAcctMsgMQBO.setRemark(COUPON_TO_ACCOUNT_MSG_REMARK);
                    couponToAcctMsgMQBO.setSendTime(DateUtil.format(sendTime, DatePattern.NORM_DATETIME_FORMAT));
                    couponToAcctMsgMQBO.setJumpUrl(MALL_COUPON_JUMP_URL);
                    subMsgCommonBO.setContent(couponToAcctMsgMQBO);

                    subMsgMqSendService.sendSubMsgMq(subMsgCommonBO);
                });
            }
        } catch (Exception e) {
            log.error("batchAsyncSendCouponToAcctMq occur error：batchNo:{}", couponDO.getBatchNo(), e);
        }
    }


    private void insertData(List<UserCouponDO> userCouponDOList){
        if(CollUtil.isEmpty(userCouponDOList)) return;
        List<List<UserCouponDO>> list = this.buildBatchList(userCouponDOList);
        if (CollectionUtils.isNotEmpty(list)) {
            for (List<UserCouponDO> subList : list) {
                //循环插入数据
                userCouponDOMapper.batchInsertCoupon(subList);
            }
        }
    }
}
