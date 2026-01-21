package com.qdbank.mall.prefecture.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.enums.PrefectureStatusEnum;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.coupon.CouponDOMapper;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.mapper.prefecture.PrefectureDOMapper;
import com.qdbank.mall.mapper.prefecturestockrelation.PrefectureStockRelationDOMapper;
import com.qdbank.mall.mapper.product.ProductDOMapper;
import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.coupon.CouponDOExample;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.prefecture.PrefectureDO;
import com.qdbank.mall.model.prefecture.PrefectureDOExample;
import com.qdbank.mall.model.prefecture.PrefectureInfoDO;
import com.qdbank.mall.model.prefecturestockrelation.PrefectureStockRelationDO;
import com.qdbank.mall.model.prefecturestockrelation.PrefectureStockRelationDOExample;
import com.qdbank.mall.model.product.ProductDO;
import com.qdbank.mall.model.product.ProductSkuDO;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.prefecture.PrefectureService;
import com.qdbank.mall.product.ProductService;
import com.qdbank.mall.request.prefecture.PrefectureLikeQueryReqDTO;
import com.qdbank.mall.request.prefecture.PrefectureReqDTO;
import com.qdbank.mall.request.prefecture.UpdatePrefectureReqDTO;
import com.qdbank.mall.request.prefecture.UpdatePrefectureStatusReqDTO;
import com.qdbank.mall.response.prefecture.PrefectureDetailResDTO;
import com.qdbank.mall.response.prefecture.PrefectureResDTO;
import com.qdbank.mall.response.product.ProductSkuResDTO;
import com.qdbank.mall.response.skustock.SkustockResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static cn.hutool.core.collection.IterUtil.isNotEmpty;

/**
 * @ClassName PrefectureServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/1 19:41
 * @Version 1.0
 **/
@Service
@Slf4j
public class PrefectureServiceImpl extends BaseServiceImpl implements PrefectureService{
    @Autowired
    private PrefectureDOMapper prefectureDOMapper;
    @Autowired
    private PrefectureStockRelationDOMapper prefectureStockRelationDOMapper;
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private ProductDOMapper productDOMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDOMapper orderDOMapper;
    @Autowired
    private CouponDOMapper couponDOMapper;
    @Override
    public int create(PrefectureReqDTO prefectureReqDTO) {
        PrefectureDO prefectureDO = new PrefectureDO();
        BeanUtils.copyProperties(prefectureReqDTO,prefectureDO);
        umsAdminService.injectUserValue(prefectureDO);
        prefectureDO.setId(super.generateId());
        prefectureDO.setStatus(0L);
        prefectureDOMapper.insert(prefectureDO);
        List<String> productIds = Arrays.stream(prefectureReqDTO.getIds().split(","))
                .map(s -> s.trim()).collect(Collectors.toList());
        //专区商品建立关联关系
        log.info("专区商品关联关系：{}", JSON.toJSONString(productIds));
        buildRelation(prefectureDO,productIds);
        return 1;
    }

@Override
public PageInfo<PrefectureResDTO> list(PrefectureLikeQueryReqDTO prefectureLikeQueryReqDTO) {
    PageHelper.startPage(prefectureLikeQueryReqDTO.getPageNum(),prefectureLikeQueryReqDTO.getPageSize());
    if(prefectureLikeQueryReqDTO.getStartTime() != null && prefectureLikeQueryReqDTO.getEndTime() != null){
        prefectureLikeQueryReqDTO.setStartTime(TimeUtil.dateZeroChange(prefectureLikeQueryReqDTO.getStartTime()));
        prefectureLikeQueryReqDTO.setEndTime(TimeUtil.dateEndChange(prefectureLikeQueryReqDTO.getEndTime()));
    }

    List<PrefectureDO> prefectureInfoDOList = prefectureDOMapper.selectByExample(buildExample(prefectureLikeQueryReqDTO));
    if(CollectionUtil.isEmpty(prefectureInfoDOList)) return null;
    List<PrefectureResDTO> prefectureResDTOList = new ArrayList<>();
    for(PrefectureDO prefectureInfoDO : prefectureInfoDOList){
        CouponDOExample couponDOExample = new CouponDOExample();
        couponDOExample.createCriteria().andPrefectureIdEqualTo(prefectureInfoDO.getId()).andBatchStatusEqualTo(5L);
        List<CouponDO> couponDOList = couponDOMapper.selectByExample(couponDOExample);
        PrefectureResDTO prefectureResDTO = new PrefectureResDTO();
        PrefectureStockRelationDOExample prefectureStockRelationDOExample = new PrefectureStockRelationDOExample();
        prefectureStockRelationDOExample.createCriteria().andPrefectureIdEqualTo(prefectureInfoDO.getId());
        prefectureResDTO.setProductCount(prefectureStockRelationDOMapper.countByExample(prefectureStockRelationDOExample));
        prefectureResDTO.setHidden(CollUtil.isEmpty(couponDOList) ? "N" : "Y");
        BeanUtils.copyProperties(prefectureInfoDO,prefectureResDTO);
        prefectureResDTOList.add(prefectureResDTO);
    }
    return super.getPageInfo(prefectureInfoDOList,prefectureResDTOList);
}

