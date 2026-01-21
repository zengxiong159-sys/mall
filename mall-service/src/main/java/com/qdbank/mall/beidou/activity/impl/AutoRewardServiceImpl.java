package com.qdbank.mall.beidou.activity.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.qdbank.mall.annotation.Lock;
import com.qdbank.mall.annotation.LockName;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.api.StatusEnum;
import com.qdbank.mall.beidou.activity.AutoRewardService;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.coupon.CouponMqSendService;
import com.qdbank.mall.domain.CouponMQBO;
import com.qdbank.mall.domain.submsg.CouponToAcctMsgMQBO;
import com.qdbank.mall.domain.submsg.SubMsgCommonBO;
import com.qdbank.mall.enums.CouponDistributeWayEnum;
import com.qdbank.mall.enums.PrefectureStatusEnum;
import com.qdbank.mall.enums.SubMsgTypeEnum;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.coupon.CouponDOMapper;
import com.qdbank.mall.mapper.prefecture.PrefectureDOMapper;
import com.qdbank.mall.mapper.usercoupon.UserCouponDOMapper;
import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.coupon.CouponDOExample;
import com.qdbank.mall.model.prefecture.PrefectureDO;
import com.qdbank.mall.model.prefecture.PrefectureInfoDO;
import com.qdbank.mall.model.product.ProductSkuDO;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.model.usercoupon.UserCouponDO;
import com.qdbank.mall.model.usercoupon.UserCouponDOExample;
import com.qdbank.mall.response.beidou.activity.req.*;
import com.qdbank.mall.response.beidou.activity.res.CouponInvalidatedResDTO;
import com.qdbank.mall.response.beidou.activity.res.CouponResDTO;
import com.qdbank.mall.response.beidou.activity.res.PrefectureProductResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.submsg.SubMsgMqSendService;
import com.qdbank.mall.util.AesUtils;
import com.qdbank.mall.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static cn.hutool.core.collection.IterUtil.isNotEmpty;
import static com.qdbank.mall.util.Constant.*;

/**
 * @Author zengxiong
 * @Description 北斗达标活动自动奖励服务实现
 * @Date 2021/11/25 14:11
 */
@Service
@Slf4j
@RefreshScope
public class AutoRewardServiceImpl extends BaseServiceImpl implements AutoRewardService {

    @Resource
    private CouponDOMapper couponDOMapper;

    @Resource
    private UserCouponDOMapper userCouponDOMapper;

    @Resource
    private PrefectureDOMapper prefectureDOMapper;

    @Resource
    private AesUtils aesUtils;

    @Value(value = "${product.jumpurl.prefix:/pages/webview2/webview?type=13&id=}")
    private String productJumpUrlPrefix;

    @Autowired
    private CouponMqSendService couponMqSendService;

    @Autowired
    private SubMsgMqSendService subMsgMqSendService;

    /**
     * 权益列表查询,查询所有已生效的指定专区现金优惠券
     *
     * @return 权益列表
     */
    @Override
    public List<CouponResDTO> getCouponList() {
        CouponDOExample couponDOExample = new CouponDOExample();
        couponDOExample.setOrderByClause("create_time desc");
        CouponDOExample.Criteria criteria = couponDOExample.createCriteria();
        criteria.andBatchStatusEqualTo(StatusEnum.BATCH_STATUS_EFFECTIVE.getCode());
        criteria.andCouponDistributeWayEqualTo(CouponDistributeWayEnum.SYSTEM_DISTRIBUTE.getCode());
        criteria.andSubActivityIdIsNull();
        List<CouponDO> couponList = couponDOMapper.selectByExample(couponDOExample);
        List<CouponResDTO> couponResDTOList = Lists.newArrayList();
        if (isNotEmpty(couponList)) {
            couponList.stream().forEach(couponDO -> {
                CouponResDTO couponResDTO = new CouponResDTO();
                BeanUtils.copyProperties(couponDO, couponResDTO);
                //设置专区名称
                Long prefectureId = couponResDTO.getPrefectureId();
                if (prefectureId != null) {
                    PrefectureDO prefectureDO = prefectureDOMapper.selectByPrimaryKey(prefectureId);
                    couponResDTO.setPrefectureName(prefectureDO.getPrefectureName());
                }
                couponResDTOList.add(couponResDTO);
            });
        }
        return couponResDTOList;
    }

