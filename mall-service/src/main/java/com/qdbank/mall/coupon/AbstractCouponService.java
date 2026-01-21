package com.qdbank.mall.coupon;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.monitorjbl.xlsx.StreamingReader;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.api.StatusEnum;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.enums.CouponDistributeWayEnum;
import com.qdbank.mall.enums.CouponTypeEnum;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.coupon.CouponDOMapper;
import com.qdbank.mall.mapper.skustock.SkustockDOMapper;
import com.qdbank.mall.mapper.usercoupon.UserCouponDOMapper;
import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.coupon.CouponDOExample;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.model.usercoupon.UserCouponDO;
import com.qdbank.mall.prefecture.PrefectureService;
import com.qdbank.mall.product.ProductService;
import com.qdbank.mall.request.coupon.CouponLikeQueryReqDTO;
import com.qdbank.mall.request.coupon.CouponReqDTO;
import com.qdbank.mall.request.coupon.UpdateCouponStatusReqDTO;
import com.qdbank.mall.request.product.ProductLikeQueryReqDTO;
import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.response.coupon.RelCouponInfo;
import com.qdbank.mall.response.prefecture.PrefectureDetailResDTO;
import com.qdbank.mall.response.product.ProductSkuResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static cn.hutool.core.util.CharUtil.DOT;

@Slf4j
public abstract class AbstractCouponService extends BaseServiceImpl implements CouponService{
    @Autowired
    private CouponDOMapper couponDOMapper;
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserCouponDOMapper userCouponDOMapper;
    @Autowired
    private SkustockDOMapper skustockDOMapper;

    @Autowired
    private AsyncInsertCouponService asyncInsertCouponService;

    @Autowired
    private PrefectureService prefectureService;

    public int create(CouponDO couponDO){
        umsAdminService.injectUserValue(couponDO);
        return couponDOMapper.insert(couponDO);
    }

    public int update(CouponDO couponDO){
        umsAdminService.injectUpdateUserValue(couponDO);
        return couponDOMapper.updateByPrimaryKeySelective(couponDO);
    }