    @Override
    public int update(UpdatePrefectureReqDTO updatePrefectureReqDTO) {
        PrefectureDO prefectureDO = new PrefectureDO();
        BeanUtils.copyProperties(updatePrefectureReqDTO,prefectureDO);
        PrefectureStockRelationDOExample prefectureStockRelationDOExample = new PrefectureStockRelationDOExample();
        //删除原有关联关系
        prefectureStockRelationDOExample.createCriteria().andPrefectureIdEqualTo(updatePrefectureReqDTO.getId());
        prefectureStockRelationDOMapper.deleteByExample(prefectureStockRelationDOExample);
        umsAdminService.injectUpdateUserValue(prefectureDO);
        prefectureDOMapper.updateByPrimaryKeySelective(prefectureDO);
        buildRelation(prefectureDO, Arrays.stream(updatePrefectureReqDTO.getIds().split(","))
                .map(s -> s.trim()).collect(Collectors.toList()));
        return 1;
    }

    @Override
    public int updateStatus(UpdatePrefectureStatusReqDTO updatePrefectureStatusReqDTO) {
        PrefectureDO prefectureDO = new PrefectureDO();
        BeanUtils.copyProperties(updatePrefectureStatusReqDTO,prefectureDO);
        umsAdminService.injectUpdateUserValue(prefectureDO);
        return prefectureDOMapper.updateStatusByPrimaryKey(prefectureDO);
    }

    @Override
    public PrefectureDetailResDTO getDetail(Long id) {
        PrefectureDO prefectureDO = new PrefectureDO();
        prefectureDO.setId(id);
        PrefectureInfoDO prefectureInfoDO = prefectureDOMapper.selectPrefectureInfo(prefectureDO);
        PrefectureDetailResDTO prefectureResDTO = new PrefectureDetailResDTO();
        if(prefectureInfoDO == null){//商品下架会解除关联关系，只返回专区详情
            PrefectureDO prefectureDO1 = prefectureDOMapper.selectByPrimaryKey(id);
            if(prefectureDO1 != null) {
                BeanUtils.copyProperties(prefectureDO1,prefectureResDTO);
            }
            return prefectureResDTO;
        }

        BeanUtils.copyProperties(prefectureInfoDO,prefectureResDTO);
        List<ProductSkuResDTO>  productInfos = new ArrayList<>();
        for(ProductSkuDO productSkuDO : prefectureInfoDO.getProductSkuDOS()){
            ProductSkuResDTO productResDTO = new ProductSkuResDTO();
            BeanUtils.copyProperties(productSkuDO,productResDTO);
            productResDTO.setProductLevel(productSkuDO.getProductLevel());
            productResDTO.setProductCategoryName(productService.productCategoryName(productResDTO));
            List<SkustockResDTO> skustocks = new ArrayList<>();
            for(SkustockDO skustockDO:productSkuDO.getSkustocks()){
                SkustockResDTO skustockResDTO = new SkustockResDTO();
                if(skustockDO.getProductLockStock() >= 0L){
                    skustockDO.setProductStock(skustockDO.getProductStock() - skustockDO.getProductLockStock());
                }
                OrderDO orderDO = new OrderDO();
                orderDO.setProductId(skustockDO.getProductId());
                orderDO.setProductSkuId(skustockDO.getProductSkuId());
                BeanUtils.copyProperties(skustockDO,skustockResDTO);
                skustockResDTO.setSalCount(orderDOMapper.selectSaleCount(orderDO));
                skustocks.add(skustockResDTO);
            }
            productResDTO.setSkustocks(skustocks);
            productInfos.add(productResDTO);
        }
        prefectureResDTO.setProductInfos(productInfos);
        return prefectureResDTO;
    }