    /**
     * 权益绑定
     *
     * @param commonReqDTO 券信息列表请求DTO
     * @return 绑定结果
     */
    @Override
    public boolean batchUpdateCoupon(CommonReqDTO commonReqDTO) {
        try {
            List<CouponDO> couponDOList = Lists.newArrayList();
            String dataDecrypt = aesUtils.aesDecrypt(commonReqDTO.getData());
            List<UpdateCouponReqDTO> updateCouponReqDTOList = JSON.parseArray(dataDecrypt, UpdateCouponReqDTO.class);
            if (isNotEmpty(updateCouponReqDTOList)) {
                updateCouponReqDTOList.forEach(updateCouponReqDTO -> {
                    CouponDO couponDO = new CouponDO();
                    BeanUtils.copyProperties(updateCouponReqDTO, couponDO);

                    //单独设置有效期结束时间
                    couponDO.setEndTime(updateCouponReqDTO.getActivityEndDate());
                    couponDOList.add(couponDO);
                });
            }
            couponDOMapper.batchUpdate(couponDOList);
            return true;
        } catch (Exception e) {
            log.error("batchUpdateCoupon error", e);
            throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
        }
    }

    /**
     * 专区商品列表查询
     *
     * @param prefectureProductQueryReqDTO 指定专区优惠券信息
     * @return 专区商品列表
     */
    @Override
    public List<PrefectureProductResDTO> prefectureProductList(PrefectureProductQueryReqDTO prefectureProductQueryReqDTO) {
        //验证指定专区现金优惠券
        Long couponId = prefectureProductQueryReqDTO.getCouponId();
        CouponDO couponDO = couponDOMapper.selectByPrimaryKey(couponId);
        if (couponDO == null) {
            throw new ApiException(ResultCode.COUPON_ID_ERROR);
        }
        BigDecimal couponAmount = couponDO.getCouponAmount();

        //查询专区商品列表
        PrefectureDO prefectureDO = new PrefectureDO();
        prefectureDO.setId(couponDO.getPrefectureId());
        PrefectureInfoDO prefectureInfoDO = prefectureDOMapper.selectPrefectureInfo(prefectureDO);
        if (prefectureInfoDO == null || PrefectureStatusEnum.DISABLE.getCode().equals(prefectureDO.getStatus())) {
            throw new ApiException(ResultCode.PREFECTURE_ID_ERROR);
        }

        List<PrefectureProductResDTO> prefectureProductResDTOList = Lists.newArrayList();
        List<ProductSkuDO> productSkuDOS = prefectureInfoDO.getProductSkuDOS();
        if (isNotEmpty(productSkuDOS)) {
            for (ProductSkuDO productSkuDO : productSkuDOS) {
                PrefectureProductResDTO prefectureProductResDTO = new PrefectureProductResDTO();
                prefectureProductResDTO.setProductName(productSkuDO.getProductName());
                prefectureProductResDTO.setMailPicUrl(productSkuDO.getMailPicUrl());

                List<SkustockDO> skustocks = productSkuDO.getSkustocks();
                //按照市场价倒序
                skustocks.sort(Comparator.comparing(SkustockDO::getMarketPrice).reversed());
                if (isNotEmpty(skustocks)) {
                    //只取市场价最高的规格
                    SkustockDO skustockDO = skustocks.get(0);
                    Long productId = skustockDO.getProductId();
                    prefectureProductResDTO.setProductId(productId);
                    //原价(市场价)
                    prefectureProductResDTO.setOriginalPrice(skustockDO.getMarketPrice());
                    //计算兑换价,兑换价 = 折算价(PRODUCT_CASH + PRODUCT_INTEGRATION*0.01) - 优惠券面额
                    Long productIntegration = skustockDO.getProductIntegration();
                    BigDecimal exchangePrice = skustockDO.getProductCash()
                            .add(new BigDecimal(productIntegration).multiply(new BigDecimal("0.01")))
                            .subtract(couponAmount);
                    prefectureProductResDTO.setExchangePrice(exchangePrice);
                    prefectureProductResDTO.setJumpUrl(productJumpUrlPrefix + productId);
                    prefectureProductResDTOList.add(prefectureProductResDTO);
                }
            }
        }
        return prefectureProductResDTOList;
    }

