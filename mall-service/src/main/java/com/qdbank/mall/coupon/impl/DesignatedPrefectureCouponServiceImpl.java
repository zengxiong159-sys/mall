package com.qdbank.mall.coupon.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Maps;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.api.StatusEnum;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.payment.orderstatus.RealOrderStatus;
import com.qdbank.mall.coupon.AbstractCouponService;
import com.qdbank.mall.coupon.AsyncInsertCouponService;
import com.qdbank.mall.domain.ExportDataBO;
import com.qdbank.mall.download.AsyncDownloadService;
import com.qdbank.mall.enums.CouponDistributeWayEnum;
import com.qdbank.mall.enums.CouponTypeEnum;
import com.qdbank.mall.enums.PrefectureStatusEnum;
import com.qdbank.mall.enums.PrefectureTypeEnum;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.coupon.CouponDOMapper;
import com.qdbank.mall.mapper.download.DownloadDOMapper;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.mapper.prefecture.PrefectureDOMapper;
import com.qdbank.mall.mapper.usercoupon.UserCouponDOMapper;
import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.download.DownloadDO;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderDOExample;
import com.qdbank.mall.model.prefecture.PrefectureDO;
import com.qdbank.mall.model.usercoupon.ExportUserCouponDO;
import com.qdbank.mall.model.usercoupon.UserCouponDO;
import com.qdbank.mall.request.coupon.CouponDetailQueryReqDTO;
import com.qdbank.mall.request.coupon.CouponReqDTO;
import com.qdbank.mall.request.coupon.UpdateCouponReqDTO;
import com.qdbank.mall.request.coupon.UpdateCouponStatusReqDTO;
import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.response.coupon.ExportCouponResDTO;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.ExcelExportUtil;
import com.qdbank.mall.util.StringUtil;
import com.qdbank.mall.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

/**
 * @Author zengxiong
 * @Description 指定专区现金优惠券
 * @Date 2021/12/2 15:37
 */
@Service
@Slf4j
public class DesignatedPrefectureCouponServiceImpl extends AbstractCouponService {

    @Autowired
    private PrefectureDOMapper prefectureDOMapper;

    @Autowired
    private CouponDOMapper couponDOMapper;

    @Autowired
    private UserCouponDOMapper userCouponDOMapper;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private AsyncInsertCouponService asyncInsertCouponService;
    @Autowired
    private AsyncDownloadService asyncDownloadService;
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private DownloadDOMapper downloadDOMapper;
    /**
     * 新建指定专区现金优惠券
     *
     * @param couponReqDTO 指定专区现金优惠券请求参数信息
     * @return 操作结果
     */
    @Override
    public int create(CouponReqDTO couponReqDTO) {
        CouponDO couponDO = new CouponDO();
        //专区编号必填;专区数据存在且已启用(专区状态 0 停用 1启用)
        Long prefectureId = couponReqDTO.getPrefectureId();
        if (prefectureId == null) {
            throw new ApiException(ResultCode.VALIDATE_FAILED);
        }
        PrefectureDO prefectureDO = prefectureDOMapper.selectByPrimaryKey(prefectureId);
        if (prefectureDO == null || PrefectureStatusEnum.DISABLE.getCode().equals(prefectureDO.getStatus())) {
            throw new ApiException(ResultCode.PREFECTURE_ID_ERROR);
        }

        BeanUtils.copyProperties(couponReqDTO, couponDO);
        couponDO.setCouponId(super.generateId());
        couponDO.setBatchNo(super.generateId() + "");
        couponDO.setDistributeWay(CouponDistributeWayEnum.SYSTEM_DISTRIBUTE.getCode());

        int count;
        MultipartFile file = couponReqDTO.getFile();
        //白名单文件上传
        if (file != null) {
            List<String> dataList = checkFile(couponReqDTO.getFile());
            updateLoadFile(couponReqDTO.getFile(), couponDO);
            couponDO.setAllTotal((long) dataList.size());

            count = create(couponDO);
            this.parseExcel(dataList, couponReqDTO, couponDO);
            if (count < 1) {
                super.deleteFile(couponDO.getGroupId(), couponDO.getFileUrl());
            }
        } else {
            //北斗达标
            couponDO.setAllTotal(0L);
            couponDO.setBatchStatus(StatusEnum.BATCH_STATUS_TO_BE_EFFECTIVE.getCode());
            count = create(couponDO);
        }
        return count;
    }

