package com.qdbank.mall.coupon.impl;

import com.google.common.collect.Lists;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.api.StatusEnum;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.CouponEnum;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.constant.payment.event.IntegralOrderEvent;
import com.qdbank.mall.coupon.CouponService;
import com.qdbank.mall.coupon.mapper.CouponMapper;
import com.qdbank.mall.dao.coupon.CouponDao;
import com.qdbank.mall.domain.CouponMQBO;
import com.qdbank.mall.enums.PrefectureStatusEnum;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.prefecturestockrelation.PrefectureStockRelationDOMapper;
import com.qdbank.mall.mapper.usercoupon.UserCouponDOMapper;
import com.qdbank.mall.mobile.MobileService;
import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.prefecture.PrefectureInfoDO;
import com.qdbank.mall.model.prefecturestockrelation.PrefectureStockRelationDOExample;
import com.qdbank.mall.model.product.ProductSkuDO;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.model.usercoupon.UserCouponDO;
import com.qdbank.mall.model.usercoupon.UserCouponDetailDO;
import com.qdbank.mall.mq.CouponMqSendService;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.prefecture.PrefectureService;
import com.qdbank.mall.request.coupon.CouponQueryReqDTO;
import com.qdbank.mall.request.coupon.UserCouponListReqDTO;
import com.qdbank.mall.request.coupon.UserCouponReqDTO;
import com.qdbank.mall.request.coupon.UserCouponStatusReqDTO;
import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.response.coupon.UserCouponResDTO;
import com.qdbank.mall.response.coupon.UserCouponStatusResDTO;
import com.qdbank.mall.response.mobile.MobileSkuResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.hutool.core.collection.CollUtil.isNotEmpty;

/**
 * @ClassName PrefectureServiceImpl
 * @Description TODO
 * @Author hongjh
 * @Date 2021/3/17 19:41
 * @Version 1.0
 **/
@Service
@Slf4j
public class CouponServiceImpl extends BaseServiceImpl implements CouponService {


    @Autowired
    private CouponDao couponDao;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    OrderService orderService;


    @Autowired
    PaymentService paymentService;

    @Autowired
    PrefectureService prefectureService;

    @Autowired
    MobileService mobileService;

    @Autowired
    private UserCouponDOMapper userCouponDOMapper;
    @Autowired
    private CouponMqSendService couponMqSendService;
    @Autowired
    private PrefectureStockRelationDOMapper prefectureStockRelationDOMapper;
    @Override
    public UserCouponResDTO qryCouponDetail(String custNo, Long userCouponId) {
        //查询商品号对应的券id
        List<UserCouponDetailDO> result = couponDao.qryCouponDetailPage(custNo, null, userCouponId, null, null);
        List<UserCouponResDTO> qresult = couponMapper.poToDtoList(result);
        if (!CollectionUtils.isEmpty(qresult)) {
            return qresult.get(0);
        }
        return null;
    }

    @Override
    public List<UserCouponResDTO> qryCouponDetailPage(String custNo, UserCouponReqDTO req) {
        //查询商品号对应的券id
        List<UserCouponDetailDO> result = couponDao.qryCouponDetailPage(custNo, req.getStatus(), null, req.getProductId(), req.getProductSkuId());
        result = this.buildResult(result);
        return couponMapper.poToDtoList(result);
    }


    @Override
    public CouponResDTO qryCouponDetailById(Long counponId) {
        CouponDO result = couponDao.qryCouponDetailById(counponId);
        return couponMapper.cpoToDTO(result);
    }

    @Override
    public List<CouponResDTO> qryIntegralCouponPage(CouponQueryReqDTO req) {
        List<CouponDO> result = couponDao.qryIntegralCouponPage(req.getCouponType(), req.getProductStatus());
        return couponMapper.cpoToDtoList(result);
    }

    @Override
    public List<UserCouponResDTO> countUseCounpon(String custNo, UserCouponReqDTO req) {
        List<UserCouponDetailDO> result = couponDao.qryCouponDetailPage(custNo, req.getStatus(), null, null, null);
        List<UserCouponResDTO> qresult = couponMapper.poToDtoList(result);
        Date now = new Date();
        if (!CollectionUtils.isEmpty(qresult)) {
            qresult.stream().filter(item -> {
                if (now.compareTo(item.getStartTime()) < 0 || now.compareTo(item.getEndTime()) > 0) {
                    return false;
                }
                return true;
            });
        }
        return qresult;
    }


