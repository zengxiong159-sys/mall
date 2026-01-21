package com.qdbank.mall.coupon.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
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
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.coupon.CouponDOMapper;
import com.qdbank.mall.mapper.download.DownloadDOMapper;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.mapper.product.ProductDOMapper;
import com.qdbank.mall.mapper.usercoupon.UserCouponDOMapper;
import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.download.DownloadDO;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderDOExample;
import com.qdbank.mall.model.product.ProductDO;
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
import java.util.Date;
import java.util.List;

/**
 * @ClassName ProductCouponServiceImpl
 * @Description 指定商品免费兑换券
 * @Author ningyuehuai
 * @Date 2021/3/7 18:04
 * @Version 1.0
 **/
@Service
@Slf4j
public class ProductCouponServiceImpl extends AbstractCouponService {
    @Autowired
    private UserCouponDOMapper userCouponDOMapper;
    @Autowired
    private CouponDOMapper couponDOMapper;
    @Autowired
    private AsyncInsertCouponService asyncInsertCouponService;
    @Autowired
    private AsyncDownloadService asyncDownloadService;
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private DownloadDOMapper downloadDOMapper;
    @Override
    public int create(CouponReqDTO couponReqDTO) {
        List<String> dataList = checkFile(couponReqDTO.getFile());
        CouponDO couponDO = new CouponDO();
        BeanUtils.copyProperties(couponReqDTO,couponDO);
        couponDO.setCouponId(super.generateId());
        couponDO.setBatchNo(super.generateId() + "");
        updateLoadFile(couponReqDTO.getFile(),couponDO);
        couponDO.setAllTotal(Long.valueOf(dataList.size()));
        couponDO.setEndTime(TimeUtil.dateEndChange(couponDO.getEndTime()));
        couponDO.setDistributeWay(CouponDistributeWayEnum.SYSTEM_DISTRIBUTE.getCode());
        this.checkProduct(couponDO);
        int count = super.create(couponDO);
        this.parseExcel(dataList,couponReqDTO,couponDO);
        if(count < 1) super.deleteFile(couponDO.getGroupId(),couponDO.getFileUrl());
        return count;
    }

