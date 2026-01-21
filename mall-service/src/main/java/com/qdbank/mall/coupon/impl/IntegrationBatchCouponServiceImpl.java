package com.qdbank.mall.coupon.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.api.StatusEnum;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.constant.payment.orderstatus.RealOrderStatus;
import com.qdbank.mall.coupon.AbstractCouponService;
import com.qdbank.mall.coupon.AsyncInsertCouponService;
import com.qdbank.mall.domain.ExportDataBO;
import com.qdbank.mall.download.AsyncDownloadService;
import com.qdbank.mall.enums.CouponDistributeWayEnum;
import com.qdbank.mall.enums.CouponObtainWayEnum;
import com.qdbank.mall.enums.CouponTypeEnum;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.coupon.CouponDOMapper;
import com.qdbank.mall.mapper.download.DownloadDOMapper;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.mapper.usercoupon.UserCouponDOMapper;
import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.download.DownloadDO;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderDOExample;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/**
 * @Author zengxiong
 * @Description 积分兑换批次券服务
 * @Date 2021/8/12 15:52
 */
@Service
@Slf4j
public class IntegrationBatchCouponServiceImpl extends AbstractCouponService {
    @Autowired
    private CouponDOMapper couponDOMapper;
    @Autowired
    private UserCouponDOMapper userCouponDOMapper;
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
     * 新建积分兑换批次券
     *
     * @param couponReqDTO 积分兑换批次券请求参数信息
     * @return 新建结果
     */
    @Override
    public int create(CouponReqDTO couponReqDTO) {
        //前端传入的券类型为对应的券服务枚举,需修正优惠券类型为积分兑换券
        couponReqDTO.setCouponType(CouponTypeEnum.INTEGRATION_COUPON.getCode());
        CouponDO couponDO = new CouponDO();
        BeanUtils.copyProperties(couponReqDTO, couponDO);
        couponDO.setCouponId(super.generateId());
        couponDO.setBatchNo(super.generateId() + "");
        couponDO.setDistributeWay(CouponDistributeWayEnum.SYSTEM_DISTRIBUTE.getCode());
        CouponDO relCouponDO = couponDOMapper.selectByPrimaryKey(couponReqDTO.getRelCouponId());
        if (relCouponDO == null || !StatusEnum.COUPON_PRODUCT_STATUS_ON.getCode().equals(relCouponDO.getProductStatus())) {
            throw new ApiException(ResultCode.PRODUCT_NOT_EXIST);
        }
        couponDO.setExpireDays(relCouponDO.getExpireDays());
        couponDO.setCouponName(relCouponDO.getCouponName());
        couponDO.setCouponAmount(relCouponDO.getCouponAmount());
        couponDO.setCouponDescription(relCouponDO.getCouponDescription());
        ProductEnum productEnum = ProductEnum.getProductByType(relCouponDO.getProductType());
        couponDO.setProductId(productEnum.productId);
        couponDO.setProductType(relCouponDO.getProductType());
        int count = 0;
        if(couponReqDTO.getFile() != null){
            /**
             * 检查白名单文件
             * 1.判断文件或文件名是否为空
             * 2.判断是否是.xlsx后缀文件
             * 3.判断文件大小是否超过5M
             * 4.判断文件内容是否为空或文件数据总数是否超过10万
             * 5.校验文件是否含有重复数据
             */
            List<String> dataList = checkFile(couponReqDTO.getFile());
            updateLoadFile(couponReqDTO.getFile(), couponDO);
            couponDO.setAllTotal(Long.valueOf(dataList.size()));
            //校验当前指定的积分兑换券信息(数据存在且状态为已上架)
            count = create(couponDO);
            this.parseExcel(dataList, couponReqDTO, couponDO);
            if (count < 1) {
                super.deleteFile(couponDO.getGroupId(), couponDO.getFileUrl());
            }
        }else {
            //北斗达标
            couponDO.setAllTotal(0L);
            couponDO.setBatchStatus(StatusEnum.BATCH_STATUS_TO_BE_EFFECTIVE.getCode());
            count = create(couponDO);
        }

        return count;
    }

