package com.qdbank.mall.dao.coupon;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.qdbank.mall.enums.CouponTypeEnum;
import com.qdbank.mall.mapper.coupon.CouponDOMapper;
import com.qdbank.mall.mapper.prefecturestockrelation.PrefectureStockRelationDOMapper;
import com.qdbank.mall.mapper.usercoupon.UserCouponDOMapper;
import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.coupon.CouponDOExample;
import com.qdbank.mall.model.prefecturestockrelation.PrefectureStockRelationDO;
import com.qdbank.mall.model.prefecturestockrelation.PrefectureStockRelationDOExample;
import com.qdbank.mall.model.usercoupon.UserCouponDO;
import com.qdbank.mall.model.usercoupon.UserCouponDOExample;
import com.qdbank.mall.model.usercoupon.UserCouponDetailDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

import static cn.hutool.core.collection.IterUtil.isNotEmpty;

/**
 * @author hongjh
 * @date 2021/3/17
 */
@Repository
public class CouponDao {

    @Autowired
    private CouponDOMapper couponDOMapper;

    @Autowired
    private UserCouponDOMapper userCouponDOMapper;

    public Integer createUserCoupon(UserCouponDO userCouponDO){
        return userCouponDOMapper.insert(userCouponDO);
    }
    @Autowired
    private PrefectureStockRelationDOMapper prefectureStockRelationDOMapper;
    /**
     * 查询用户优惠券
     * @param custNo
     * @param status
     * @param userCouponId
     * @param productId
     * @param productSkuId
     * @return
     */
    public List<UserCouponDetailDO> qryCouponDetailPage(String custNo, Long status,Long userCouponId,Long productId,Long productSkuId){
        List<UserCouponDetailDO> userCouponDetailList = userCouponDOMapper.qryCouponDetail(custNo, status, userCouponId, productId, productSkuId);
        return this.buildTime(userCouponDetailList);
    }

    public CouponDO  qryCouponDetailById(Long couponId){
        return couponDOMapper.selectByPrimaryKey(couponId);
    }


    public CouponDO  qryCouponDetailByUserCouponId(Long userCouponId){
        return couponDOMapper.qryCouponDetailByUserCouponId(userCouponId);
    }

    /**
     * 查询积分列表规格
     * @param couponType
     * @param productStatus
     * @return
     */
    public List<CouponDO> qryIntegralCouponPage(Long couponType, Long productStatus){
        CouponDOExample example = new CouponDOExample();
        example.setOrderByClause("product_type,coupon_amount");
        CouponDOExample.Criteria criteria =example.createCriteria();
        criteria.andCouponTypeEqualTo(couponType);
        criteria.andProductStatusEqualTo(productStatus);
        return couponDOMapper.selectByExample(example);
    }

    /**
     * 通过客户号 订单号处理
     * @param custNo
     * @param userCouponId
     * @param orderSn
     * @param beforeStatus
     * @param afterStatus
     * @return
     */
    public int updateUserCouponStatus(String custNo,Long userCouponId,String orderSn,Long beforeStatus,Long afterStatus){
        UserCouponDO userCouponDO = new UserCouponDO();
        userCouponDO.setUpdateTime(new Date());
        userCouponDO.setStatus(afterStatus);

        UserCouponDOExample example = new UserCouponDOExample();
        UserCouponDOExample.Criteria criteria =example.createCriteria();
        criteria.andUserCouponIdEqualTo(userCouponId);
        criteria.andStatusEqualTo(beforeStatus);
        criteria.andCustNoEqualTo(custNo);
        return userCouponDOMapper.updateByExampleSelective(userCouponDO,example);
    }

    public CouponDO  qryCouponDetailByBatchNo(String batchNo){
        CouponDOExample example = new CouponDOExample();
        CouponDOExample.Criteria criteria =example.createCriteria();
        criteria.andBatchNoEqualTo(batchNo);
        List<CouponDO> results=couponDOMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(results)){
            return results.get(0);
        }
        return null;
    }

    /**
     * 判断是否是指定专区现金优惠券(发放方式为北斗)
     * @param couponType    优惠券类型
     * @param subActivityId  子活动id
     * @return  true:是 false:否
     */
    private boolean isBeidouDesignatedPrefectureCoupon(long couponType, String subActivityId) {
        return (CouponTypeEnum.PREFECTURE_CASH_COUPON.getCode().equals(couponType) || CouponTypeEnum.INTEGRATION_COUPON.getCode().equals(couponType))
                && StringUtils.isNotEmpty(subActivityId);
    }
    
    public List<UserCouponDetailDO> selectCustCouponList(String custNo,Long status,Date statTime,Date endTime){
        List<UserCouponDetailDO> result = userCouponDOMapper.selectCouponList(custNo,status,statTime,endTime);
        return this.buildTime(result);
    }
    private List<UserCouponDetailDO> buildTime(List<UserCouponDetailDO> userCouponDetailList){
        //特殊处理优惠券开始时间和结束时间
        if(isNotEmpty(userCouponDetailList)) {
            userCouponDetailList.forEach(userCouponDetail -> {
                String batchNo = userCouponDetail.getBatchNo();
                if(StringUtils.isNotEmpty(batchNo)) {
                    //先判断是否是指定专区现金优惠券(北斗发放,batchNo有值)
                    if(isBeidouDesignatedPrefectureCoupon(userCouponDetail.getCouponType(), userCouponDetail.getSubActivityId())) {
                        userCouponDetail.setStartTime(userCouponDetail.getCreateTime());
                        userCouponDetail.setEndTime(userCouponDetail.getExpireDate());
                        if(userCouponDetail.getPrefectureId() != null){
                            PrefectureStockRelationDOExample prefectureStockRelationDOExample = new PrefectureStockRelationDOExample();
                            prefectureStockRelationDOExample.createCriteria().andPrefectureIdEqualTo(userCouponDetail.getPrefectureId());
                            List<PrefectureStockRelationDO> list = prefectureStockRelationDOMapper.selectByExample(prefectureStockRelationDOExample);
                            if(CollUtil.isNotEmpty(list) && list.size() == 1){
                                userCouponDetail.setProductId(list.get(0).getProductId());
                            }
                        }
                    } else {
                        CouponDOExample couponDOExample = new CouponDOExample();
                        couponDOExample.createCriteria().andBatchNoEqualTo(batchNo);
                        List<CouponDO> couponDOList = couponDOMapper.selectByExample(couponDOExample);
                        if(CollectionUtil.isNotEmpty(couponDOList)) {
                            CouponDO couponDO = couponDOList.get(0);
                            //开始时间默认取实际的发放时间
                            userCouponDetail.setStartTime(couponDO.getSendTime());
                            userCouponDetail.setEndTime(couponDO.getEndTime());
                        }
                    }

                } else {
                    //目前仅积分兑换券自行兑换场景
                    userCouponDetail.setStartTime(userCouponDetail.getCreateTime());
                    userCouponDetail.setEndTime(userCouponDetail.getExpireDate());
                }
            });
        }
        return userCouponDetailList;
    }
}