package com.qdbank.mall.product.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSON;
import com.qdbank.mall.mapper.prefecture.PrefectureDOMapper;
import com.qdbank.mall.model.prefecture.PrefectureDO;
import com.qdbank.mall.model.prefecture.PrefectureDOExample;
import com.qdbank.mall.model.prefecturestockrelation.PrefectureStockRelationDO;
import com.qdbank.mall.model.product.*;
import com.qdbank.mall.replace.ReplaceService;
import com.qdbank.mall.response.product.*;
import com.qdbank.mall.service.MqSendService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.api.StatusEnum;
import com.qdbank.mall.api.SystemServiceEnum;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.enums.IdentificationEnum;
import com.qdbank.mall.enums.SkuStockStatusEnum;
import com.qdbank.mall.enums.SpecificationAttributeStatusEnum;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.coupon.CouponDOMapper;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.mapper.prefecturestockrelation.PrefectureStockRelationDOMapper;
import com.qdbank.mall.mapper.product.ProductDOMapper;
import com.qdbank.mall.mapper.productpicurl.ProductPicUrlDOMapper;
import com.qdbank.mall.mapper.productskuattrrelation.ProductSkuAttrRelationMapper;
import com.qdbank.mall.mapper.skustock.SkustockDOMapper;
import com.qdbank.mall.mapper.specificationattribute.SpecificationattributeDOMapper;
import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.coupon.CouponDOExample;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.prefecturestockrelation.PrefectureStockRelationDOExample;
import com.qdbank.mall.model.productpicurl.ProductPicUrlDO;
import com.qdbank.mall.model.productpicurl.ProductPicUrlDOExample;
import com.qdbank.mall.model.productskuattrrelation.ProductSkuAttrRelationDO;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.model.skustock.SkustockDOExample;
import com.qdbank.mall.model.specificationattribute.SpecificationattributeDO;
import com.qdbank.mall.model.specificationattribute.SpecificationattributeDOExample;
import com.qdbank.mall.product.AsyncProductService;
import com.qdbank.mall.product.ProductService;
import com.qdbank.mall.productcategory.ProductcategoryService;
import com.qdbank.mall.request.CommonStringReqDTO;
import com.qdbank.mall.request.product.*;
import com.qdbank.mall.request.productpicurl.ProductDetailPicReqDTO;
import com.qdbank.mall.request.productpicurl.UpLoadPictureReqDTO;
import com.qdbank.mall.request.skustock.SkustockReqDTO;
import com.qdbank.mall.request.specificationattribute.SpecificationExtendReqDTO;
import com.qdbank.mall.response.productcategory.ProductCategoryResDTO;
import com.qdbank.mall.response.productpicurl.ProductPicUrlResDTO;
import com.qdbank.mall.response.productpicurl.UpLoadPictureResDTO;
import com.qdbank.mall.response.skustock.SkustockResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.SpringContextUtils;
import com.qdbank.mall.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import static cn.hutool.core.util.StrUtil.COMMA;
import static cn.hutool.core.util.StrUtil.C_UNDERLINE;
import static com.qdbank.mall.constant.Constant.*;
import static java.math.BigDecimal.ROUND_HALF_UP;
import static java.util.stream.Collectors.toList;

/**
 * @ClassName ProductServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/2 16:58
 * @Version 1.0
 **/