    @Override
    public void checkUserCoupon(OrderDO order) {
        //查询当前用户的
        List<UserCouponDetailDO> result = couponDao.qryCouponDetailPage(order.getCustNo() + "", 0L, order.getUserCouponId(), null, null);
        //查询不到，非本人或已过期
        if (CollectionUtils.isEmpty(result)) {
            throw new ApiException(ResultCode.USER_COUPON_EXPIR);
        }

        UserCouponDetailDO userCouponDetail = result.get(0);

        //优惠券类型
        Long couponType = userCouponDetail.getCouponType();
        CouponEnum couponEnum = CouponEnum.getCouponType(couponType);
        boolean noPass = true;

        //指定专区现金优惠券校验
        if (CouponEnum.PREFECTURE_CASH_COUPON.couponType.equals(couponType)) {
            Long prefectureId = userCouponDetail.getPrefectureId();
            List<PrefectureInfoDO> prefectureInfoDOS = prefectureService.qryProductsByPrefectureId(prefectureId);

            //判断专区数据存在且已启用,专区商品列表不为空且包含该商品
            if (validatePrefecture(prefectureInfoDOS)) {
                List<ProductSkuDO> productSkuDOList = prefectureInfoDOS.get(0).getProductSkuDOS();
                List<Long> productIdList = productSkuDOList.stream().map(ProductSkuDO::getProductId).collect(Collectors.toList());
                noPass = !productIdList.contains(order.getProductId());
            }
        } else {
            //优惠券是否对应指定商品
            if (userCouponDetail.getProductId().equals(order.getProductId())) {
                //券批次处理
                if (couponEnum.group == CouponEnum.CouponGroup.APPOINT) {
                    if (userCouponDetail.getProductSkuId().equals(order.getProductSkuId())) {
                        noPass = false;
                    }
                } else {
                    /**
                     * 规格判断：话费无规格概念
                     */
                    noPass = false;
                }
            }
        }

        //校验不通过报错
        if (noPass) {
            throw new ApiException(ResultCode.USER_COUPON_NOT_FIX_PRODUCT);
        }

        log.info("当前优惠券信息-[{}]", JsonUtil.toJSONString(userCouponDetail));

        order.setCouponType(couponType);
        //优惠券面值
        order.setCouponAmount(userCouponDetail.getCouponAmount());
        //优惠券id--只有非积分兑换券的订单才会触发调用使用----重要
        order.setExchangeCouponId(userCouponDetail.getCouponId());

        //优惠券有效期判断
        //积分兑换券
        Date now = new Date();
        if (CouponEnum.COUPON_TYPE_EXCHANGE == couponEnum) {
            //定时任务设置的过期时间
            if (now.compareTo(userCouponDetail.getExpireDate()) > 0) {
                throw new ApiException(ResultCode.USER_COUPON_EXPIR);
            }
            //用户兑换需做处理，银行发放不存在积分兑换券记录
            if (!StringUtils.hasText(userCouponDetail.getBatchNo())) {
                //更新源订单的状态--积分兑换操作需更新源订单
                OrderDO couponOrder = orderService.qryOrderBySn(userCouponDetail.getOrderSn());
                //源订单使用
                paymentService.handlerOrder(couponOrder, IntegralOrderEvent.USE, null);
            }
            //      integralPaymentService.hasUseCoupon(couponOrder);
        } else if (CouponEnum.CouponGroup.APPOINT == couponEnum.group) {
            //指定商品免费兑换券--通过后管设置有效期进行处理
            if (userCouponDetail.getEndTime().compareTo(now) < 0) {
                throw new ApiException(ResultCode.USER_COUPON_EXPIR);
            }

            //除指定专区现金优惠券,校验优惠券开始时间
            if (!CouponEnum.PREFECTURE_CASH_COUPON.couponType.equals(couponType) && userCouponDetail.getStartTime().compareTo(now) > 0) {
                throw new ApiException(ResultCode.USER_COUPON_NOT_GET_EXPIR);
            }
        }
    }