    /**
     * 更新积分兑换批次券信息
     *
     * @param updateCouponReqDTO 积分兑换批次券请求参数信息
     * @param file               白名单文件
     * @return 更新记录条数
     */
    @Override
    public int update(UpdateCouponReqDTO updateCouponReqDTO, MultipartFile file) {
        //前端传入的券类型为对应的券服务枚举,需修正优惠券类型为积分兑换券
        updateCouponReqDTO.setCouponType(CouponTypeEnum.INTEGRATION_COUPON.getCode());
        CouponDO couponDO = checkCouponId(updateCouponReqDTO.getCouponId());
        Long batchStatus = couponDO.getBatchStatus();
        Integer obtainWay = updateCouponReqDTO.getObtainWay();
        if(CouponObtainWayEnum.WHITE_LIST_FILE.getCode().equals(obtainWay)) {
            if (!StatusEnum.BATCH_STATUS_NOT_SENDED.getCode().equals(batchStatus) && !StatusEnum.BATCH_STATUS_TO_BE_EFFECTIVE.getCode().equals(batchStatus)) {
                throw new ApiException(ResultCode.COUPON_STATUS_ERROR);
            }
        } else if(CouponObtainWayEnum.BEIDOU.getCode().equals(obtainWay) ) {
            if(!StatusEnum.BATCH_STATUS_TO_BE_EFFECTIVE.getCode().equals(batchStatus) && !StatusEnum.BATCH_STATUS_TO_BE_EFFECTIVE.getCode().equals(batchStatus)) {
                throw new ApiException(ResultCode.COUPON_STATUS_ERROR);
            }
        }

        //编辑前检查数据完整性
        String batchNo = couponDO.getBatchNo();
        validateFileData(batchNo, couponDO);
        if(StatusEnum.BATCH_STATUS_NOT_SENDED.getCode().equals(Long.valueOf(batchStatus)) &&  CouponObtainWayEnum.BEIDOU.getCode().equals(Long.valueOf(obtainWay)) ){
            //1. 删fsfs文件
            //2. 清空用户持券表
            //3. 券主表alltotal重置为0
            //4.状态由待发放改为待生效
            asyncInsertCouponService.deleteFile(couponDO.getGroupId(),  couponDO.getFileUrl());
            userCouponDOMapper.deleteByBatchNo(batchNo);
            CouponDO updateCouponDO = new CouponDO();
            updateCouponDO.setCouponId(couponDO.getCouponId());
            updateCouponDO.setBatchStatus(StatusEnum.BATCH_STATUS_TO_BE_EFFECTIVE.getCode());
            updateCouponDO.setAllTotal(0L);
            updateCouponDO.setDistributeWay(Long.valueOf(obtainWay));
            updateCouponDO.setFileName(null);
            updateCouponDO.setFileUrl(null);
            return couponDOMapper.updateById(updateCouponDO);
        }
        //将积分兑换券部分信息更新至积分兑换批次券
        long currentRelCouponId = updateCouponReqDTO.getRelCouponId();
        CouponDO relCouponDO = couponDOMapper.selectByPrimaryKey(currentRelCouponId);

        //更新用户持券表信息
        UserCouponDO userCouponDO = new UserCouponDO();
        userCouponDO.setBatchNo(batchNo);
        userCouponDO.setCouponId(currentRelCouponId);
        userCouponDO.setCouponName(relCouponDO.getCouponName());
        userCouponDO.setUpdateTime(new Date());
        userCouponDOMapper.updateByBatchNo(userCouponDO);

        //白名单文件被更新
        if (file != null) {
            List<String> dataList = checkFile(file);
            CouponReqDTO couponReqDTO = new CouponReqDTO();
            String oldGroupId = couponDO.getGroupId();
            String oldFilePath = couponDO.getFileUrl();
            //上传文件后根据批次号清空用户持券信息表数据
            updateLoadFile(file, couponDO);
            deleteByBathNo(batchNo);
            //更新白名单文件记录条数
            BeanUtils.copyProperties(couponDO, couponReqDTO);
            this.parseExcel(dataList, couponReqDTO, couponDO);
            couponDO.setAllTotal(Long.valueOf(dataList.size()));

            //异步删除原有的白名单文件
            asyncInsertCouponService.deleteFile(oldGroupId, oldFilePath);
        }

        //更新积分兑换批次券信息
        couponDO.setRelCouponId(currentRelCouponId);
        couponDO.setExpireDays(relCouponDO.getExpireDays());
        couponDO.setCouponName(relCouponDO.getCouponName());
        couponDO.setCouponAmount(relCouponDO.getCouponAmount());
        couponDO.setCouponDescription(relCouponDO.getCouponDescription());
        couponDO.setBatchStatus(CouponObtainWayEnum.WHITE_LIST_FILE.getCode().equals(Long.valueOf(obtainWay)) ? StatusEnum.BATCH_STATUS_NOT_SENDED.getCode() : StatusEnum.BATCH_STATUS_TO_BE_EFFECTIVE.getCode());
        couponDO.setDistributeWay(Long.valueOf(obtainWay));
        return update(couponDO);
    }