@Service
@Slf4j
@RefreshScope
public class ProductServiceImpl extends BaseServiceImpl implements ProductService {
    @Autowired
    private ProductDOMapper productDOMapper;
    @Resource
    private AsyncProductService asyncProductService;
    @Resource
    private SkustockDOMapper skustockDOMapper;
    @Autowired
    private SpecificationattributeDOMapper specificationattributeDOMapper;
    @Resource
    private ProductPicUrlDOMapper productPicUrlDOMapper;
    @Autowired
    private ProductcategoryService productcategoryService;
    @Autowired
    private OrderDOMapper orderDOMapper;
    @Autowired
    private PrefectureStockRelationDOMapper prefectureStockRelationDOMapper;
    @Autowired
    private CouponDOMapper couponDOMapper;
    @Autowired
    private ProductSkuAttrRelationMapper productSkuAttrRelationMapper;
    @Autowired
    private PrefectureDOMapper prefectureDOMapper;
    @Value(value = "${bsn.products.categegoty.cid1}")
    private String virsualProduct;
    @Autowired
    private ReplaceService replaceService;
    public PageInfo<ProductResDTO> list(ProductLikeQueryReqDTO productLikeQueryReqDTO) {
        PageHelper.startPage(productLikeQueryReqDTO.getPageNum(),productLikeQueryReqDTO.getPageSize());
        ProductInfoQueryDO productInfoQueryDO = new ProductInfoQueryDO();
        if(productLikeQueryReqDTO.getProductCashMin() == null){
            productLikeQueryReqDTO.setProductCashMin(BigDecimal.ZERO);
        }
        if(productLikeQueryReqDTO.getProductCashMax() == null){
            productLikeQueryReqDTO.setProductCashMax(new BigDecimal(10000000));
        }
        BeanUtils.copyProperties(productLikeQueryReqDTO,productInfoQueryDO);

        //过滤掉虚拟产品
        List<String>filterProducts=Arrays.asList(virsualProduct.split(","));
        productInfoQueryDO.setFilterProducts(filterProducts);

        List<ProductInfoDO> productInfoDOList = productDOMapper.selectProductInfo(productInfoQueryDO);
        if(CollUtil.isEmpty(productInfoDOList)) {
            return  super.getPageInfo(new ArrayList<>(),new ArrayList<>());
        }
        List<ProductResDTO> productAllResDTOList = new ArrayList<>();
        Iterator<ProductInfoDO>  itr = productInfoDOList.iterator();
        while (itr.hasNext()){
            ProductInfoDO productInfoDO = itr.next();
            ProductResDTO productResDTO = new ProductResDTO();
            BeanUtils.copyProperties(productInfoDO,productResDTO);
            productResDTO.setProductCategoryName(this.productCategoryName(productResDTO));
            List<SkustockResDTO> skustockResDTOS = new ArrayList<>();
            List<ProductPicUrlResDTO> productPicUrlResDTOS = new ArrayList<>();

            //判断当前商品的规格信息是否异常(被修改或删除),商品对应规格信息全部为下架即为异常
            List<SkustockDO> skustockList = productInfoDO.getSkustocks();
            if(CollectionUtil.isNotEmpty(skustockList)) {
                boolean skuStockExFlag =
                        skustockList.stream().allMatch(skustockDO -> SkuStockStatusEnum.PUT_OFF.getCode().equals(skustockDO.getStatus()));

                if(skuStockExFlag) {
                    productResDTO.setSkustocks(Collections.EMPTY_LIST);
                } else {
                    //过滤出已上架的规格
                    List<SkustockDO> putOnSkusTockList = skustockList.stream()
                            .filter(skusTock-> SkuStockStatusEnum.PUT_ON.getCode().equals(skusTock.getStatus())).collect(Collectors.toList());
                    DecimalFormat decimalFormat = new DecimalFormat("0.00#");
                    putOnSkusTockList.stream().forEach(skustockDO -> {
                        SkustockResDTO skustockResDTO = new SkustockResDTO();
                        if(skustockDO.getProductLockStock() >= 0L){
                            skustockDO.setProductStock(skustockDO.getProductStock() - skustockDO.getProductLockStock());
                        }
                        BeanUtils.copyProperties(skustockDO,skustockResDTO);
                        OrderDO orderDO = new OrderDO();
                        orderDO.setProductId(skustockDO.getProductId());
                        orderDO.setProductSkuId(skustockDO.getProductSkuId());
                        skustockResDTO.setSalCount(orderDOMapper.selectSaleCount(orderDO));
                        skustockResDTO.setProductName(productInfoDO.getProductName());
                        BigDecimal marketPrice = skustockResDTO.getMarketPrice();
                        BigDecimal advicePrice = skustockResDTO.getAdvicePrice();
                        BigDecimal productCash = skustockResDTO.getProductCash();
                        skustockResDTO.setMarketPrice(priceFormat(marketPrice, decimalFormat, ROUND_HALF_UP));
                        skustockResDTO.setAdvicePrice(priceFormat(advicePrice, decimalFormat, ROUND_HALF_UP));
                        skustockResDTO.setProductCash(priceFormat(productCash, decimalFormat, ROUND_HALF_UP));
                        if(productLikeQueryReqDTO.getMerchantNo() == null){
                            skustockResDTO.setSkuPicUrl(replaceService.replaceUrl(skustockResDTO.getSkuPicUrl()));
                        }
                        skustockResDTOS.add(skustockResDTO);
                    });
                }
            }

            for(ProductPicUrlDO productPicUrlDO : productInfoDO.getPicUrls()){
                ProductPicUrlResDTO productPicUrlResDTO = new ProductPicUrlResDTO();
                BeanUtils.copyProperties(productPicUrlDO,productPicUrlResDTO);
                if(productPicUrlDO.getMainFlag() == 0){
                    productResDTO.setMailPicUrl(productLikeQueryReqDTO.getMerchantNo() != null ? productPicUrlDO.getPicUrl() : replaceService.replaceUrl(productPicUrlDO.getPicUrl()));
                }
                if(productLikeQueryReqDTO.getMerchantNo() == null){
                    productPicUrlResDTO.setPicUrl(replaceService.replaceUrl(productPicUrlDO.getPicUrl()));
                }
                productPicUrlResDTOS.add(productPicUrlResDTO);
            }
            if(CollectionUtil.isNotEmpty(skustockResDTOS)){
                productResDTO.setSkustocks(skustockResDTOS.stream().sorted((x,y) -> y.getCreateTime().compareTo(x.getCreateTime())).collect(toList()));
            }else{
                if(SystemServiceEnum.UMS_ADMIN_SERVICE.getSystem().equals(productLikeQueryReqDTO.getSystem())) {
                    itr.remove();
                    continue;
                }
            }
            if(productLikeQueryReqDTO.getMerchantNo() == null){
                productResDTO.setProductDetail(replaceService.replaceUrl(productResDTO.getProductDetail()));
            }
            productResDTO.setPicUrls(productPicUrlResDTOS);
            productAllResDTOList.add(productResDTO);
        }
        return super.getPageInfo(productInfoDOList,productAllResDTOList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStatus(UpdateProductStatusReqDTO updateProductStatusReqDTO) {
        ProductDO productDO = new ProductDO();
        BeanUtils.copyProperties(updateProductStatusReqDTO,productDO);
        UmsAdminService umsAdminService = SpringContextUtils.getBean(SystemServiceEnum.getServiceName(updateProductStatusReqDTO.getSystem()),UmsAdminService.class);
        umsAdminService.injectUpdateUserValue(productDO);

        //出库操作,将商品上架设置时设置的部分信息(实际售价金额、实际售价积分、折算价、每人每件限购、限购开始时间、限购结束时间、积分是否结算)进行置空处理
        long productId = updateProductStatusReqDTO.getProductId();
        if(StatusEnum.PUBLISH_STATUS_OUT.getCode().equals(updateProductStatusReqDTO.getPublishStatus())) {
            //重置该商品的商品标识为常规商品
            productDO.setIdentification(IdentificationEnum.COMMON_PRODUCT.getIdentification());
            skustockDOMapper.resetProductSkuInfoByProductId(productId);

        }

        if(StatusEnum.PUBLIST_STATUS_IN.getCode().equals(updateProductStatusReqDTO.getPublishStatus())){
            checkCouponProduct(updateProductStatusReqDTO.getProductId());
            //入库操作之前判断规格信息是否异常
            ProductSkuAttrRelationDO productSkuAttrRelationDO = new ProductSkuAttrRelationDO();
            productSkuAttrRelationDO.setProductId(productId);
            List<ProductSkuAttrRelationDO> productSkuAttrRelationDOList = productSkuAttrRelationMapper.select(productSkuAttrRelationDO);
            if(CollectionUtil.isEmpty(productSkuAttrRelationDOList)) {
                throw new ApiException(ResultCode.SPRCIFICATION_ATTRIBUTE_NAME_CHANGED);
            }
            ProductDO oldProductDO = productDOMapper.selectByPrimaryKey(productId);
            if(oldProductDO != null && oldProductDO.getFreightTemplateId() == null){
                throw new ApiException(ResultCode.TEMPLATE_INFO_EXCEPTION);
            }
            //业务要求下架商品需要删除专区关联关系
            PrefectureStockRelationDOExample prefectureStockRelationDOExample = new PrefectureStockRelationDOExample();
            prefectureStockRelationDOExample.createCriteria().andProductIdEqualTo(productId);
            prefectureStockRelationDOMapper.deleteByExample(prefectureStockRelationDOExample);
            //asyncProductService.productDownNoticeMgt(productId);
            //判断专区是否关联商品
            PrefectureDOExample prefectureDOExample = new PrefectureDOExample();
            prefectureDOExample.createCriteria().andStatusEqualTo(1L);
            List<PrefectureDO> prefectureDOS = prefectureDOMapper.selectByExample(prefectureDOExample);
            if(CollectionUtil.isNotEmpty(prefectureDOS)){
                //判断已启用状态专区下商品是否关联商品，如果未关联商品则将专区状态改为停用
                for(PrefectureDO prefectureDO : prefectureDOS){
                    PrefectureStockRelationDOExample prefectures = new PrefectureStockRelationDOExample();
                    prefectures.createCriteria().andPrefectureIdEqualTo(prefectureDO.getId());
                    List<PrefectureStockRelationDO> prefectureStockRelationDOList = prefectureStockRelationDOMapper.selectByExample(prefectures);
                    if(CollectionUtil.isEmpty(prefectureStockRelationDOList)){
                        //未关联商品则将专区状态置为停用
                        PrefectureDO updatePrefectureDO = new PrefectureDO();
                        updatePrefectureDO.setStatus(0L);
                        updatePrefectureDO.setId(prefectureDO.getId());
                        prefectureDOMapper.updateByPrimaryKeySelective(updatePrefectureDO);
                    }
                }



            }
        }
        return productDOMapper.updateByPrimaryKeySelective(productDO);
    }

    @Override
        public ProductResDTO detail(Long id) {
        ProductInfoQueryDO productInfoQueryDO = new ProductInfoQueryDO();
        productInfoQueryDO.setProductId(id);
        ProductInfoDO productDO = productDOMapper.selectProductDetail(productInfoQueryDO);
        ProductResDTO productResDTO = new ProductResDTO();
        BeanUtils.copyProperties(productDO,productResDTO);

        //商品图册
        List<ProductPicUrlResDTO> picUrls = new ArrayList<>();
        ProductPicUrlDOExample productPicUrlDOExample = new ProductPicUrlDOExample();
        productPicUrlDOExample.createCriteria().andProductIdEqualTo(id).andFileSourceEqualTo("1");
        List<ProductPicUrlDO> picUrlDOList = productPicUrlDOMapper.selectByExample(productPicUrlDOExample);
        for (ProductPicUrlDO picUrlDO : picUrlDOList){
            ProductPicUrlResDTO productPicUrlResDTO = new ProductPicUrlResDTO();
            BeanUtils.copyProperties(picUrlDO,productPicUrlResDTO);
            picUrls.add(productPicUrlResDTO);
        }
        productResDTO.setPicUrls(picUrls);

        //规格信息
        DecimalFormat decimalFormat = new DecimalFormat("0.00#");
        List<SkustockResDTO> skustocks = new ArrayList<>();
        for (SkustockDO skustockDO : productDO.getSkustocks()){
            if(STATUS_STOSHELF.equals(skustockDO.getStatus())) {
                continue;
            }

            SkustockResDTO skustockResDTO = new SkustockResDTO();
            BeanUtils.copyProperties(skustockDO,skustockResDTO);

            //设置实际库存
            if(skustockDO.getProductLockStock() >= 0L){
                skustockResDTO.setProductStock(skustockDO.getProductStock() - skustockDO.getProductLockStock());
            }
            ProductPicUrlDOExample skuPicUrlExample = new ProductPicUrlDOExample();
            skuPicUrlExample.createCriteria().andPicUrlEqualTo(skustockDO.getSkuPicUrl());
            List<ProductPicUrlDO> picUrlDOS = productPicUrlDOMapper.selectByExample(skuPicUrlExample);
            if(CollUtil.isNotEmpty(picUrlDOS)){
                skustockResDTO.setSkuPicName(picUrlDOS.get(0).getFileName());
            }

            //市场价和建议售价格式化,保留两位小数,不足两位补0
            BigDecimal marketPrice = skustockResDTO.getMarketPrice();
            BigDecimal advicePrice = skustockResDTO.getAdvicePrice();
            skustockResDTO.setMarketPrice(priceFormat(marketPrice, decimalFormat, ROUND_HALF_UP));
            skustockResDTO.setAdvicePrice(priceFormat(advicePrice, decimalFormat, ROUND_HALF_UP));
            skustocks.add(skustockResDTO);
        }
        productResDTO.setProductCategoryName(this.productCategoryName(productResDTO));
        productResDTO.setSkustocks(skustocks);
        productResDTO.setDetailPicUrls(this.getDetailPicUrls(id));

        //设置商品规格异常标识 Y:异常 N:正常
        ProductSkuAttrRelationDO productSkuAttrRelationDO = new ProductSkuAttrRelationDO();
        productSkuAttrRelationDO.setProductId(id);
        List<ProductSkuAttrRelationDO> productSkuAttrRelationDOList = productSkuAttrRelationMapper.select(productSkuAttrRelationDO);
        productResDTO.setAttributeExceptionFlag(CollectionUtil.isEmpty(productSkuAttrRelationDOList) ? "Y" : "N");
        return productResDTO;
    }

    @Override
    public int upProduct(CommonStringReqDTO commonStringReqDTO) {
        List<SkustockDO> list = JsonUtil.json2List(commonStringReqDTO.getJsonStr(),SkustockDO.class);
        if(CollectionUtil.isEmpty(list)) return 0;
        UmsAdminService umsAdminService = SpringContextUtils.getBean(SystemServiceEnum.UMS_ADMIN_SERVICE.getServiceName(),UmsAdminService.class);
        for(SkustockDO skustockDO : list){
            umsAdminService.injectUpdateUserValue(skustockDO);
            if(skustockDO.getProductIntegration()==null){
                skustockDO.setProductIntegration(0L);
            }
            skustockDO.setProductStock(null);
            skustockDO.setProductLockStock(null);
            skustockDO.setSkuPicUrl(null);
            BigDecimal productCash = skustockDO.getProductCash() == null ? BigDecimal.ZERO : skustockDO.getProductCash();
            skustockDO.setProductCash(productCash);
            skustockDO.setProductPrice(productCash.add(skustockDO.getProductIntegration() != null ? BigDecimal.valueOf(skustockDO.getProductIntegration()).divide(new BigDecimal(100)) : BigDecimal.ZERO));

            //校验最多抵扣积分
            Long maxIntegralDeduct = skustockDO.getMaxIntegralDeduct();
            if(maxIntegralDeduct != null) {
                //最多积分抵扣积分对应金额
                BigDecimal maxIntegralDeductAmount = BigDecimal.valueOf(maxIntegralDeduct).divide(new BigDecimal(100));
                //最多积分抵扣积分对应金额 > 折算价,提示积分最多可抵扣量超出限制,请核对!
                if(maxIntegralDeductAmount.compareTo(skustockDO.getProductPrice()) == 1) {
                    throw new ApiException(ResultCode.MAX_INTEGRAL_DEDUCT_LIMIT);
                }
            }
            skustockDOMapper.updateByPrimaryKeySelective(skustockDO);
        }
        ProductDO productDO = new ProductDO();
        productDO.setProductId(list.get(0).getProductId());
        umsAdminService.injectUpdateUserValue(productDO);
        productDO.setIdentification(commonStringReqDTO.getIdentification());
        productDOMapper.updateByPrimaryKeySelective(productDO);
        return 1;
    }

    @Override
    public List<SkustockResDTO> skustockList(Long productId) {
        SkustockDOExample skustockDOExample = new SkustockDOExample();
        skustockDOExample.createCriteria().andProductIdEqualTo(productId).andStatusEqualTo(0L);
        List<SkustockDO> list = skustockDOMapper.selectByExample(skustockDOExample);
        List<SkustockResDTO> skustockResDTOS = new ArrayList<>();
        for (SkustockDO skustockDO : list){
            SkustockResDTO skustockResDTO = new SkustockResDTO();
            BeanUtils.copyProperties(skustockDO,skustockResDTO);
            skustockResDTOS.add(skustockResDTO);
        }
        return skustockResDTOS;
    }

    /**
     * 新建商品
     * @param productReqDTO 商品信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(ProductReqDTO productReqDTO) {
        //新增或编辑商品时,存储用户操作的规格属性缓存 Map<attributeName_pid, id>
        Map<String, Long> attributeCacheMap = Maps.newHashMap();
        Long productId = super.generateId();
        //获取商户编号
        ProductDO productDO = new ProductDO();
        spectionAttributeInsert(productReqDTO.getAttributeNames(), productReqDTO.getMerchantNo(), attributeCacheMap);
        //新增规格信息
        insertSkustock(productReqDTO.getSkustocks(),productId, attributeCacheMap);
        //新增商品数据
        return insertProduct(productReqDTO,productId,productDO);
    }

    /**
     * 新增商品数据
     * @param productReqDTO
     * @param productId
     * @return
     */
    public int  insertProduct(ProductReqDTO productReqDTO,Long productId,ProductDO productDO){
        UmsAdminService umsAdminService = SpringContextUtils.getBean(SystemServiceEnum.CMS_ADMIN_SERVICE.getServiceName(),UmsAdminService.class);
        BeanUtils.copyProperties(productReqDTO,productDO);
        productDO.setProductId(productId);
        productDO.setPublishStatus(Constant.PUBLISH_STATUS_STORAGEING);
        for(ProductPictureReqDTO productPictureReqDTO : productReqDTO.getPicUrls()){
            if(productPictureReqDTO.getMainFlag() == 0){
                productDO.setMailPicUrl(productPictureReqDTO.getPicUrl());
                break;
            }
        }
        umsAdminService.injectUserValue(productDO);
        int count = productDOMapper.insert(productDO);
        //关联商品图册url
        updateProductId(productReqDTO.getPicUrls(),productId);
        deleteDetailPic(productReqDTO,productId);
        return count;
    }

    /**
     * 新增规格信息
     * @param skustockReqDTOS  规格信息
     * @param productId 商品id
     * @param attributeCacheMap 规格属性操作临时缓存
     */
    public void insertSkustock(List<SkustockReqDTO> skustockReqDTOS ,Long productId, Map<String, Long> attributeCacheMap){
        UmsAdminService umsAdminService = SpringContextUtils.getBean(SystemServiceEnum.CMS_ADMIN_SERVICE.getServiceName(),UmsAdminService.class);
        for (SkustockReqDTO reqDTO : skustockReqDTOS){
            try {
                SkustockDO skustockDO = new SkustockDO();
                BeanUtils.copyProperties(reqDTO,skustockDO);
                skustockDO.setStatus(STATUS_STORAGEING);
                skustockDO.setProductId(productId);
                skustockDO.setProductSkuId(super.generateId());
                //初始化
                skustockDO.setProductLockStock(0L);
                umsAdminService.injectUserValue(skustockDO);
                skustockDOMapper.insert(skustockDO);

                //规格信息包含pid数据,格式: [{"形状_0":"方角1_133694967865696256"},{"重量_0":"500g_133689280389472256"}]
                String productSpDataIncludePid = skustockDO.getProductSpDataIncludePid();

                //新增之前,先将该商品对应的商品-规格信息-规格属性关联关系全部清空
                ProductSkuAttrRelationDO productSkuAttrRelation = new ProductSkuAttrRelationDO();
                productSkuAttrRelation.setProductId(productId);
                productSkuAttrRelationMapper.delete(productSkuAttrRelation);

                if(StringUtil.hasText(productSpDataIncludePid)) {
                    //新增商品-规格信息-规格属性关联数据
                    ProductSkuAttrRelationDO productSkuAttrRelationDO = new ProductSkuAttrRelationDO();
                    productSkuAttrRelationDO.setProductId(productId);
                    productSkuAttrRelationDO.setSkuStockId(skustockDO.getProductSkuId());
                    umsAdminService.injectUserValue(productSkuAttrRelationDO);

                    JSONArray productSpDataIncludePidJsonArr = JSONArray.fromObject(productSpDataIncludePid);
                    productSpDataIncludePidJsonArr.stream().forEach(productSpDataIncludePidTmp -> {
                         JSONObject productSpDataIncludePidJsonObj = (JSONObject)productSpDataIncludePidTmp;
                        Iterator iterator = productSpDataIncludePidJsonObj.keys();
                        while(iterator.hasNext()){
                           String parentAttribute = (String) iterator.next();
                           productSkuAttrRelationDO.setId(super.generateId());
                           productSkuAttrRelationDO.setAttributeId(attributeCacheMap.get(parentAttribute));
                           productSkuAttrRelationMapper.insert(productSkuAttrRelationDO);

                           String childAttribute = productSpDataIncludePidJsonObj.getString(parentAttribute);
                           productSkuAttrRelationDO.setId(super.generateId());
                           productSkuAttrRelationDO.setAttributeId(attributeCacheMap.get(childAttribute));
                           productSkuAttrRelationMapper.insert(productSkuAttrRelationDO);
                        }
                    });
                }
            }catch (Exception e){
                log.error("商品规格信息:{} 入库异常:{}",productId,e);
                throw new ApiException(ResultCode.BUILD_SPECIFICATIONS_MAIN_PICTURE_EXCEPTION);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(UpdateProductReqDTO updateProductReqDTO) {
        ProductDO productDO = new ProductDO();
        BeanUtils.copyProperties(updateProductReqDTO,productDO);
        //设置商品主图
        for(ProductPictureReqDTO productPictureReqDTO : updateProductReqDTO.getPicUrls()){
            if(productPictureReqDTO.getMainFlag() == 0){
                productDO.setMailPicUrl(productPictureReqDTO.getPicUrl());
                break;
            }
        }

        //新增或编辑商品时,存储用户操作的规格属性缓存 Map<attributeName_pid, id>
        Map<String, Long> attributeCacheMap = Maps.newHashMap();
        //规格属性
        spectionAttributeInsert(updateProductReqDTO.getAttributeNames(),updateProductReqDTO.getMerchantNo(), attributeCacheMap);
        //规格信息
        updateSkustock(updateProductReqDTO, attributeCacheMap);
        //修改商品图册
        updateProductPics(updateProductReqDTO);
        //修改商品详情图册
        updateProductDetailPics(updateProductReqDTO);
        //修改商品主信息
        UmsAdminService umsAdminService = SpringContextUtils.getBean(SystemServiceEnum.CMS_ADMIN_SERVICE.getServiceName(),UmsAdminService.class);
        umsAdminService.injectUpdateUserValue(productDO);
        return productDOMapper.updateByPrimaryKeySelective(productDO);
    }

    private void updateProductDetailPics(UpdateProductReqDTO updateProductReqDTO) {
        if(CollUtil.isEmpty(updateProductReqDTO.getDetailPicUrls())) {
            return;
        }
        List<ProductDetailPicReqDTO> productDetailPicReqDTOS = updateProductReqDTO.getDetailPicUrls();
        List<String> updatePicList = productDetailPicReqDTOS.stream().map(productDetailPicReqDTO -> productDetailPicReqDTO.getPicUrl()).collect(toList());
        List<ProductPicUrlDO> productPicUrlDOS = getProductPicUrls(updateProductReqDTO.getProductId(),"2");
        List<String> oldPicList = CollUtil.isNotEmpty(productPicUrlDOS) ? productPicUrlDOS.stream().map(productPicUrlDO -> productPicUrlDO.getPicUrl()).collect(toList()) : new ArrayList<>();
        updateAndDeletePics(updatePicList,oldPicList,updateProductReqDTO.getProductId());
    }

    private void updateProductPics(UpdateProductReqDTO updateProductReqDTO) {
        if(CollUtil.isEmpty(updateProductReqDTO.getPicUrls())) {
            return;
        }
        List<ProductPictureReqDTO> productPictureReqDTOList = updateProductReqDTO.getPicUrls();
        List<String> updatePicList = productPictureReqDTOList.stream().map(productPictureReqDTO -> productPictureReqDTO.getPicUrl()).collect(toList());
        List<ProductPicUrlDO> productPicUrlDOS = getProductPicUrls(updateProductReqDTO.getProductId(),"1");
        List<String> oldPicList = CollUtil.isNotEmpty(productPicUrlDOS) ? productPicUrlDOS.stream().map(productPicUrlDO -> productPicUrlDO.getPicUrl()).collect(toList()) : new ArrayList<>();
        //获取需要新增的图片 进行商品id关联
        updateAndDeletePics(updatePicList,oldPicList,updateProductReqDTO.getProductId());
        //修改商品图册主图信息
        updateProductPicMainFlag(productPictureReqDTOList);
    }

    private void updateProductPicMainFlag(List<ProductPictureReqDTO> productPictureReqDTOList) {
        UmsAdminService umsAdminService = SpringContextUtils.getBean(SystemServiceEnum.CMS_ADMIN_SERVICE.getServiceName(),UmsAdminService.class);
        for(ProductPictureReqDTO productPictureReqDTO : productPictureReqDTOList){
            ProductPicUrlDOExample updatePicUrlDOExample = new ProductPicUrlDOExample();
            ProductPicUrlDO productPicUrlDO = new ProductPicUrlDO();
            productPicUrlDO.setMainFlag(productPictureReqDTO.getMainFlag());
            umsAdminService.injectUpdateUserValue(productPicUrlDO);
            updatePicUrlDOExample.createCriteria().andPicUrlEqualTo(productPictureReqDTO.getPicUrl());
            productPicUrlDOMapper.updateByExampleSelective(productPicUrlDO,updatePicUrlDOExample);
        }
    }

    private  List<ProductPicUrlDO> getProductPicUrls(Long proudctId,String fileSource){
        ProductPicUrlDOExample productPicUrlDOExample = new ProductPicUrlDOExample();
        productPicUrlDOExample.createCriteria().andProductIdEqualTo(proudctId).andFileSourceEqualTo(fileSource);
        List<ProductPicUrlDO> productPicUrlDOS = productPicUrlDOMapper.selectByExample(productPicUrlDOExample);
        return productPicUrlDOS;
    }


    /**
     * 修改和删除图片
     * @param updatePicList
     * @param oldPicList
     * @param productId
     */
    private void updateAndDeletePics(List<String> updatePicList, List<String> oldPicList, Long productId){
        List<String> addPicList = CollUtil.subtractToList(updatePicList,oldPicList);
        UmsAdminService umsAdminService = SpringContextUtils.getBean(SystemServiceEnum.CMS_ADMIN_SERVICE.getServiceName(),UmsAdminService.class);
        for(String picUrl : addPicList){
            ProductPicUrlDO productPicUrlDO = new ProductPicUrlDO();
            productPicUrlDO.setProductId(productId);
            umsAdminService.injectUpdateUserValue(productPicUrlDO);
            ProductPicUrlDOExample updatePicUrlDOExample = new ProductPicUrlDOExample();
            updatePicUrlDOExample.createCriteria().andPicUrlEqualTo(picUrl);
            productPicUrlDOMapper.updateByExampleSelective(productPicUrlDO,updatePicUrlDOExample);
        }
        //获取需要删除的图片url
        List<String> deletePicUrls = CollUtil.subtractToList(oldPicList,updatePicList);
        for(String picUrl : deletePicUrls){
            ProductPicUrlDOExample deletePicUrlDOExample = new ProductPicUrlDOExample();
            deletePicUrlDOExample.createCriteria().andPicUrlEqualTo(picUrl);
            List<ProductPicUrlDO> deletePics = productPicUrlDOMapper.selectByExample(deletePicUrlDOExample);
            for(ProductPicUrlDO productPicUrlDO : deletePics){
                asyncProductService.deletePicture(productPicUrlDO.getGroupId(),productPicUrlDO.getPicUrl());
                productPicUrlDOMapper.deleteByPrimaryKey(productPicUrlDO.getId());
            }
        }
    }

    /**
     * 更新规格信息
     * @param updateProductReqDTO   规格信息
     * @param attributeCacheMap 规格属性操作临时缓存
     */
    public void updateSkustock(UpdateProductReqDTO updateProductReqDTO, Map<String, Long> attributeCacheMap){
        UmsAdminService umsAdminService = SpringContextUtils.getBean(SystemServiceEnum.CMS_ADMIN_SERVICE.getServiceName(),UmsAdminService.class);

        //查询原有商品-规格信息-规格属性关系
        long productId = updateProductReqDTO.getProductId();
        ProductSkuAttrRelationDO productSkuAttrRelationDO = new ProductSkuAttrRelationDO();
        productSkuAttrRelationDO.setProductId(productId);
        List<ProductSkuAttrRelationDO> tempProductSkuAttrRelationDOList = productSkuAttrRelationMapper.select(productSkuAttrRelationDO);

        //先将该商品对应的商品-规格信息-规格属性关联关系全部清空
        productSkuAttrRelationMapper.delete(productSkuAttrRelationDO);

        /***
         *  如果用户重新点击生成规格(更新标识为0),新增商品-规格信息-规格属性关联关系
         *  如果未点击生成规格(更新标识为1),则恢复商品-规格信息-规格属性关联关系
         */
        if(updateProductReqDTO.getUpdateFlag() == 0){
            //先将该商品规格信息全部设置为已下架
            SkustockDO skustockDO = new SkustockDO();
            skustockDO.setStatus(STATUS_STOSHELF);
            skustockDO.setProductId(productId);
            skustockDOMapper.updateByProductId(skustockDO);

            insertSkustock(updateProductReqDTO.getSkustocks(),updateProductReqDTO.getProductId(), attributeCacheMap);
        } else {
            //恢复商品对应的商品-规格信息-规格属性关联关系
            if(CollectionUtil.isNotEmpty(tempProductSkuAttrRelationDOList)) {
                tempProductSkuAttrRelationDOList.stream().forEach(tempProductSkuAttrRelationDO -> {
                    tempProductSkuAttrRelationDO.setId(super.generateId());
                    umsAdminService.injectUpdateUserValue(tempProductSkuAttrRelationDO);
                    productSkuAttrRelationMapper.insert(tempProductSkuAttrRelationDO);
                });
            }
        }

        if(CollUtil.isNotEmpty(updateProductReqDTO.getOldSkustocks())){
            List<SkustockReqDTO> skustockReqDTOList = updateProductReqDTO.getOldSkustocks();
            for (SkustockReqDTO skustockReqDTO : skustockReqDTOList){
                SkustockDO skustock = skustockDOMapper.selectByPrimaryKey(Long.valueOf(skustockReqDTO.getProductSkuId()));
                if(skustock == null) {
                    return;
                }
                SkustockDO skustockDO = new SkustockDO();
                //用户重新点击生成规格
                if(updateProductReqDTO.getUpdateFlag() == 0){
                    skustockDO.setStatus(STATUS_STOSHELF);
                }
                BeanUtils.copyProperties(skustockReqDTO,skustockDO);
                int count = skustockDOMapper.updateByPrimaryKeySelective(skustockDO);
                if(count > 0 && StringUtils.isNotBlank(skustock.getSkuPicUrl()) && !skustock.getSkuPicUrl().equals(skustockReqDTO.getSkuPicUrl())){
                    asyncProductService.deletePicture(skustock.getGroupId(),skustock.getSkuPicUrl());
                    log.info("更新规格:{}规格信息成功，上传旧图片:{}成功",skustockReqDTO.getProductSkuId(),skustock.getSkuPicUrl());
                }
            }
        }
    }

    @Override
    @Transactional
    public UpLoadPictureResDTO uploadPictures(UpLoadPictureReqDTO upLoadPictureReqDTO) {
        UpLoadPictureResDTO resDTO = new UpLoadPictureResDTO();
        Long id = super.generateId();
        try {
            String[] filePath = super.uploadFile(upLoadPictureReqDTO.getFile(), true);
            resDTO.setPicUrl(filePath[2]);
            ProductPicUrlDO productPicUrlDO = new ProductPicUrlDO();
            BeanUtils.copyProperties(resDTO,productPicUrlDO);
            productPicUrlDO.setId(id);
            productPicUrlDO.setGroupId(filePath[0]);
            productPicUrlDO.setPicUrl(filePath[2]);
            if(upLoadPictureReqDTO.getMainFlag() != null){
                productPicUrlDO.setMainFlag(upLoadPictureReqDTO.getMainFlag());
            } else {
                //如果主图标识为空,则默认非主图
                productPicUrlDO.setMainFlag(1L);
            }
            resDTO.setFileName(upLoadPictureReqDTO.getFile().getOriginalFilename());
            productPicUrlDO.setFileSource(upLoadPictureReqDTO.getFileSource());
            productPicUrlDO.setFileName(upLoadPictureReqDTO.getFile().getOriginalFilename());
            UmsAdminService umsAdminService = SpringContextUtils.getBean(SystemServiceEnum.CMS_ADMIN_SERVICE.getServiceName(),UmsAdminService.class);
            umsAdminService.injectUserValue(productPicUrlDO);
            productPicUrlDOMapper.insert(productPicUrlDO);
        } catch (Exception e) {
            log.error("上传图片:{} 异常:{}",upLoadPictureReqDTO,e);
            throw new ApiException(ResultCode.BUILD_SPECIFICATIONS_UPLOAD_PICTURES_EXCEPTION);
        }
        return resDTO;
    }

    @Override
    public PageInfo<ProductSkuResDTO> listProductSkuInfo(ProductLikeQueryReqDTO productLikeQueryReqDTO) {
        ProductInfoQueryDO productInfoQueryDO = new ProductInfoQueryDO();
        BeanUtils.copyProperties(productLikeQueryReqDTO,productInfoQueryDO);
        List<ProductSkuDO> productSkuDOS = productDOMapper.selectProductSkuInfo(productInfoQueryDO);
        List<ProductSkuResDTO> productSkuResDTOList = new ArrayList<>();
        for(ProductSkuDO productSkuDO : productSkuDOS){
            ProductSkuResDTO productSkuResDTO = new ProductSkuResDTO();
            BeanUtils.copyProperties(productSkuDO,productSkuResDTO);
            productSkuResDTO.setProductCategoryName(this.productCategoryName(productSkuResDTO));
            List<SkustockDO> skustocks = productSkuDO.getSkustocks();
            List<SkustockResDTO> skustockResDTOList = new ArrayList<>();
            for(SkustockDO skustockDO : skustocks){
                SkustockResDTO skustockResDTO = new SkustockResDTO();
                BeanUtils.copyProperties(skustockDO,skustockResDTO);
                skustockResDTOList.add(skustockResDTO);
            }
            productSkuResDTO.setSkustocks(skustockResDTOList);
            productSkuResDTOList.add(productSkuResDTO);
        }
        return super.getPageInfo(productSkuDOS,productSkuResDTOList);
    }

    @Override
    public ProductSkuResDTO getProductSkuDetail(ProductLikeQueryReqDTO productLikeQueryReqDTO) {
        ProductInfoQueryDO productInfoQueryDO = new ProductInfoQueryDO();
        productInfoQueryDO.setProductId(productLikeQueryReqDTO.getProductId());
        productInfoQueryDO.setProductSkuId(productLikeQueryReqDTO.getProductSkuId());
        productInfoQueryDO.setPublishStatus(productLikeQueryReqDTO.getPublishStatus());
        List<ProductSkuDO> productSkuDOS = productDOMapper.selectProductSkuInfo(productInfoQueryDO);
        if(CollectionUtil.isEmpty(productSkuDOS)) throw new ApiException(ResultCode.PRODUCT_SKU_STOCK_NOT_EXIST);
        ProductSkuDO productSkuDO = productSkuDOS.get(0);
        ProductSkuResDTO productSkuResDTO = new ProductSkuResDTO();
        BeanUtils.copyProperties(productSkuDO,productSkuResDTO);
        productSkuResDTO.setProductCategoryName(this.productCategoryName(productSkuResDTO));
        List<SkustockDO> skustockDOS = productSkuDO.getSkustocks();
        List<SkustockResDTO> skustockResDTOS = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(skustockDOS)){
            SkustockDO skustockDO = skustockDOS.get(0);
            SkustockResDTO skustockResDTO = new SkustockResDTO();
            BeanUtils.copyProperties(skustockDO,skustockResDTO);
            skustockResDTOS.add(skustockResDTO);
        }
        productSkuResDTO.setSkustocks(skustockResDTOS);
        return productSkuResDTO;
    }
    @Override
    public String productCategoryName(ProductCategoryID productCategoryID){
        if(productCategoryID == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getCategoryName(productCategoryID.getCategoryId1())).append("-")
                .append(getCategoryName(productCategoryID.getCategoryId2()));
        String categoryName3 = getCategoryName(productCategoryID.getCategoryId3());
        if(StringUtils.isNotBlank(categoryName3)){
            stringBuffer.append("-").append(categoryName3);
        }
        String categoryName4 = getCategoryName(productCategoryID.getCategoryId4());
        if(StringUtils.isNotBlank(categoryName4)){
            stringBuffer.append("-").append(categoryName4);
        }
        return stringBuffer.toString();
    }

    private String getCategoryName(Long id){
        if(id == null) {
            return "";
        }
        ProductCategoryResDTO productCategoryResDTO = productcategoryService.detail(id);
        if(productCategoryResDTO != null && StringUtils.isNotBlank(productCategoryResDTO.getCategoryName())) {
            return productCategoryResDTO.getCategoryName();
        }
        return "";
    }

    /**
     * 规格属性操作
     * @param attributeNames 商品规格属性
     * @param merchantNo 商户号
     * @param attributeCacheMap  规格属性操作临时缓存
     */
    private void spectionAttributeInsert(String attributeNames, Long merchantNo, Map<String, Long> attributeCacheMap){
        UmsAdminService umsAdminService = SpringContextUtils.getBean(SystemServiceEnum.CMS_ADMIN_SERVICE.getServiceName(),UmsAdminService.class);
        List<SpecificationExtendReqDTO> list = JsonUtil.json2List(attributeNames, SpecificationExtendReqDTO.class);
        //转换异常
        if(list == null) {
            throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
        }

        if(CollUtil.isNotEmpty(list)){
            for(SpecificationExtendReqDTO specificationExtendReqDTO : list){
                long parentId = specificationExtendReqDTO.getParentId();
                //父级规格属性入缓存
                attributeCacheMap.put(specificationExtendReqDTO.getParentAttributeName() + C_UNDERLINE + 0, parentId);

                String[] attributeNameArr = specificationExtendReqDTO.getAttributeName().split(COMMA);
                if(ArrayUtil.isNotEmpty(attributeNameArr)) {
                    //去重
                    List<String> distinctAttributeNames = Arrays.asList(attributeNameArr).stream().distinct().collect(toList());
                    SpecificationattributeDO specificationattributeDO = new SpecificationattributeDO();
                    specificationattributeDO.setParentId(parentId);
                    specificationattributeDO.setMerchantNo(merchantNo);

                    /**
                     * 新增(或更新)数据之前,先判断当前规格属性对应的所有已上架参数是否超过20个
                     * 将页面填写的属性名与数据库现有属性作差集,差集结果即为需要上架(新增或更新)的属性
                     * 若差集结果数量+现有数据库中数据数量 > 20,则不允许新增
                     */
                    List<String> attributeNamesData = specificationattributeDOMapper.selectChildrenByPid(specificationattributeDO);

                    //取交集,将交集属性数据存入缓存
                    List<String> intersectionAttributeNames = distinctAttributeNames.stream().filter(item -> attributeNamesData.contains(item)).collect(toList());
                    intersectionAttributeNames.stream().forEach(attributeName -> {
                        specificationattributeDO.setAttributeName(attributeName);
                        SpecificationattributeDO existSpecificationAttributeDO =
                                specificationattributeDOMapper.selectAttrByMerchantNoPidAttrName(specificationattributeDO);
                        attributeCacheMap.put(attributeName + C_UNDERLINE + parentId, existSpecificationAttributeDO.getId());
                    });

                    //取差集
                    List<String> reduceAttributeNames = distinctAttributeNames.stream().filter(item -> !attributeNamesData.contains(item)).collect(toList());
                    if(CollectionUtil.isNotEmpty(reduceAttributeNames)) {
                        if(reduceAttributeNames.size() + attributeNamesData.size() > MAXIMUM_SPECIFICATION_ATTRIBUTE){
                            throw new ApiException(ResultCode.SPECIFICATION_ATTRIBUTE_NAMBER);
                        }

                        for(String attributeName: reduceAttributeNames){
                            SpecificationattributeDO specificationattribute = new SpecificationattributeDO();
                            specificationattribute.setParentId(parentId);
                            specificationattribute.setAttributeName(attributeName.trim());
                            specificationattribute.setMerchantNo(merchantNo);

                            SpecificationattributeDOExample specificationattributeDOExample = new SpecificationattributeDOExample();
                            SpecificationattributeDOExample.Criteria criteria = specificationattributeDOExample.createCriteria();
                            criteria.andAttributeNameEqualTo(attributeName.trim());
                            criteria.andMerchantNoEqualTo(merchantNo);
                            criteria.andParentIdEqualTo(parentId);
                            List<SpecificationattributeDO> specificationattributeDOList = specificationattributeDOMapper.selectByExample(specificationattributeDOExample);
                            if(CollUtil.isEmpty(specificationattributeDOList)){
                                umsAdminService.injectUserValue(specificationattribute);
                                specificationattribute.setId(super.generateId());
                                specificationattributeDOMapper.insert(specificationattribute);
                            } else {
                                //若规格属性已经存在,则表明该规格属性之前被下架,直接更新状态为上架
                                SpecificationattributeDO uniqueSpecificationAttribute = specificationattributeDOList.get(0);
                                specificationattribute.setId(uniqueSpecificationAttribute.getId());
                                specificationattribute.setStatus(SpecificationAttributeStatusEnum.PUT_ON.getCode());
                                umsAdminService.injectUpdateUserValue(specificationattribute);
                                specificationattributeDOMapper.updateByPrimaryKeySelective(specificationattribute);
                            }
                            //子级规格属性入缓存
                            attributeCacheMap.put(specificationattribute.getAttributeName() + C_UNDERLINE + parentId, specificationattribute.getId());
                        }
                    }
                }
            }
        } else {
            throw new ApiException(ResultCode.SPRCIFICATION_ATTRIBUTE_NAME_EMPTY);
        }
    }

    /**
     * 商品图册关联商品id
     * @param picUrls
     * @param productId
     */
   private void updateProductId( List<ProductPictureReqDTO> picUrls,Long productId){
       if(CollUtil.isEmpty(picUrls)) {
           return;
       }
       for(ProductPictureReqDTO productPictureReqDTO : picUrls ){
           ProductPicUrlDO productPicUrlDO = new ProductPicUrlDO();
           productPicUrlDO.setProductId(productId);
           if(productPictureReqDTO.getMainFlag() != null){
               productPicUrlDO.setMainFlag(productPictureReqDTO.getMainFlag());
           }
           ProductPicUrlDOExample productPicUrlDOExample = new ProductPicUrlDOExample();
           productPicUrlDOExample.createCriteria().andPicUrlEqualTo(productPictureReqDTO.getPicUrl());
           productPicUrlDOMapper.updateByExampleSelective(productPicUrlDO,productPicUrlDOExample);
       }
       log.info("关联商品图册更新图片成功：{}",picUrls == null ? "0" : picUrls.size());
   }

    /**
     * 删除商品详情图片
     * @param productReqDTO
     */
   private void deleteDetailPic(ProductReqDTO productReqDTO,Long productId){
       if(CollUtil.isEmpty(productReqDTO.getDetailPicUrls())) {
           return;
       }
       for(ProductDetailPicReqDTO productDetailPicReqDTO : productReqDTO.getDetailPicUrls()){
           ProductPicUrlDOExample productPicUrlDOExample = new ProductPicUrlDOExample();
           productPicUrlDOExample.createCriteria().andPicUrlEqualTo(productDetailPicReqDTO.getPicUrl());
           if(productReqDTO.getProductDetail().indexOf(productDetailPicReqDTO.getPicUrl()) == -1){
               List<ProductPicUrlDO> productPicUrlDOS = productPicUrlDOMapper.selectByExample(productPicUrlDOExample);
               if(CollUtil.isNotEmpty(productPicUrlDOS)){
                   for(ProductPicUrlDO productPicUrlDO : productPicUrlDOS){
                       asyncProductService.deletePicture(productPicUrlDO.getGroupId(),productPicUrlDO.getPicUrl());
                       productPicUrlDOMapper.deleteByPrimaryKey(productPicUrlDO.getId());
                   }
               }
           }else{//存在则关联商品ID
               ProductPicUrlDO productPicUrlDO = new ProductPicUrlDO();
               productPicUrlDO.setProductId(productId);
               productPicUrlDOMapper.updateByExampleSelective(productPicUrlDO,productPicUrlDOExample);
           }
       }
   }

   private  List<ProductDetailPictureResDTO> getDetailPicUrls(Long id){
       ProductPicUrlDOExample productPicUrlDOExample = new ProductPicUrlDOExample();
       productPicUrlDOExample.createCriteria().andProductIdEqualTo(id).andFileSourceEqualTo("2");
       List<ProductPicUrlDO> productPicUrlDOS = productPicUrlDOMapper.selectByExample(productPicUrlDOExample);
       List<ProductDetailPictureResDTO> productDetailPictureResDTOS = new ArrayList<>();
       for(ProductPicUrlDO productPicUrlDO : productPicUrlDOS){
           ProductDetailPictureResDTO productDetailPictureResDTO = new ProductDetailPictureResDTO();
           productDetailPictureResDTO.setPicUrl(productPicUrlDO.getPicUrl());
           productDetailPictureResDTOS.add(productDetailPictureResDTO);
       }
       return productDetailPictureResDTOS;
   }

    /**
     * 按照指定格式,格式化
     * @param price 价格
     * @param decimalFormat 格式
     * @param roundingMode  舍入模式
     * @return  格式化结果
     */
   private BigDecimal priceFormat(BigDecimal price, DecimalFormat decimalFormat, int roundingMode) {
       if(price != null) {
           return new BigDecimal(decimalFormat.format(price)).setScale(2, roundingMode);
       } else {
           return null;
       }
   }

    /**
     * 校验指定商品现金券是否关联商品
     * @param productId
     */
   private void checkCouponProduct(Long productId){
       CouponDOExample couponDOExample = new CouponDOExample();
       couponDOExample.createCriteria().andProductIdEqualTo(productId).andCouponTypeEqualTo(2L).andBatchStatusIn(new ArrayList<Long>(){{this.add(0L);this.add(1L);}});
       List<CouponDO> couponDOList = couponDOMapper.selectByExample(couponDOExample);
       if(CollUtil.isNotEmpty(couponDOList)) throw new ApiException(ResultCode.COUPON_RELATION_PRODUCT);
   }


    public PageInfo<ProductResDTO> listNew(ProductLikeQueryReqDTO productLikeQueryReqDTO) {
        PageHelper.startPage(productLikeQueryReqDTO.getPageNum(),productLikeQueryReqDTO.getPageSize());
        ProductInfoQueryDO productInfoQueryDO = new ProductInfoQueryDO();
        BeanUtils.copyProperties(productLikeQueryReqDTO,productInfoQueryDO);
        //过滤掉虚拟产品
        List<String>filterProducts=Arrays.asList(virsualProduct.split(","));
        productInfoQueryDO.setFilterProducts(filterProducts);
        List<ProductDO> productDOList = productDOMapper.selectProductInfoNew(productInfoQueryDO);
        if(CollUtil.isEmpty(productDOList)) {
            return  super.getPageInfo(new ArrayList<>(),new ArrayList<>());
        }
        List<ProductResDTO> productAllResDTOList = new ArrayList<>();
        Iterator<ProductDO>  itr = productDOList.iterator();
        while (itr.hasNext()){
            ProductDO productInfoDO = itr.next();
            ProductResDTO productResDTO = new ProductResDTO();
            BeanUtils.copyProperties(productInfoDO,productResDTO);
            productResDTO.setProductCategoryName(this.productCategoryName(productResDTO));
            List<SkustockResDTO> skustockResDTOS = new ArrayList<>();
            List<ProductPicUrlResDTO> productPicUrlResDTOS = new ArrayList<>();

            //判断当前商品的规格信息是否异常(被修改或删除),商品对应规格信息全部为下架即为异常
            List<SkustockDO> skustockList = skustockDOMapper.selectAllByProductId(productInfoDO.getProductId());
            if(CollectionUtil.isNotEmpty(skustockList)) {
                boolean skuStockExFlag =
                        skustockList.stream().allMatch(skustockDO -> SkuStockStatusEnum.PUT_OFF.getCode().equals(skustockDO.getStatus()));

                if(skuStockExFlag) {
                    productResDTO.setSkustocks(Collections.EMPTY_LIST);
                } else {
                    //过滤出已上架的规格
                    List<SkustockDO> putOnSkusTockList = skustockList.stream()
                            .filter(skusTock-> SkuStockStatusEnum.PUT_ON.getCode().equals(skusTock.getStatus())).collect(Collectors.toList());
                    DecimalFormat decimalFormat = new DecimalFormat("0.00#");
                    Iterator<SkustockDO>  skustockDOIterator = putOnSkusTockList.iterator();
                    while (skustockDOIterator.hasNext()){
                        SkustockDO skustockDO = skustockDOIterator.next();
                        SkustockResDTO skustockResDTO = new SkustockResDTO();
                        if(skustockDO.getProductLockStock() >= 0L){
                            skustockDO.setProductStock(skustockDO.getProductStock() - skustockDO.getProductLockStock());
                        }
                        if(productLikeQueryReqDTO.getProductCashMax() != null && productLikeQueryReqDTO.getProductCashMax().compareTo(BigDecimal.ZERO) > 0 && productLikeQueryReqDTO.getProductCashMin() != null && productLikeQueryReqDTO.getProductCashMin().compareTo(BigDecimal.ZERO) > 0 ){
                            if(skustockDO.getAdvicePrice() != null && skustockDO.getAdvicePrice().compareTo(BigDecimal.ZERO) > 0){
                                if(skustockDO.getAdvicePrice().compareTo(productLikeQueryReqDTO.getProductCashMin()) < 0 || skustockDO.getAdvicePrice().compareTo(productLikeQueryReqDTO.getProductCashMax()) > 0){
                                    skustockDOIterator.remove();
                                    continue;
                                }
                            }
                        }
                        BeanUtils.copyProperties(skustockDO,skustockResDTO);
                        skustockResDTO.setSalCount(skustockDO.getProductLockStock());
                        BigDecimal marketPrice = skustockResDTO.getMarketPrice();
                        BigDecimal advicePrice = skustockResDTO.getAdvicePrice();
                        BigDecimal productCash = skustockResDTO.getProductCash();
                        skustockResDTO.setMarketPrice(priceFormat(marketPrice, decimalFormat, ROUND_HALF_UP));
                        skustockResDTO.setAdvicePrice(priceFormat(advicePrice, decimalFormat, ROUND_HALF_UP));
                        skustockResDTO.setProductCash(priceFormat(productCash, decimalFormat, ROUND_HALF_UP));
                        skustockResDTOS.add(skustockResDTO);
                    }
                }
            }
            ProductPicUrlDOExample productPicUrlDOExample = new ProductPicUrlDOExample();
            productPicUrlDOExample.createCriteria().andProductIdEqualTo(productInfoDO.getProductId()).andFileSourceEqualTo("1");
            List<ProductPicUrlDO> picUrlDOList = productPicUrlDOMapper.selectByExample(productPicUrlDOExample);
            for(ProductPicUrlDO productPicUrlDO : picUrlDOList){
                ProductPicUrlResDTO productPicUrlResDTO = new ProductPicUrlResDTO();
                BeanUtils.copyProperties(productPicUrlDO,productPicUrlResDTO);
                productPicUrlResDTOS.add(productPicUrlResDTO);
                if(productPicUrlDO.getMainFlag() == 0){
                    productResDTO.setMailPicUrl(replaceService.replaceUrl(productPicUrlDO.getPicUrl()));
                }
            }
            if(CollectionUtil.isNotEmpty(skustockResDTOS)){
                productResDTO.setSkustocks(skustockResDTOS.stream().sorted((x,y) -> y.getCreateTime().compareTo(x.getCreateTime())).collect(toList()));
            }else{//无专区数据
                itr.remove();
                continue;
            }
            productResDTO.setPicUrls(productPicUrlResDTOS);
            productAllResDTOList.add(productResDTO);
        }
        return super.getPageInfo(productDOList,productAllResDTOList);
    }

    @Override
    public ProductPublishStatusResDTO getPublishStatus(Long productId) {
       ProductDO productDO = productDOMapper.selectByPrimaryKey(productId);
       if(productDO == null) throw  new ApiException(ResultCode.COUPON_PRODUCT_NOT_EXIST);
       ProductPublishStatusResDTO productPublishStatusResDTO = new ProductPublishStatusResDTO();
        productPublishStatusResDTO.setPublishStatus(productDO.getPublishStatus());
        return productPublishStatusResDTO;
    }

    @Override
    public List<ProductIdsResDTO> queryProductByIds(ProductIdsReqDTO productIdsReqDTO) {
       List<ProductIdsResDTO> productIdsResDTOS = new ArrayList<>();
       String [] ids = productIdsReqDTO.getProductIds().split(",");
        for(String id : ids){
            ProductIdsResDTO productIdsResDTO = new ProductIdsResDTO();
            ProductDO productDO = productDOMapper.selectByPrimaryKey(Long.valueOf(id));
            if(productDO == null) throw  new ApiException(ResultCode.PRODUCT_NO_EXIST);
            productIdsResDTO.setProductName(productDO.getProductName());
            productIdsResDTO.setProductAttr(productDO.getProductAttr());
            productIdsResDTO.setMailPicUrl(productDO.getMailPicUrl());
            List<SkustockDO> skustockDOS = skustockDOMapper.selectAllByProductId(Long.valueOf(id));
            if(CollUtil.isNotEmpty(skustockDOS)){
                SkustockDO skustockDO = skustockDOS.get(0);
                productIdsResDTO.setAdvicePrice(skustockDO.getAdvicePrice());
                productIdsResDTO.setMarketPrice(skustockDO.getMarketPrice());
                productIdsResDTO.setProductPrice(skustockDO.getProductPrice());
                productIdsResDTO.setProductCash(skustockDO.getProductCash());
                productIdsResDTO.setProductIntegration(skustockDO.getProductIntegration());
                productIdsResDTO.setProductName(productDO.getProductName());
            }
            productIdsResDTOS.add(productIdsResDTO);
        }
        return productIdsResDTOS;
    }
}
