package com.qdbank.mall.orderimport.impl;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.coupon.AsyncInsertCouponService;
import com.qdbank.mall.model.usercoupon.UserCouponDO;
import com.qdbank.mall.model.usercoupon.UserIntegrationImportDO;
import com.qdbank.mall.orderimport.OrderImportService;
import com.qdbank.mall.request.orderimport.OrderImportReqDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName IntegerationImportServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/7/6 11:27
 * @Version 1.0
 **/
@Service
@Slf4j
public class IntegerationImportServiceImpl implements OrderImportService {
    @Autowired
    private AsyncInsertCouponService asyncInsertCouponService;
    @Override
    public CommonResult orderImport(OrderImportReqDTO orderImportReqDTO) {
        try {
            Long start = System.currentTimeMillis();
            ExcelReader reader = ExcelUtil.getReader(orderImportReqDTO.getFile().getInputStream());
            reader.addHeaderAlias("用户持券优惠券编号","userCouponId");
            reader.addHeaderAlias("优惠券名称","couponName");
            reader.addHeaderAlias("积分券的积分值","orderIntegration");
            reader.addHeaderAlias("关联积分兑换订单号","orderSn");
            reader.addHeaderAlias("优惠券状态","status");
            reader.addHeaderAlias("优惠券创建时间","createTime");
            reader.addHeaderAlias("优惠券兑换时间","updateTime");
            reader.addHeaderAlias("优惠券过期时间","expireDate");
            reader.addHeaderAlias("客户号","custNo");
            List<UserIntegrationImportDO> orderDOList = reader.readAll(UserIntegrationImportDO.class);
            List<UserCouponDO> list = new ArrayList<>();
            for(UserIntegrationImportDO u : orderDOList){
                UserCouponDO userCouponDO = new UserCouponDO();
                userCouponDO.setCouponId(getExchangeId(u.getCouponName()));
                userCouponDO.setStatus(getStatus(u.getStatus()));
                userCouponDO.setCouponName(u.getCouponName());
                userCouponDO.setCreateTime(u.getCreateTime());
                userCouponDO.setUpdateTime(u.getUpdateTime());
                userCouponDO.setExpireDate(u.getExpireDate());
                userCouponDO.setCouponType(0L);
                userCouponDO.setOrderSn(u.getOrderSn());
                userCouponDO.setUserCouponId(u.getUserCouponId());
                userCouponDO.setOrderIntegration(u.getOrderIntegration());
                userCouponDO.setCustNo(u.getCustNo());
                list.add(userCouponDO);
            }
            asyncInsertCouponService.batchInsert(list);
            log.info("用户积分兑换券数据解析条数:{},耗时:{}",list.size(),System.currentTimeMillis() - start);
        }catch (Exception e){
            log.error("解析历史积分兑换券数据异常:{}",e);
        }
        return null;
    }

    private Long getStatus(String status){
        if("已过期".equals(status)){
            return 2L;
         }
        if("已使用".equals(status)){
            return 1L;
        }
        if("待使用".equals(status)){
            return 0L;
        }
        //默认已作废
        return 3L;
    }
    private Long getExchangeId(String couponName){
        if("10元话费充值券".equals(couponName)) return 10000L;
        if("10元油卡充值券".equals(couponName)) return 10001L;
        if("15元话费充值券".equals(couponName)) return   10002L;
        if("30元油卡充值券".equals(couponName)) return  10003L ;
        if("3元话费充值券".equals(couponName)) return   10004L;
        if("3元视频会员充值券".equals(couponName)) return  10005L ;
        if("50元油卡充值券".equals(couponName)) return  10006L ;
        if("5元话费充值券".equals(couponName)) return   10007L;
        if("5元视频会员充值券".equals(couponName)) return 10008L;
        return 0L;
    }
}
