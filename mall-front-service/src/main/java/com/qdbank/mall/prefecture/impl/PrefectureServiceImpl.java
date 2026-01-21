package com.qdbank.mall.prefecture.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.dao.prefecture.PrefectureDao;
import com.qdbank.mall.mapper.prefecture.PrefectureDOMapper;
import com.qdbank.mall.mapper.prefecturestockrelation.PrefectureStockRelationDOMapper;
import com.qdbank.mall.mapper.product.ProductDOMapper;
import com.qdbank.mall.mapper.productpicurl.ProductPicUrlDOMapper;
import com.qdbank.mall.model.prefecture.PrefectureDO;
import com.qdbank.mall.model.prefecture.PrefectureDOExample;
import com.qdbank.mall.model.prefecture.PrefectureInfoDO;
import com.qdbank.mall.model.product.ProductInfoDO;
import com.qdbank.mall.model.product.ProductInfoQueryDO;
import com.qdbank.mall.model.product.ProductSkuDO;
import com.qdbank.mall.model.productpicurl.ProductPicUrlDO;
import com.qdbank.mall.model.productpicurl.ProductPicUrlDOExample;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.prefecture.PrefectureService;
import com.qdbank.mall.prefecture.mapper.PrefectureMapper;
import com.qdbank.mall.prefecture.mapper.ProductMapper;
import com.qdbank.mall.prefecture.mapper.ProductSkuMapper;
import com.qdbank.mall.request.prefecture.PrefectureLikeQueryReqDTO;
import com.qdbank.mall.response.prefecture.PrefectureResDTO;
import com.qdbank.mall.response.product.ProductCategoryID;
import com.qdbank.mall.response.product.ProductDetailPictureResDTO;
import com.qdbank.mall.response.product.ProductResDTO;
import com.qdbank.mall.response.product.ProductSkuResDTO;
import com.qdbank.mall.response.productcategory.ProductCategoryResDTO;
import com.qdbank.mall.response.productpicurl.ProductPicUrlResDTO;
import com.qdbank.mall.response.skustock.SkustockResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.util.BsnUtil;
import com.qdbank.mall.util.SpringContextUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @ClassName PrefectureServiceImpl
 * @Description TODO
 * @Author hongjh
 * @Date 2021/3/17 19:41
 * @Version 1.0
 **/
@Service("PrefectureService")
public class PrefectureServiceImpl extends BaseServiceImpl implements PrefectureService{


    @Autowired
   private PrefectureDao prefectureDao;

    @Autowired
    private PrefectureMapper prefectureMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductSkuMapper productSkuMapper;

    @Resource
    private ProductPicUrlDOMapper productPicUrlDOMapper;

    @Override
    public List<PrefectureResDTO> qryPrefectures() {
        Date now = new Date();
        List<PrefectureDO> list =prefectureDao.qryPrefectures(now,1L,1);
        return prefectureMapper.poToDtoList(list);
    }


    @Override
    public List<ProductSkuResDTO> qryPrefectures(PrefectureLikeQueryReqDTO req) {
        List<ProductSkuDO> list = prefectureDao.qryProductsByPrefectureId2(req.getPrefectureId());
        return productSkuMapper.proPoToList(list);
    }

    @Override
    public CommonPage qryPrefecturesPage(PrefectureLikeQueryReqDTO req) {
        Page page = PageHelper.startPage(req.getPageNum(),req.getPageSize());
        List<ProductSkuDO> list = prefectureDao.qryProductsByPrefectureId2(req.getPrefectureId());

        //过滤无用规格
        if(!CollectionUtils.isEmpty(list)){
            for(ProductSkuDO skuDO : list ){
                //已上架处理
                List<SkustockDO> skus =skuDO.getSkustocks();
                if(!CollectionUtils.isEmpty(skus)){
                    List<SkustockDO> newList =  new ArrayList<>();
                    //过滤
                    for(SkustockDO sku : skus){
                        if(sku.getStatus()==0L){
                            newList.add(sku);
                        }
                    }
                    skuDO.setSkustocks(newList);
                }
            }
        }

        List<ProductSkuResDTO> _list=productSkuMapper.proPoToList(list);

        //缓存特别处理
        CommonPage result = new CommonPage();
        result.setTotalPage(page.getPages());
        result.setPageNum(page.getPageNum());
        result.setPageSize(page.getPageSize());
        result.setTotal(page.getTotal());
        result.setList(_list);
        return result;
    }