    /**
     * 用户优惠券使用状态查询
     *
     * @param userCouponQueryReqDTO 用户持券状态查询请求DTO
     * @return 优惠券使用状态
     */
    @Override
    public Long queryUserCoupon(UserCouponQueryReqDTO userCouponQueryReqDTO) {
        UserCouponDOExample userCouponDOExample = new UserCouponDOExample();
        userCouponDOExample.setOrderByClause("create_time desc");

        UserCouponDOExample.Criteria criteria = userCouponDOExample.createCriteria();
        criteria.andCustNoEqualTo(userCouponQueryReqDTO.getCustNo());
        criteria.andCouponIdEqualTo(userCouponQueryReqDTO.getCouponId());
        List<UserCouponDO> userCouponList = userCouponDOMapper.selectByExample(userCouponDOExample);
        if (isNotEmpty(userCouponList)) {
            //有且只有一条
            UserCouponDO userCouponDO = userCouponList.get(0);
            return userCouponDO.getStatus();
        }
        return null;
    }

    /**
     * 发券
     *
     * @param couponId 券id
     * @param custNo   通联核心客户号
     * @return 发券结果 true:成功 false:失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Lock(leaseTime = 10, timeUnit = TimeUnit.SECONDS)
    public boolean issueCoupon(@LockName Long couponId, @LockName String custNo) {
        //根据优惠券id查询优惠券信息
        CouponDO couponDO = couponDOMapper.selectByPrimaryKey(couponId);
        if (couponDO == null) {
            throw new ApiException(ResultCode.COUPON_ID_ERROR);
        }
        //验证是否已生效
        Long batchStatus = couponDO.getBatchStatus();
        if (!StatusEnum.BATCH_STATUS_EFFECTIVE.getCode().equals(batchStatus)) {
            throw new ApiException(ResultCode.COUPON_NOT_ACTIVE);
        }

        //查询用户持券表数据
        UserCouponDOExample userCouponDOExample = new UserCouponDOExample();
        userCouponDOExample.setOrderByClause("create_time desc");

        UserCouponDOExample.Criteria criteria = userCouponDOExample.createCriteria();
        criteria.andCustNoEqualTo(custNo);
        criteria.andCouponIdEqualTo(couponId);
        List<UserCouponDO> userCouponList = userCouponDOMapper.selectByExample(userCouponDOExample);
        if (isNotEmpty(userCouponList)) {
            throw new ApiException(ResultCode.COUPON_ISSUE_REPEAT);
        }

        //用户持券表插入数据
        UserCouponDO userCouponDO = new UserCouponDO();
        userCouponDO.setCouponId(couponId);
        userCouponDO.setCreateTime(new Date());
        userCouponDO.setUpdateTime(new Date());
        userCouponDO.setBatchNo(couponDO.getBatchNo());
        userCouponDO.setCouponType(couponDO.getCouponType());
        userCouponDO.setStatus(StatusEnum.USER_COUPON_NOT_USED.getCode());
        userCouponDO.setCustNo(custNo);
        userCouponDO.setUserCouponId(super.generateId());
        userCouponDO.setCouponName(couponDO.getCouponName());

        //设置过期时间
        LocalDate now = LocalDate.now();
        LocalDate expireLocalDate = now.plusDays(couponDO.getExpireDays());
        ZonedDateTime zonedDateTime = expireLocalDate.atStartOfDay(ZoneId.systemDefault());
        Date expireDate = Date.from(zonedDateTime.toInstant());
        Date expireEndDate = TimeUtil.dateEndChange(expireDate);
        userCouponDO.setExpireDate(expireEndDate);
        boolean userCouponInsertFlag = userCouponDOMapper.insert(userCouponDO) > 0;
        boolean couponUpdateFlag = false;
        if (userCouponInsertFlag) {
            //将该批次券对应的all total字段自增
            couponUpdateFlag = couponDOMapper.updateCouponAllTotal(couponDO) > 0;
        }
        boolean flag = userCouponInsertFlag && couponUpdateFlag;
        if (flag) {
            sendCouponToAcctMsgMq(userCouponDO);

            CouponMQBO couponMQBO = new CouponMQBO();
            couponMQBO.setOperateType(Constant.OPERATE_TYPE_INSERT);
            couponMQBO.setCouponId(userCouponDO.getUserCouponId() + "");
            couponMQBO.setStatus("20");
            couponMQBO.setDescription(couponDO.getCouponDescription());
            couponMQBO.setCustNo(userCouponDO.getCustNo() + "");
            couponMQBO.setCreateTime(DateUtil.format(userCouponDO.getCreateTime(), DatePattern.NORM_DATETIME_PATTERN));
            couponMQBO.setAvailableBeginTime(DateUtil.format(userCouponDO.getCreateTime(), DatePattern.NORM_DATETIME_PATTERN));
            couponMQBO.setAvailableEndTime(DateUtil.format(userCouponDO.getExpireDate(), DatePattern.NORM_DATETIME_PATTERN));
            couponMQBO.setCouponNotice("");
            couponMQBO.setMallCouponType(userCouponDO.getCouponType() + "");
            couponMQBO.setCouponAmount(couponDO.getCouponAmount());
            couponMQBO.setCouponName(couponDO.getCouponName());
            couponMQBO.setTransactionMinimum("");
            couponMQBO.setProductType(couponDO.getProductType() + "");
            couponMqSendService.couponMqSend(couponMQBO);
        }
        return flag;
    }

    /**
     * 批量失效
     *
     * @param commonReqDTO 优惠券失效请求DTO
     * @return 操作结果 true:成功 false:失败
     */
    @Override
    public boolean batchInvalidCoupon(CommonReqDTO commonReqDTO) {
        List<CouponDO> couponDOList = Lists.newArrayList();
        try {
            String dataDecrypt = aesUtils.aesDecrypt(commonReqDTO.getData());
            List<InvalidCouponReqDTO> invalidCouponReqDTOList = JSON.parseArray(dataDecrypt, InvalidCouponReqDTO.class);
            if (isNotEmpty(invalidCouponReqDTOList)) {
                invalidCouponReqDTOList.forEach(invalidCouponReqDTO -> {
                    CouponDO couponDO = new CouponDO();
                    BeanUtils.copyProperties(invalidCouponReqDTO, couponDO);

                    //设置批次状态为失效
                    couponDO.setBatchStatus(StatusEnum.BATCH_STATUS_INVALIDATION.getCode());
                    couponDOList.add(couponDO);
                });
            }
            couponDOMapper.batchUpdate(couponDOList);
            return true;
        } catch (Exception e) {
            log.error("batchInvalidCoupon error", e);
            throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
        }
    }