    @Override
    public int updateExpireCoupon(Date date) {
        List<String> list = couponDOMapper.selectExpireIntegrationCouponBatchNos(DateUtil.offsetDay(date, -7), date);
        log.info("查询行发批次兑换券过期批次号数量：{}", CollectionUtil.isEmpty(list) ? 0 : list.size());
        if (CollectionUtil.isEmpty(list)) return 0;
        int count = couponDOMapper.updateExpireIntegrationCoupon(DateUtil.offsetDay(date, -7), date);
        log.info("修改积分兑换券主表过期行发批次兑换券数量：{}", count);
        count = userCouponDOMapper.updateProductCouponExpireStatus(list);
        log.info("过期行发批次兑换券用户过期券数量:{}", count);
        list.stream().forEach(obj->{
            asyncInsertCouponService.batchAnsySendMq(obj,2L);
        });
        return count;
    }

    /**
     * 积分兑换批次券信息详情(复用列表list接口,无需实现)
     *
     * @param couponDetailQueryReqDTO 优惠券信息详情请求参数DTO
     * @return 积分兑换批次券信息
     */
    @Override
    public CouponResDTO detail(CouponDetailQueryReqDTO couponDetailQueryReqDTO) {
        return new CouponResDTO();
    }

    /**
     * 发放或作废操作
     *
     * @param couponStatusReqDTO 优惠券信息
     * @return 操作结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStatus(UpdateCouponStatusReqDTO couponStatusReqDTO) {
        //检查传入的券id是否正确
        CouponDO couponDO = checkCouponId(couponStatusReqDTO.getCouponId());

        //检查数据完整性
        validateFileData(couponStatusReqDTO.getBatchNo(), couponDO);

        //检查传入的券批次状态是否合法
        checkBatchStatus(couponDO.getBatchStatus(), couponStatusReqDTO.getBatchStatus());
        int count = 0;
        Long status = 0L;
        //待发放状态更新为已发放
        if (StatusEnum.BATCH_STATUS_NOT_SENDED.getCode().equals(couponDO.getBatchStatus())
                && StatusEnum.BATCH_STATUS_SENDED.getCode().equals(couponStatusReqDTO.getBatchStatus())) {
            UserCouponDO userCouponDO = new UserCouponDO();

            //将用户持券信息表该批次所有数据的用户使用券状态更新为待使用状态并设置优惠券过期时间(当前发放时间+指定的积分兑换券有效天数)
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
        //北斗发券
        }else if(StatusEnum.BATCH_STATUS_TO_BE_EFFECTIVE.getCode().equals(couponDO.getBatchStatus()) && StatusEnum.BATCH_STATUS_EFFECTIVE.getCode().equals(couponStatusReqDTO.getBatchStatus())){//待生效
           return super.updateStatus(couponStatusReqDTO);
        }else {
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
            status = 3L;
        }
        if(count > 0){
            asyncInsertCouponService.batchAnsySendMq(couponStatusReqDTO.getBatchNo(),status);
        }
        return count;
    }

    /**
     * 导出使用明细
     *
     * @param batchNo  批次号
     */
    @Override
    public CommonResult exportCoupons( String batchNo,Long couponType) {
        List<ExportUserCouponDO> exportUserCouponDOList = userCouponDOMapper.selectCouponDOByBatchNo(batchNo);
        if(CollUtil.isEmpty(exportUserCouponDOList)) throw new ApiException(ResultCode.DATA_EMPTY);
        String fileName = Constant.COUPON_FILE_NAME + exportUserCouponDOList.get(0).getBatchNo();
        DownloadDO downloadDO = new DownloadDO();
        downloadDO.setFileName(fileName);
        downloadDO.setMerchantNo(null);
        downloadDO.setId(super.generateId());
        downloadDO.setFileType(3L);
        downloadDO.setStatus(0L);
        umsAdminService.injectUserValue(downloadDO);
        asyncDownloadService.checkFileExist(fileName,downloadDO.getCreatedBy());
        downloadDOMapper.insert(downloadDO);
        asyncDownloadService.asyncDownloadIntegrationBatchCoupon(exportUserCouponDOList,downloadDO.getId());
        return CommonResult.success(null);
    }
}