    @Override
    public int updateStatus(UpdateCouponStatusReqDTO couponStatusReqDTO) {
        couponStatusReqDTO.setCouponType(null);//只修改状态
        CouponDO couponDO = new CouponDO();
        BeanUtils.copyProperties(couponStatusReqDTO,couponDO);
        umsAdminService.injectUpdateUserValue(couponDO);
        return couponDOMapper.updateByPrimaryKeySelective(couponDO);
    }
    @Override
    public PageInfo<CouponResDTO> list(CouponLikeQueryReqDTO couponLikeQueryReqDTO) {
        PageHelper.startPage(couponLikeQueryReqDTO.getPageNum(),couponLikeQueryReqDTO.getPageSize());

        /**
         * 优惠券管理(行发):
         *  1.前端传入的券类型为空且发放方式为行发,查询全部类型的批次优惠券
         *  2.前端传入的券类型为1且发放方式为行发,查询指定商品免费兑换券列表
         *  3.前端传入的券类型为2且发放方式为行发,查询指定商品现金兑换券列表
         *  4.前端传入的券类型为3(积分兑换批次券服务枚举定义)且发放方式为行发,查询积分兑换批次券列表
         *  5.前端传入的券类型为4且发放方式为行发,查询指定专区现金优惠券列表
         *
         * 积分兑换券管理(自行兑换):
         *  5.前端传入的券类型为0且发放方式为用户自行兑换,查询积分兑换券列表
         */
        List<CouponDO> couponDOList = couponDOMapper.selectByExample(buildCriteria(couponLikeQueryReqDTO));
        List<CouponResDTO> couponResDTOList = new ArrayList<>();
        for (CouponDO couponDO : couponDOList){
            CouponResDTO couponResDTO = new CouponResDTO();
            BeanUtils.copyProperties(couponDO, couponResDTO);

            //积分兑换券
            if(CouponTypeEnum.INTEGRATION_COUPON.getCode().equals(couponDO.getCouponType())){
                Long distributeWay = couponDO.getDistributeWay();

                //自行兑换
                if(CouponDistributeWayEnum.SELF_EXCHANGE.getCode().equals(distributeWay)) {
                    List<HashMap<BigDecimal, BigDecimal>> countHashMaps = userCouponDOMapper.selectCountByCouponId(couponDO.getCouponId(), false);
                    couponResDTO.setUsedCount(getCouponCountByStatus(countHashMaps, StatusEnum.USER_COUPON_USED.getCode()));
                    couponResDTO.setExpiredCount(getCouponCountByStatus(countHashMaps, StatusEnum.USER_COUPON_EXPIRE.getCode()));
                    couponResDTO.setExchangedCount(userCouponDOMapper.selectExchangeCount(couponDO.getCouponId()));
                }

                //行发积分兑换券
                if(CouponDistributeWayEnum.SYSTEM_DISTRIBUTE.getCode().equals(distributeWay)) {
                    //积分兑换批次券已使用数和已过期数需排除自行兑换的积分兑换批次券类型(直接根据批次号查询即可)
                    List<HashMap<BigDecimal, BigDecimal>> countHashMaps = userCouponDOMapper.selectCountByBatchNo(couponDO.getBatchNo());
                    couponResDTO.setUsedCount(getCouponCountByStatus(countHashMaps, StatusEnum.USER_COUPON_USED.getCode()));
                    couponResDTO.setExpiredCount(getCouponCountByStatus(countHashMaps, StatusEnum.USER_COUPON_EXPIRE.getCode()));

                    //积分兑换批次券列表需额外设置指定积分兑换券信息
                    RelCouponInfo relCouponInfo = new RelCouponInfo();
                    CouponDO relCouponDO = couponDOMapper.selectByPrimaryKey(couponDO.getRelCouponId());
                    BeanUtils.copyProperties(relCouponDO, relCouponInfo);
                    relCouponInfo.setStartTime(couponDO.getStartTime());
                    relCouponInfo.setEndTime(couponDO.getEndTime());
                    couponResDTO.setRelCouponInfo(relCouponInfo);
                }
            }else{
                //指定专区现金优惠券设置专区商品列表
                if(CouponTypeEnum.PREFECTURE_CASH_COUPON.getCode().equals(couponDO.getCouponType())) {
                    try {
                        PrefectureDetailResDTO prefectureProductList = prefectureService.activityProductList(couponDO.getPrefectureId());
                        couponResDTO.setPrefectureDetailResDTO(prefectureProductList);
                    } catch (ApiException e) {
                        log.error("指定专区现金优惠券设置专区商品列表结果出错", e.getErrorCode().getMessage());
                    }
                }

                List<HashMap<BigDecimal, BigDecimal>> countHashMaps = userCouponDOMapper.selectCountByCouponId(couponDO.getCouponId(), true);
                couponResDTO.setUsedCount(getCouponCountByStatus(countHashMaps, StatusEnum.USER_COUPON_USED.getCode()));
                couponResDTO.setExpiredCount(getCouponCountByStatus(countHashMaps, StatusEnum.USER_COUPON_EXPIRE.getCode()));

                SkustockDO skustockDO = skustockDOMapper.selectByPrimaryKey(couponDO.getProductSkuId());
                couponResDTO.setProductPrice(skustockDO == null || skustockDO.getProductPrice() == null ? BigDecimal.ZERO : skustockDO.getProductPrice());
            }
            couponResDTOList.add(couponResDTO);
        }
        return super.getPageInfo(couponDOList,couponResDTOList);
    }

