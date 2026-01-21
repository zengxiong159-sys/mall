package com.qdbank.mall.coupon.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.api.StatusEnum;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.coupon.AbstractCouponService;
import com.qdbank.mall.coupon.CouponMqSendService;
import com.qdbank.mall.enums.CouponDistributeWayEnum;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.coupon.CouponDOMapper;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.mapper.usercoupon.UserCouponDOMapper;
import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.usercoupon.UserCouponDO;
import com.qdbank.mall.request.coupon.CouponDetailQueryReqDTO;
import com.qdbank.mall.request.coupon.CouponReqDTO;
import com.qdbank.mall.request.coupon.UpdateCouponReqDTO;
import com.qdbank.mall.request.coupon.UpdateCouponStatusReqDTO;
import com.qdbank.mall.response.coupon.CouponResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;


/**
 * @ClassName IntegrationCouponServiceImpl
 * @Description 积分兑换券服务
 * @Author ningyuehuai
 * @Date 2021/3/7 17:16
 * @Version 1.0
 **/
@Service
@Slf4j
public class IntegrationCouponServiceImpl extends AbstractCouponService {
    @Autowired
    private UserCouponDOMapper userCouponDOMapper;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private CouponDOMapper couponDOMapper;
    @Autowired
    private CouponMqSendService couponMqSendService;
    @Override
    public int create(CouponReqDTO couponReqDTO) {
        CouponDO couponDO = new CouponDO();
        BeanUtils.copyProperties(couponReqDTO,couponDO);
        couponDO.setCouponId(super.generateId());
        couponDO.setProductStatus(0L);
        couponDO.setDistributeWay(CouponDistributeWayEnum.SELF_EXCHANGE.getCode());
        //固定商品
        ProductEnum productEnum = ProductEnum.getProductByType(couponReqDTO.getProductType());
        couponDO.setProductId(productEnum.productId);
        return super.create(couponDO);
    }

    @Override
    public int update(UpdateCouponReqDTO updateCouponReqDTO, MultipartFile file) {
        super.checkCouponId(updateCouponReqDTO.getCouponId());
        CouponDO couponDO = new CouponDO();
        BeanUtils.copyProperties(updateCouponReqDTO,couponDO);
        return super.update(couponDO);
    }
    @Override
    public int updateStatus(UpdateCouponStatusReqDTO couponStatusReqDTO){
        super.checkCouponId(couponStatusReqDTO.getCouponId());
        return  super.updateStatus(couponStatusReqDTO);
    }

    @Override
    public CommonResult exportCoupons( String batchNo,Long couponType) {
        return CommonResult.success(null);
    }

    @Override
    public int updateExpireCoupon(Date date) {
        List<String> list = userCouponDOMapper.selectExpireOrderSN(DateUtil.offsetDay(date,-7),date);
       int count = userCouponDOMapper.updateIntegrationCouponExpireStatus(DateUtil.offsetDay(date,-7),date);
       log.info("积分兑换券过期用户数量:{}",count);
       if(count > 0){
           log.info("查询过期积分兑换订单数量：{}", CollUtil.isNotEmpty(list) ? list.size() : 0);
           if(CollUtil.isNotEmpty(list)){
              int orderCount = orderDOMapper.batchUpdateExpireOrder(list);
              log.info("修改过期积分兑换订单数量:{}",orderCount);
           }
           List<UserCouponDO> userCouponDOList = userCouponDOMapper.queryExpireUserCoupon(DateUtil.offsetDay(date,-7),date,0L);
           couponMqSendService.couponListMqSend(userCouponDOList);
       }
       return count;
    }

    /**
     * 积分兑换券详情
     * @param couponDetailQueryReqDTO   优惠券信息详情请求参数DTO
     * @return  积分兑换券信息
     */
    @Override
    public CouponResDTO detail(CouponDetailQueryReqDTO couponDetailQueryReqDTO) {
        CouponResDTO integrationResDTO = new CouponResDTO();
        CouponDO couponDO = couponDOMapper.selectByPrimaryKey(couponDetailQueryReqDTO.getCouponId());
        if(couponDO == null || !StatusEnum.COUPON_PRODUCT_STATUS_ON.getCode().equals(couponDO.getProductStatus())) {
            throw new ApiException(ResultCode.COUPON_PRODUCT_NOT_EXIST);
        }
        BeanUtils.copyProperties(couponDO, integrationResDTO);
        return integrationResDTO;
    }
}