    @Override
    public void resetCouponStatus(OrderDO order, Long afterStatus) {
        //查询当前用户的
        List<UserCouponDetailDO> result = couponDao.qryCouponDetailPage(order.getCustNo() + "", StatusEnum.USER_COUPON_USED.getCode(), order.getUserCouponId(), null, null);
        //查询不到，非本人或已过期
        if(CollectionUtils.isEmpty(result)){
            throw new ApiException(ResultCode.USER_COUPON_EXPIR);
        }

        UserCouponDetailDO userCouponDetail = result.get(0);

        //更新源订单的状态--积分兑换操作需更新源订单
        OrderDO couponOrder = orderService.qryOrderBySn(userCouponDetail.getOrderSn());
        Map params = new HashMap<>();
        params.put("afterStatus", afterStatus);
        //源订单使用
        paymentService.handlerOrder(couponOrder, IntegralOrderEvent.RESET, params);

    }


    @Override
    public boolean activeUserCoupon(OrderDO order, Long beforeStatus, Long afterStatus) {
        int result = couponDao.updateUserCouponStatus(order.getCustNo() + "", order.getUserCouponId(), order.getOrderSn(), beforeStatus, afterStatus);
        return result > 0;
    }

    @Override
    public Integer createUserCoupon(UserCouponDO userCoupon) {
        if (userCoupon.getUserCouponId() == null) {
            userCoupon.setUserCouponId(super.generateId());
        }
        return couponDao.createUserCoupon(userCoupon);
    }

    /**
     * 根据客户号查询指定专区现金优惠券(匹配当前商品id)列表
     *
     * @param userCouponDO 用户持券信息
     * @return 指定专区现金优惠券列表
     */
    @Override
    public List<UserCouponResDTO> queryPrefectureUserCouponList(UserCouponDO userCouponDO) {
        List<UserCouponResDTO> result = Lists.newArrayList();
        Long curProductId = userCouponDO.getProductId();
        Long curProductSkuId = userCouponDO.getProductSkuId();

        List<UserCouponDetailDO> userCouponDetailDOS = userCouponDOMapper.selectPrefectureUserCouponList(userCouponDO);
        List<UserCouponResDTO> userCouponResDTOList = couponMapper.poToDtoList(userCouponDetailDOS);
        if (isNotEmpty(userCouponResDTOList)) {
            //循环查询所有专区下对应的所有商品id
            for (UserCouponResDTO userCouponResDTO : userCouponResDTOList) {
                long prefectureId = userCouponResDTO.getPrefectureId();
                List<PrefectureInfoDO> prefectureInfoDOS = prefectureService.qryProductsByPrefectureId(prefectureId);

                //校验专区数据存在且已启用,专区商品列表不为空
                if (validatePrefecture(prefectureInfoDOS)) {
                    List<ProductSkuDO> productSkuDOList = prefectureInfoDOS.get(0).getProductSkuDOS();
                    productSkuDOList.forEach(productSkuDO -> {
                        //校验商品或商品规格是否下架
                        if (curProductId.equals(productSkuDO.getProductId())
                                && validateProductAndSkustoks(curProductId, curProductSkuId)) {
                            result.add(userCouponResDTO);

                        }
                    });
                }
            }
        }
        return result;
    }

    @Override
    public UserCouponStatusResDTO queryCouponStatus(UserCouponStatusReqDTO userCouponStatusReqDTO) {
        UserCouponResDTO userCouponResDTO = this.qryCouponDetail(null,userCouponStatusReqDTO.getUserCouponId());
        if(userCouponResDTO == null) throw new ApiException(ResultCode.COUPON_ID_ERROR);
        UserCouponStatusResDTO userCouponStatusResDTO = new UserCouponStatusResDTO();
        BeanUtils.copyProperties(userCouponResDTO,userCouponStatusResDTO);
        if(!userCouponStatusResDTO.getStatus().equals(0L)){//非待使用状态 写入MQ
            CouponMQBO couponMQBO = new CouponMQBO();
            couponMQBO.setOperateType(Constant.OPERATE_TYPE_DELETE);;
            couponMQBO.setCouponId(userCouponResDTO.getUserCouponId()+"");
            couponMqSendService.couponMqSend(couponMQBO);
        }
        userCouponStatusResDTO.setProductCount(0L);
        if(userCouponResDTO.getPrefectureId() != null){
            PrefectureStockRelationDOExample prefectureStockRelationDOExample = new PrefectureStockRelationDOExample();
            prefectureStockRelationDOExample.createCriteria().andPrefectureIdEqualTo(userCouponResDTO.getPrefectureId());
            userCouponStatusResDTO.setProductCount(prefectureStockRelationDOMapper.countByExample(prefectureStockRelationDOExample));
        }
        return userCouponStatusResDTO;
    }