    /**
     * 优惠券列表筛选
     * @param couponLikeQueryReqDTO 优惠券列表筛选参数
     * @return  优惠券信息
     */
    private CouponDOExample buildCriteria(CouponLikeQueryReqDTO couponLikeQueryReqDTO){
        CouponDOExample couponDOExample = new CouponDOExample();
        couponDOExample.setOrderByClause("CREATE_TIME DESC");
        CouponDOExample.Criteria criteria = couponDOExample.createCriteria();
        if(StringUtils.isNotBlank(couponLikeQueryReqDTO.getCouponName())){
            criteria.andCouponNameLike("%"+couponLikeQueryReqDTO.getCouponName()+"%");
        }
        if(StringUtils.isNotBlank(couponLikeQueryReqDTO.getBatchNo())){
            criteria.andBatchNoEqualTo(couponLikeQueryReqDTO.getBatchNo());
        }
        if(couponLikeQueryReqDTO.getCouponType() != null){
            criteria.andCouponTypeEqualTo(couponLikeQueryReqDTO.getCouponType());
        }
        if(couponLikeQueryReqDTO.getProductType() != null){
            criteria.andProductTypeEqualTo(couponLikeQueryReqDTO.getProductType());
        }
        if(couponLikeQueryReqDTO.getStartCreateTime() != null){
            criteria.andCreateTimeGreaterThanOrEqualTo(TimeUtil.dateZeroChange(couponLikeQueryReqDTO.getStartCreateTime()));
        }
        if(couponLikeQueryReqDTO.getEndCreateTime() != null){
            criteria.andCreateTimeLessThanOrEqualTo(TimeUtil.dateEndChange(couponLikeQueryReqDTO.getEndCreateTime()));
        }
        if(couponLikeQueryReqDTO.getBatchStatus() != null){
            criteria.andBatchStatusEqualTo(couponLikeQueryReqDTO.getBatchStatus());
        }
        if(couponLikeQueryReqDTO.getProductStatus() != null){
            criteria.andProductStatusEqualTo(couponLikeQueryReqDTO.getProductStatus());
        }
        if(CouponDistributeWayEnum.getEnumByCode(couponLikeQueryReqDTO.getDistributeWay()) != null) {
            criteria.andCouponDistributeWayEqualTo(couponLikeQueryReqDTO.getDistributeWay());
        }
        return couponDOExample;
    }

    public void checkProduct(CouponDO couponDO){
        ProductLikeQueryReqDTO productLikeQueryReqDTO = new ProductLikeQueryReqDTO();
        productLikeQueryReqDTO.setPublishStatus(StatusEnum.PUBLIST_STATUS_UP.getCode());
        productLikeQueryReqDTO.setProductId(couponDO.getProductId());
        productLikeQueryReqDTO.setProductSkuId(couponDO.getProductSkuId());
        ProductSkuResDTO productSkuResDTO = productService.getProductSkuDetail(productLikeQueryReqDTO);
        if(couponDO.getCouponType() == 2){//指定商品现金券校验商品是否有积分
            if(productSkuResDTO.getSkustocks().get(0).getProductIntegration() != null &&  productSkuResDTO.getSkustocks().get(0).getProductIntegration() > 0){
                throw new ApiException(ResultCode.COUPON_NOT_CASH_PAY);
            }
        }
    }

    public List<String> getFileHeader(){
        List<String> rowHead = CollUtil.newLinkedList("通联客户号", "批次号", "优惠券类型", "券编号",  "券名称","券面值","发放成功时间","当前状态","状态更新时间","关联订单号","关联商品编号","关联商品名称");
        return rowHead;
    }

    /**
     * 检查传入的券Id是否正确
     * @param couponId      券id
     * @return  优惠券信息
     */
    public CouponDO checkCouponId(Long couponId){
        CouponDO couponDO = couponDOMapper.selectByPrimaryKey(couponId);
        if(couponDO == null) {
            throw new ApiException(ResultCode.COUPON_ID_ERROR);
        }
        return couponDO;
    }

    /**
     * 根据用户使用优惠券状态查询用户用户持券信息表使用总数
     * @param list      用户优惠券状态列表
     * @param status    用户优惠券状态
     * @return  对应用户优惠券状态数量
     */
    private Long getCouponCountByStatus(List<HashMap<BigDecimal,BigDecimal>> list, Long status){
        for(HashMap<BigDecimal,BigDecimal>  hashMap : list){
            if(hashMap.get("STATUS").longValue() == status){
                return hashMap.get("QUANTITY").longValue();
            }
        }
        return 0L;
    }