    /**
     * 更新指定专区现金优惠券
     *
     * @param updateCouponReqDTO 指定专区现金优惠券请求参数信息
     * @param file               白名单文件
     * @return 操作结果
     */
    @Override
    public int update(UpdateCouponReqDTO updateCouponReqDTO, MultipartFile file) {
        Integer prefectureType = updateCouponReqDTO.getPrefectureType();
        CouponDO couponDO = checkCouponId(updateCouponReqDTO.getCouponId());
        String batchNo = couponDO.getBatchNo();

        //获取方式为白名单
        if (PrefectureTypeEnum.NORMAL.getCode().equals(prefectureType)) {
            //白名单文件上传，编辑前检查数据完整性
            if (file != null) {
                //非待发放状态,不允许编辑
                if (!StatusEnum.BATCH_STATUS_NOT_SENDED.getCode().equals(couponDO.getBatchStatus())) {
                    throw new ApiException(ResultCode.COUPON_STATUS_ERROR);
                }
                validateFileData(batchNo, couponDO);

                List<String> dataList = checkFile(file);
                CouponReqDTO couponReqDTO = new CouponReqDTO();
                String oldGroupId = couponDO.getGroupId();
                String oldFilePath = couponDO.getFileUrl();

                //上传文件后根据批次号清空用户持券信息表数据
                updateLoadFile(file, couponDO);
                if (deleteByBathNo(batchNo) < 1) {
                    throw new ApiException(ResultCode.DELETE_USER_COUPON_ERROR);
                }

                //更新白名单文件记录条数
                BeanUtils.copyProperties(couponDO, couponReqDTO);
                this.parseExcel(dataList, couponReqDTO, couponDO);
                couponDO.setAllTotal((long) dataList.size());

                //异步删除原有的白名单文件
                asyncInsertCouponService.deleteFile(oldGroupId, oldFilePath);
            } else {
                BeanUtils.copyProperties(updateCouponReqDTO, couponDO);
            }
        } else {
            //活动专区为非待生效状态,不允许编辑
            if (!StatusEnum.BATCH_STATUS_TO_BE_EFFECTIVE.getCode().equals(couponDO.getBatchStatus())) {
                throw new ApiException(ResultCode.COUPON_STATUS_ERROR);
            }
            BeanUtils.copyProperties(updateCouponReqDTO, couponDO);
        }

        //更新用户持券表信息
        UserCouponDO userCouponDO = new UserCouponDO();
        userCouponDO.setBatchNo(batchNo);
        userCouponDO.setCouponName(couponDO.getCouponName());
        userCouponDO.setUpdateTime(new Date());
        userCouponDOMapper.updateByBatchNo(userCouponDO);

        return update(couponDO);
    }

    /**
     * 指定专区现金优惠券定时过期(失效)
     *
     * @param date 指定日期
     * @return 操作结果
     */
    @Override
    public int updateExpireCoupon(Date date) {
        //查询指定专区现金优惠券前一周内所有待发放、已发放、待生效、已生效的批次号 <专区类型,批次号>
        Date weekAgoDate = DateUtil.offsetDay(date, -7);
        List<String> batchNoList = couponDOMapper.selectDesignatedPrefectureCouponBatchNos(weekAgoDate, date);
        log.info("指定专区现金优惠券(白名单导入方式)有效批次号数量：{}", isEmpty(batchNoList) ? 0 : batchNoList.size());
        if (isEmpty(batchNoList)) {
            return 0;
        }

        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("startDate", weekAgoDate);
        paramMap.put("endDate", date);

        //获取方式为白名单上传,查询所有待发放、已发放的批次号,将优惠券表数据过期处理(批次状态更新为已过期)
        paramMap.put("batchStatus", StatusEnum.BATCH_STATUS_EXPIRE.getCode());
        paramMap.put("list", batchNoList);

        int count = couponDOMapper.updateExpirePrefectureCoupon(paramMap);
        log.info("优惠券主表常规专区指定专区现金优惠券(白名单导入方式)过期数量：{}", count);
        return count;
    }

    @Override
    public CouponResDTO detail(CouponDetailQueryReqDTO couponDetailQueryReqDTO) {
        return null;
    }