    @Override
    public List<UserCouponResDTO> selectCouponDetailPage(UserCouponListReqDTO userCouponReqDTO) {
        //查询商品号对应的券id
        List<UserCouponDetailDO> result = couponDao.selectCustCouponList(userCouponReqDTO.getCustNo(),userCouponReqDTO.getStatus(),userCouponReqDTO.getStartTime(),userCouponReqDTO.getEndTime());
        result = this.buildResult(result);
        return couponMapper.poToDtoList(result);
    }

    /**
     * 校验专区是否启用且专区商品列表是否为空
     *
     * @param prefectureInfoDOS 专区列表
     * @return true:通过 false:不通过
     */
    private boolean validatePrefecture(List<PrefectureInfoDO> prefectureInfoDOS) {
        return isNotEmpty(prefectureInfoDOS) && PrefectureStatusEnum.ENABLE.getCode().equals(prefectureInfoDOS.get(0).getStatus())
                && isNotEmpty(prefectureInfoDOS.get(0).getProductSkuDOS());
    }

    /**
     * 校验商品和商品规格是否上架
     *
     * @param productId    商品id
     * @param productSkuId 规格id
     * @return true:通过 false:不通过
     */
    private boolean validateProductAndSkustoks(Long productId, Long productSkuId) {
        ProductSkuDO productSkuDO = prefectureService.qryProductSku(productId, productSkuId, true);
        if (productSkuDO != null) {
            List<SkustockDO> skustocks = productSkuDO.getSkustocks();
            return Constant.PUBLISH_STATUS_SHELVES.equals(productSkuDO.getPublishStatus())
                    && (isNotEmpty(skustocks));
        }
        return false;
    }

    private  List<UserCouponDetailDO> buildResult(List<UserCouponDetailDO> result){
        //话费规格判断
        List<MobileSkuResDTO> initSkus = mobileService.qryMobileSkus();
        boolean notSupportMobile = CollectionUtils.isEmpty(initSkus);

        if (!CollectionUtils.isEmpty(result)) {
            result.forEach(item -> {
                Long couponType = item.getCouponType();
                CouponEnum couponEnum = CouponEnum.getCouponType(couponType);
                //批次优惠券--判断优惠券是否下线
                if (CouponEnum.CouponGroup.APPOINT == couponEnum.group) {
                    //指定专区现金优惠券
                    if (CouponEnum.PREFECTURE_CASH_COUPON.couponType.equals(couponType)) {
                        Long prefectureId = item.getPrefectureId();
                        List<PrefectureInfoDO> prefectureInfoDOS = prefectureService.qryProductsByPrefectureId(prefectureId);

                        if (validatePrefecture(prefectureInfoDOS)) {
                            item.setProductStatus(1L);
                        } else {
                            //判断专区数据存在且已启用,专区商品列表不为空
                            item.setProductStatus(0L);
                        }
                    } else {
                        if (validateProductAndSkustoks(item.getProductId(), item.getProductSkuId())) {
                            item.setProductStatus(1L);
                        } else {
                            //商品未发布 或 规格未发布
                            item.setProductStatus(0L);
                        }
                    }
                }
                //积分兑换券逻辑
                if(couponEnum==CouponEnum.COUPON_TYPE_EXCHANGE && ProductEnum.getProductByType(item.getProductType())==ProductEnum.MOBILE_RECHARGE){
                    if(notSupportMobile && 0L!=item.getProductStatus()){
                        item.setProductStatus(0L);
                    }
                }

                //积分兑换券做时间处理--对应前端ui，开始结束时间
                /* if(0L == couponType){
                 *//*  Calendar now = Calendar.getInstance();
                   now.setTime(item.getExpireDate());
                   now.add(Calendar.DATE, (int) (0L-item.getExpireDays()));*//*
                   //过期时间--即创建时间
                   item.setStartTime(item.getCreateTime());
                   item.setEndTime(item.getExpireDate());
               }*/
            });
        }
        return result;
    }
}