    /**
     * 校验白名单文件
     * @param file  白名单文件
     * @return  客户号
     */
    public List<String> checkFile(MultipartFile file){
        if(file == null) {
            throw new ApiException(ResultCode.COUPON_FILE_EMPTY);
        }
        List<String> readList;
        try {
            checkFileType(file.getOriginalFilename());
            checkFileSize(file.getSize(),5,"M");
            Long start = System.currentTimeMillis();
            readList = getAllCustNo(file);
            log.info("读取文件总耗时:{}",(System.currentTimeMillis() - start));

            if(CollectionUtil.isEmpty(readList)) {
                throw new ApiException(ResultCode.COUPON_FILE_EMPTY);
            }
            if(readList.size() > Constant.FILE_SIZE) {
                throw new ApiException(ResultCode.COUPON_FILE_SIZE_ERROR);
            }
            checkRepeatData(readList);
        }catch (ApiException api){
            throw api;
        }catch (Exception e){
            log.error("校验上传文件异常："+e);
            throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
        }
        return readList;
    }

    /**
     * 读取白名单文件客户号
     * @param file  白名单文件
     * @return  客户号名单
     */
    private List<String> getAllCustNo(MultipartFile file) {
        Workbook wk = null;
        try {
            wk = StreamingReader.builder()
            //缓存到内存中的行数，默认是10
            .rowCacheSize(100)
            //读取资源时，缓存到内存的字节大小，默认是1024
            .bufferSize(4096)
            //打开资源，必须，可以是InputStream或者是File，注意：只能打开xlsx格式的文件
            .open(file.getInputStream());

            //读取第一个sheet
            Sheet sheet = wk.getSheetAt(0);
            if(sheet != null) {
                //遍历所有的行
                List<String> custNoList = new ArrayList<>(sheet.getLastRowNum());
                for (Row row : sheet) {
                    //遍历所有的列
                    if(row.getRowNum() != 0 && row.getCell(0) != null) {
                        custNoList.add(row.getCell(0).getStringCellValue());
                    }
                }
                return custNoList;
            }
        } catch (Exception e) {
            log.error("读取文件客户号异常", e);
            throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
        } finally {
            try {
                if(wk != null) {
                    wk.close();
                }
            } catch (IOException e) {
                log.error("文件流关闭异常", e);
                throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
            }
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 校验文件类型
     *
     * @param fileName  文件名
     */
    public void checkFileType(String fileName){
        if(StringUtils.isBlank(fileName)) {
            throw new ApiException(ResultCode.COUPON_FILE_TYPE_ERROR);
        }
        String fileEndName = fileName.substring(fileName.lastIndexOf(DOT));
        if(StringUtils.isNotBlank(fileEndName)){
            if(!fileEndName.endsWith(".xlsx")) {
                throw new ApiException(ResultCode.COUPON_FILE_TYPE_ERROR);
            }
        }else {
            throw new ApiException(ResultCode.COUPON_FILE_TYPE_ERROR);
        }
    }

    /**
     * 检查名单数据是否重复
     *
     * @param list  名单数据
     */
    public void checkRepeatData(List<String> list){
        long count = list.stream().distinct().count();
        if(list.size() != count) {
            throw new ApiException(ResultCode.COUPON_FILE_EXIST_REPEATE_DATE);
        }
    }

    /**
     * 判断文件大小
     *
     * @param len   文件长度
     * @param size  限制大小
     * @param unit  限制单位（B,K,M,G）
     */
    public void checkFileSize(Long len, int size, String unit) {
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            throw new ApiException(ResultCode.COUPON_FILE_OUT_SIZE);
        }
    }

    /**
     * 上传白名单文件
     * @param file  白名单文件
     * @param couponDO  优惠券信息
     */
    public void updateLoadFile(MultipartFile file, CouponDO couponDO){
        try {
            String[] uploadResult = super.uploadFile(file, false);
            couponDO.setFileUrl(uploadResult[2]);
            couponDO.setGroupId(uploadResult[0]);
            couponDO.setFileName(file.getOriginalFilename());

            //默认待发放状态
            couponDO.setBatchStatus(StatusEnum.BATCH_STATUS_NOT_SENDED.getCode());
        }catch (ApiException apiException){
            throw apiException;
        }catch (Exception e){
            log.error("上传白名单文件异常", e);
            throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
        }
    }

    /**
     * 将白名单文件数据异步入用户持券信息表SMS_USER_COUPON
     * @param readList  白名单文件数据
     * @param couponReqDTO  页面传入券信息
     * @param couponDO  券信息
     */
    public void parseExcel(List<String> readList, CouponReqDTO couponReqDTO, CouponDO couponDO){
        try {
            long couponType = couponDO.getCouponType();
            List<UserCouponDO> userCouponDOList = new ArrayList<>();
            for (String custNo : readList) {
                if(!NumberUtil.isNumber(custNo)) {
                    throw new ApiException(ResultCode.CUST_NO_ERROR);
                }
                UserCouponDO userCouponDO = new UserCouponDO();
                BeanUtils.copyProperties(couponReqDTO, userCouponDO);
                userCouponDO.setBatchNo(couponDO.getBatchNo());

                //指定商品免费兑换券存入券信息主表主键,积分兑换批次券存入指定积分兑换券id(便于C端直接关联查询到商品id)
                if(CouponTypeEnum.INTEGRATION_COUPON.getCode() == couponType) {
                    userCouponDO.setCouponId(couponDO.getRelCouponId());
                } else {
                    userCouponDO.setCouponId(couponDO.getCouponId());
                }
                userCouponDO.setCreateTime(new Date());
                userCouponDO.setUpdateTime(new Date());
                userCouponDO.setStatus(StatusEnum.USER_COUPON_NOT_SEND.getCode());
                userCouponDO.setCustNo(custNo);
                userCouponDO.setUserCouponId(super.generateId());
                userCouponDO.setCouponName(couponDO.getCouponName());
                userCouponDOList.add(userCouponDO);
            }
            asyncInsertCouponService.batchAnsyInsert(userCouponDOList);
        }catch (ApiException apiException){
            throw apiException;
        }catch (Exception e){
            //发生异常时删除已上传文件
            super.deleteFile(couponDO.getGroupId(), couponDO.getFileUrl());
            log.error("解析白名单文件异常", e);
            throw new ApiException(ResultCode.PARASE_COUPON_FILE_ERROR);
        }
    }

    /**
     * 根据优惠券批次号,批量删除用户持券信息数据
     * @param batchNo   优惠券批次号
     * @return  删除结果
     */
    public int deleteByBathNo(String batchNo){
        Long start = System.currentTimeMillis();
        int count = userCouponDOMapper.deleteByBatchNo(batchNo);
        log.info("删除白名单数据耗时：{}",System.currentTimeMillis() - start);
        return count;
    }

    /**
     * 检查传入的批次券状态是否合法
     * @param oldBatchStatus    原批次状态数据
     * @param newBatchStatus    传入的批次状态
     */
    public void checkBatchStatus(Long oldBatchStatus, Long newBatchStatus){
        //优惠券状态只能被修改为发放(1)、作废(3)或已生效(5)
        if(!StatusEnum.BATCH_STATUS_SENDED.getCode().equals(newBatchStatus)
                && !StatusEnum.BATCH_STATUS_INVLALID.getCode().equals(newBatchStatus)
                && !StatusEnum.BATCH_STATUS_EFFECTIVE.getCode().equals(newBatchStatus)) {
            throw new ApiException(ResultCode.COUPON_BATCH_STATUS_ERROR);
        }

        //修改前后状态相同视为非法
        if((oldBatchStatus.equals(newBatchStatus))) {
            throw new ApiException(ResultCode.COUPON_BATCH_STATUS_ERROR);
        }

        //已过期(2)、已作废(3)、已失效(6)不允许修改状态
        if(StatusEnum.BATCH_STATUS_EXPIRE.getCode().equals(oldBatchStatus)
                || StatusEnum.BATCH_STATUS_INVLALID.getCode().equals(oldBatchStatus)
                || StatusEnum.BATCH_STATUS_INVALIDATION.getCode().equals(oldBatchStatus)) {
            throw new ApiException(ResultCode.COUPON_BATCH_STATUS_EXPIRE);
        }
    }

    /**
     * 检查指定批次券数据是否完整
     * @param batchNo   批次号
     * @param couponDO  券信息
     */
    public void validateFileData(String batchNo, CouponDO couponDO) {
        //检查数据完整性
        Long userCouponCount = userCouponDOMapper.selectCouponCountByBatchNo(batchNo);
        if (userCouponCount == null) {
            throw new ApiException(ResultCode.COUPON_ID_ERROR);
        }
        if (!userCouponCount.equals(couponDO.getAllTotal())) {
            throw new ApiException(ResultCode.COUPON_DATA_PROCESSING);
        }
    }
}