    /**
     * 生效、发放、作废操作
     *
     * @param couponStatusReqDTO 优惠券信息
     * @return 操作结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStatus(UpdateCouponStatusReqDTO couponStatusReqDTO) {
        //检查传入的券id是否正确
        CouponDO couponDO = checkCouponId(couponStatusReqDTO.getCouponId());

        //根据专区类型判断获取方式,专区类型为达标专区即为北斗获取,为常规专区则为白名单上传
        Long prefectureId = couponDO.getPrefectureId();
        PrefectureDO prefectureDO = prefectureDOMapper.selectByPrimaryKey(prefectureId);

        Long oldBatchStatus = couponDO.getBatchStatus();
        Long newBatchStatus = couponStatusReqDTO.getBatchStatus();
        int count = 0;
        Long status = 0L;
        //检查传入的券批次状态是否合法
        checkBatchStatus(oldBatchStatus, newBatchStatus);

        //常规专区,白名单文件上传
        if (PrefectureTypeEnum.NORMAL.getCode().equals(prefectureDO.getPrefectureType())) {
            //检查数据完整性
            validateFileData(couponStatusReqDTO.getBatchNo(), couponDO);

            //待发放状态更新为已发放
            if (StatusEnum.BATCH_STATUS_NOT_SENDED.getCode().equals(oldBatchStatus)
                    && StatusEnum.BATCH_STATUS_SENDED.getCode().equals(newBatchStatus)) {
                UserCouponDO userCouponDO = new UserCouponDO();

                //将用户持券信息表该批次所有数据的用户使用券状态更新为待使用状态并设置优惠券过期时间(当前发放时间+指定专区现金优惠券有效天数)
                userCouponDO.setBatchNo(couponDO.getBatchNo());
                userCouponDO.setStatus(StatusEnum.USER_COUPON_NOT_USED.getCode());

                LocalDate now = LocalDate.now();
                LocalDate expireLocalDate = now.plusDays(couponDO.getExpireDays());
                ZonedDateTime zonedDateTime = expireLocalDate.atStartOfDay(ZoneId.systemDefault());
                Date expireDate = Date.from(zonedDateTime.toInstant());
                Date expireEndDate = TimeUtil.dateEndChange(expireDate);
                userCouponDO.setExpireDate(expireEndDate);
                userCouponDO.setUpdateTime(new Date());
                count = userCouponDOMapper.updateByBatchNo(userCouponDO);

                //更新优惠券主表该批次数据批次状态为已发放并设置有效期开始时间、有效期结束时间、发放时间
                couponStatusReqDTO.setStartTime(new Date());
                couponStatusReqDTO.setEndTime(expireEndDate);
                couponStatusReqDTO.setSendTime(new Date());
                int couponStatusUpdateResult = super.updateStatus(couponStatusReqDTO);

                //发送优惠券到账提醒kafka消息
                if(count > 0 && couponStatusUpdateResult > 0){
                    asyncInsertCouponService.batchAsyncSendCouponToAcctMq(couponDO, couponStatusReqDTO.getSendTime());
                }
            } else {
                /**
                 *  待使用更新为已作废
                 *  1.修改优惠券主表状态为已作废
                 *  2.修改用户持券表对应数据为已作废,排除用户使用券状态为已使用的
                 */
                super.updateStatus(couponStatusReqDTO);

                UserCouponDO userCouponDO = new UserCouponDO();
                userCouponDO.setStatus(StatusEnum.USER_COUPON_INVLALID.getCode());
                userCouponDO.setBatchNo(couponDO.getBatchNo());
                userCouponDO.setUpdateTime(new Date());
                count = userCouponDOMapper.updateUserCouponInvalidByBatchNo(userCouponDO);
                status = StatusEnum.USER_COUPON_INVLALID.getCode();
            }
            if(count > 0){
                asyncInsertCouponService.batchAnsySendMq(couponDO.getBatchNo(),status);
            }
        } else {
            //达标专区,批次状态从待生效更新为已生效(作废)
            count = super.updateStatus(couponStatusReqDTO);
        }
        return count;
    }

    @Override
    public CommonResult exportCoupons(String batchNo, Long couponType) {
        List<ExportUserCouponDO> exportUserCouponDOList = userCouponDOMapper.selectCouponDOByBatchNo(batchNo);
        if(CollUtil.isEmpty(exportUserCouponDOList)) throw new ApiException(ResultCode.DATA_EMPTY);
        String fileName = Constant.COUPON_FILE_NAME + exportUserCouponDOList.get(0).getBatchNo();
        DownloadDO downloadDO = new DownloadDO();
        downloadDO.setFileName(fileName);
        downloadDO.setMerchantNo(null);
        downloadDO.setId(super.generateId());
        downloadDO.setFileType(5L);
        downloadDO.setStatus(0L);
        umsAdminService.injectUserValue(downloadDO);
        asyncDownloadService.checkFileExist(fileName,downloadDO.getCreatedBy());
        downloadDOMapper.insert(downloadDO);
        asyncDownloadService.asyncDownloadCashCoupon(exportUserCouponDOList,downloadDO.getId());
        return CommonResult.success(null);
    }
}