    /**
     * 暂无对库存处理，存放商品基本信息
     * @param productId
     * @return
     */
    @Override
    public ProductSkuDO qryProductSku(Long productId){
        ProductInfoQueryDO query = new ProductInfoQueryDO();
        query.setProductId(productId);
        //规格上架--过滤由业务觉决定
    //    query.setSkuStatus(0L);
        List<ProductSkuDO> products =prefectureDao.selectProductDetailSkuInfo(query);
        if (!CollectionUtils.isEmpty(products)) {
           return products.get(0);
        }
        return null;
    }

    @Override
    public ProductSkuDO qryProductSku(Long productId, Long productSkuId,boolean checkClose){
        //通过缓存获取
        PrefectureService prefectureService = SpringContextUtils.getBean("PrefectureService",PrefectureService.class);
        ProductSkuDO productSkuDO =prefectureService.qryProductSku(productId);

        //发布状态
       /* if(checkClose){
           Long publishStatus = productSkuDO.getPublishStatus();
           if(!Constant.PUBLISH_STATUS_SHELVES.equals(publishStatus)){
               return null;
           }
        }*/

        //是否过滤下架规格
        if(productSkuDO!=null && !CollectionUtils.isEmpty(productSkuDO.getSkustocks())){
            List<SkustockDO> result = new ArrayList<>();
            List<SkustockDO> list =productSkuDO.getSkustocks();
            if(!CollectionUtils.isEmpty(list)){
                A:for(SkustockDO skustockDO : list){

                //价格兜底
                    skustockDO.setMarketPrice(BsnUtil.convertBigdecimal(skustockDO.getMarketPrice()));
                    skustockDO.setAdvicePrice(BsnUtil.convertBigdecimal(skustockDO.getAdvicePrice()));
                    skustockDO.setProductPrice(BsnUtil.convertBigdecimal(skustockDO.getProductPrice()));
                    skustockDO.setProductCash(BsnUtil.convertBigdecimal(skustockDO.getProductCash()));
                    skustockDO.setProductIntegration(BsnUtil.convertLong(skustockDO.getProductIntegration()));

                    skustockDO.setPromotionPerLimit(BsnUtil.convertLong(skustockDO.getPromotionPerLimit()));
                    skustockDO.setProductStock(BsnUtil.convertLong(skustockDO.getProductStock()));
                    skustockDO.setProductLockStock(BsnUtil.convertLong(skustockDO.getProductLockStock()));
                    skustockDO.setDiscountAmount(BsnUtil.convertBigdecimal(skustockDO.getDiscountAmount()));
                    skustockDO.setSalCount(skustockDO.getProductLockStock());
                    //如果需要过滤，且为下架状态，执行下一条数据
                    if(checkClose && skustockDO.getStatus()!= null && skustockDO.getStatus().equals(1L)){
                        continue A;
                    }

                    //如果是单个规格处理，判断
                    if(productSkuId!=null ){
                        if(skustockDO.getProductSkuId().equals(productSkuId)){
                            //命中后，跳出
                            result.add(skustockDO);
                            break A;
                        }
                        continue A;
                    }

                    // 追加
                    result.add(skustockDO);
                }
                productSkuDO.setSkustocks(result);
            }
        }

        //查询某个规格
      /*  if(productSkuDO!=null && productSkuId!=null){
            //如果查询某个规格，处理
            List<SkustockDO> list =productSkuDO.getSkustocks();
            List<SkustockDO> result = new ArrayList<>();
            if(!CollectionUtils.isEmpty(list)){
                for(SkustockDO skustockDO : list){
                    if(skustockDO.getProductSkuId().equals(productSkuId)){
                        result.add(skustockDO);
                        break;
                    }
                }
                productSkuDO.setSkustocks(result);
            }
        }*/

        return productSkuDO;
    }

    /**
     * 根据专区id获取专区信息和专区关联商品列表
     * @param prefectureId  专区id
     * @return  专区信息和专区关联商品列表
     */
    @Override
    public List<PrefectureInfoDO> qryProductsByPrefectureId(Long prefectureId) {
        return prefectureDao.qryProductsByPrefectureId(prefectureId);
    }


}