    @Override
    public int update(UpdateCouponReqDTO updateCouponReqDTO, MultipartFile file) {
        CouponDO couponDO = super.checkCouponId(updateCouponReqDTO.getCouponId());
        //检查数据完整性
        validateFileData(couponDO.getBatchNo(), couponDO);

        if(couponDO.getBatchStatus() != StatusEnum.BATCH_STATUS_NOT_SENDED.getCode()) throw  new ApiException(ResultCode.COUPON_STATUS_ERROR);
        CouponDO updateCouponDO = new CouponDO();
        BeanUtils.copyProperties(updateCouponReqDTO,updateCouponDO);
        updateCouponDO.setEndTime(TimeUtil.dateEndChange(updateCouponReqDTO.getEndTime()));
        int count = super.update(updateCouponDO);
        if(count > 0 && file != null){
            List<String> dataList = checkFile(file);
            CouponReqDTO couponReqDTO = new CouponReqDTO();
            //获取最新记录
            couponDO = couponDOMapper.selectByPrimaryKey(updateCouponReqDTO.getCouponId());
            String oldGroupId = couponDO.getGroupId();
            String oldFilePath = couponDO.getFileUrl();
            //文件上传
            updateLoadFile(file,couponDO);
            //先删除原有数据
            if(deleteByBathNo(couponDO.getBatchNo()) < 1) throw new ApiException(ResultCode.DELETE_USER_COUPON_ERROR);
            BeanUtils.copyProperties(couponDO,couponReqDTO);
            //重新解析入库
            this.parseExcel(dataList,couponReqDTO,couponDO);
            couponDO.setAllTotal(Long.valueOf(dataList.size()));
            super.update(couponDO);//修改记录条数
            //异步删除原有白名单文件
            asyncInsertCouponService.deleteFile(oldGroupId,oldFilePath);
        }
        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStatus(UpdateCouponStatusReqDTO couponStatusReqDTO) {
        couponStatusReqDTO.setProductStatus(null);
        CouponDO couponDO = super.checkCouponId(couponStatusReqDTO.getCouponId());
        checkBatchStatus(couponDO.getBatchStatus(), couponStatusReqDTO.getBatchStatus());
        //校验数据是否解析完成
        validateFileData(couponStatusReqDTO.getBatchNo(), couponDO);

        int count = 0;
        Long status = 0L;
        if(couponDO.getBatchStatus() == StatusEnum.BATCH_STATUS_NOT_SENDED.getCode() && couponStatusReqDTO.getBatchStatus() == 1){//改状态为已发放
            UserCouponDO userCouponDO = new UserCouponDO();
            //将用户持券信息表的状态改为已发放状态
            userCouponDO.setStatus(StatusEnum.USER_COUPON_NOT_USED.getCode());
            userCouponDO.setCouponId(couponStatusReqDTO.getCouponId());
            userCouponDO.setUpdateTime(new Date());
            count = userCouponDOMapper.updateStatus(userCouponDO);
            couponStatusReqDTO.setSendTime(new Date());
            int couponStatusUpdateResult = super.updateStatus(couponStatusReqDTO);

            //发送优惠券到账提醒kafka消息
            if(count > 0 && couponStatusUpdateResult > 0){
                asyncInsertCouponService.batchAsyncSendCouponToAcctMq(couponDO, couponStatusReqDTO.getSendTime());
            }
        }else{//改状态为作废
            //修改主表状态为已作废
            super.updateStatus(couponStatusReqDTO);
            //修改明细表为已作废
            UserCouponDO userCouponDO = new UserCouponDO();
            userCouponDO.setStatus(StatusEnum.USER_COUPON_INVLALID.getCode());
            userCouponDO.setCouponId(couponStatusReqDTO.getCouponId());
            userCouponDO.setUpdateTime(new Date());
            count = userCouponDOMapper.updateUserCouponInvalidByCouponId(userCouponDO);
            status = 3l;
        }
        if(count > 0){
            asyncInsertCouponService.batchAnsySendMq(couponStatusReqDTO.getBatchNo(),status);
        }
        return count;
    }

    @Override
    @Transactional
    public int updateExpireCoupon(Date date) {
        List<String> list = couponDOMapper.selectExpireBatchNos(DateUtil.offsetDay(date,-7),date);
        log.info("查询指定商品免费兑换券过期批次号数量：{}", CollectionUtil.isEmpty(list) ? 0 : list.size());
        if(CollectionUtil.isEmpty(list)) return 0;
        int count = couponDOMapper.updateExpireCoupon(DateUtil.offsetDay(date,-7),date);
        log.info("修改积分兑换券主表过期指定商品免费兑换券数量：{}",count);
        count = userCouponDOMapper.updateProductCouponExpireStatus(list);
        log.info("过期指定商品免费兑换券用户过期券数量:{}",count);
        list.stream().forEach(obj->{
            asyncInsertCouponService.batchAnsySendMq(obj,2L);
        });
        return count;
    }

    /**
     * 指定商品免费兑换券详情
     * @param couponDetailQueryReqDTO   优惠券信息详情请求参数DTO
     * @return  指定商品免费兑换券信息
     */
    @Override
    public CouponResDTO detail(CouponDetailQueryReqDTO couponDetailQueryReqDTO) {
        return new CouponResDTO();
    }

    /**
     * 导出使用明细
     * @param batchNo   批次号
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
        downloadDO.setFileType(couponType.equals(2L) ? 4L : 2L);
        downloadDO.setStatus(0L);
        umsAdminService.injectUserValue(downloadDO);
        asyncDownloadService.checkFileExist(fileName,downloadDO.getCreatedBy());
        downloadDOMapper.insert(downloadDO);
        asyncDownloadService.asyncDownloadProductCoupon(exportUserCouponDOList,downloadDO.getId());
        return CommonResult.success(null);
    }
}