    /**
     * 获取活动专区商品列表
     * @param id    专区id
     * @return  专区商品列表
     */
    @Override
    public PrefectureDetailResDTO activityProductList(Long id) {
        PrefectureDO prefectureDO = prefectureDOMapper.selectByPrimaryKey(id);
        if (prefectureDO == null || PrefectureStatusEnum.DISABLE.getCode().equals(prefectureDO.getStatus())) {
            throw new ApiException(ResultCode.PREFECTURE_ID_ERROR);
        }

        PrefectureDetailResDTO detail = this.getDetail(id);

        //获取关联商品信息
        List<ProductSkuResDTO> productInfoList = detail.getProductInfos();
        if (isNotEmpty(productInfoList)) {
            productInfoList.forEach(productSkuResDTO -> {
                //获取该商品的所有规格属性
                List<SkustockResDTO> skustocks = productSkuResDTO.getSkustocks();
                if (isNotEmpty(skustocks)) {
                    skustocks.forEach(skustock -> {
                        //计算折算价
                        Long productIntegration = skustock.getProductIntegration();
                        BigDecimal exchangePrice = skustock.getProductCash()
                                .add(new BigDecimal(productIntegration).multiply(new BigDecimal("0.01")));
                        skustock.setExchangePrice(exchangePrice);
                    });
                    //同一商品多规格按折算价正序返回
                    skustocks.sort(Comparator.comparing(SkustockResDTO::getExchangePrice));

                    //设置最低折算价
                    productSkuResDTO.setMinExchangePrice(skustocks.get(0).getExchangePrice());
                }
            });

            //按最低折算价排序
            productInfoList.sort(Comparator.comparing(ProductSkuResDTO::getMinExchangePrice));
        }
        return detail;
    }

    private void buildRelation(PrefectureDO prefectureDO,List<String> productIds){
        for(String productId : productIds){
            Long prod = null;
            Long productLevel = null;
            if(productId.contains(":")){//常规专区
                prod = Long.valueOf(productId.split(":")[0]);
                productLevel = Long.valueOf(productId.split(":")[1]);
            }else{//达标专区
                prod = Long.valueOf(productId);
            }

            this.insertPrefectureProduct(prefectureDO,prod,productLevel);
        }
    }
    private PrefectureDOExample buildExample(PrefectureLikeQueryReqDTO prefectureLikeQueryReqDTO){
        PrefectureDOExample prefectureDOExample = new PrefectureDOExample();
        prefectureDOExample.setOrderByClause("CREATE_TIME desc");
        PrefectureDOExample.Criteria criteria = prefectureDOExample.createCriteria();
        if(StringUtils.isNotBlank(prefectureLikeQueryReqDTO.getPrefectureName())){
            criteria.andPrefectureNameLike("%"+prefectureLikeQueryReqDTO.getPrefectureName()+"%");
        }
        if(prefectureLikeQueryReqDTO.getStartTime() != null){
            criteria.andStartTimeGreaterThanOrEqualTo(prefectureLikeQueryReqDTO.getStartTime());
        }
        if(prefectureLikeQueryReqDTO.getEndTime() != null){
            criteria.andStartTimeLessThanOrEqualTo(prefectureLikeQueryReqDTO.getEndTime());
        }
        if(prefectureLikeQueryReqDTO.getStatus() != null){
            criteria.andStatusEqualTo(prefectureLikeQueryReqDTO.getStatus());
        }
        if(prefectureLikeQueryReqDTO.getPrefectureType() != null){
            criteria.andPrefectureTypeEqualTo(prefectureLikeQueryReqDTO.getPrefectureType());
        }
        if(prefectureLikeQueryReqDTO.getPrefectureId() != null){
            criteria.andIdEqualTo(prefectureLikeQueryReqDTO.getPrefectureId());
        }
        return prefectureDOExample;
    }

    private void insertPrefectureProduct(PrefectureDO prefectureDO,Long productId,Long productLevel){
        try {
            ProductDO productDO = productDOMapper.selectByPrimaryKey(productId);
            if(productDO == null) throw  new ApiException(ResultCode.PRODUCT_NO_EXIST);
            if(productDO.getPublishStatus() != 2) throw  new ApiException(ResultCode.PRODUCT_STATUS_ERROR);
            PrefectureStockRelationDO prefectureStockRelationDO = new PrefectureStockRelationDO();
            prefectureStockRelationDO.setId(super.generateId());
            prefectureStockRelationDO.setPrefectureId(prefectureDO.getId());
            prefectureStockRelationDO.setProductId(productId);
            prefectureStockRelationDO.setCreatedBy(prefectureDO.getCreatedBy());
            prefectureStockRelationDO.setUpdatedBy(prefectureDO.getUpdatedBy());
            prefectureStockRelationDO.setCreateTime(new Date());
            prefectureStockRelationDO.setUpdateTime(new Date());
            prefectureStockRelationDO.setProductLevel(productLevel);
            prefectureStockRelationDOMapper.insert(prefectureStockRelationDO);
        }catch (DuplicateKeyException e){
            log.error("插入专区商品绑定关系违反唯一约束异常:{}",e);
        }catch (Exception e){
            log.error("插入专区商品绑定关系异常:{}",e);
        }
    }
}