    /**
     * 批量查询优惠券失效状态
     *
     * @param couponInvalidatedReqDTOList 优惠券id列表
     * @return 优惠券失效状态
     */
    @Override
    public List<CouponInvalidatedResDTO> getCouponInvalidated(List<CouponInvalidatedReqDTO> couponInvalidatedReqDTOList) {
        CouponDOExample couponDOExample = new CouponDOExample();
        CouponDOExample.Criteria criteria = couponDOExample.createCriteria();
        criteria.andCouponIdIn(couponInvalidatedReqDTOList.stream().map(CouponInvalidatedReqDTO::getCouponId).collect(Collectors.toList()));
        List<CouponDO> couponList = couponDOMapper.selectByExample(couponDOExample);

        List<CouponInvalidatedResDTO> couponInvalidatedResDTOList = new ArrayList<>(couponList.size());
        if (isNotEmpty(couponList)) {
            couponList.forEach(couponDO -> {
                CouponInvalidatedResDTO couponInvalidatedResDTO = new CouponInvalidatedResDTO();
                couponInvalidatedResDTO.setCouponId(couponDO.getCouponId());
                couponInvalidatedResDTO.setBatchNo(couponDO.getBatchNo());
                couponInvalidatedResDTO.setInvalidated(StatusEnum.BATCH_STATUS_INVALIDATION.getCode().equals(couponDO.getBatchStatus()));
                couponInvalidatedResDTOList.add(couponInvalidatedResDTO);
            });
        }
        return couponInvalidatedResDTOList;
    }

    /**
     * 发送优惠券到账提醒kafka消息
     *
     * @param userCouponDO 用户优惠券信息
     */
    private void sendCouponToAcctMsgMq(UserCouponDO userCouponDO) {
        SubMsgCommonBO subMsgCommonBO = new SubMsgCommonBO();
        subMsgCommonBO.setType(SubMsgTypeEnum.COUPON_TO_ACCOUNT_MSG.getCode());
        subMsgCommonBO.setMsgCode(SUB_MSG_PREFIX + super.generateId());
        subMsgCommonBO.setCustNo(userCouponDO.getCustNo());

        CouponToAcctMsgMQBO couponToAcctMsgMQBO = new CouponToAcctMsgMQBO();
        couponToAcctMsgMQBO.setCouponName(userCouponDO.getCouponName());
        couponToAcctMsgMQBO.setRemark(COUPON_TO_ACCOUNT_MSG_REMARK);
        couponToAcctMsgMQBO.setSendTime(DateUtil.format(userCouponDO.getCreateTime(), DatePattern.NORM_DATETIME_FORMAT));
        couponToAcctMsgMQBO.setJumpUrl(MALL_COUPON_JUMP_URL);
        subMsgCommonBO.setContent(couponToAcctMsgMQBO);

        subMsgMqSendService.sendSubMsgMq(subMsgCommonBO);
    }

}
