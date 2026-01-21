package com.qdbank.mall.prefecture;

import com.github.pagehelper.Page;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.model.prefecture.PrefectureInfoDO;
import com.qdbank.mall.model.product.ProductSkuDO;
import com.qdbank.mall.request.prefecture.PrefectureLikeQueryReqDTO;
import com.qdbank.mall.request.prefecture.PrefectureReqDTO;
import com.qdbank.mall.request.prefecture.UpdatePrefectureReqDTO;
import com.qdbank.mall.request.prefecture.UpdatePrefectureStatusReqDTO;
import com.qdbank.mall.response.prefecture.PrefectureResDTO;
import com.qdbank.mall.response.product.ProductSkuResDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PrefectureService {

    /**
     * 查询专区列表
     * @return
     */
    List<PrefectureResDTO> qryPrefectures();

    /**
     * 查询专区对应商品
     * @param req
     * @return
     */
    List<ProductSkuResDTO> qryPrefectures(PrefectureLikeQueryReqDTO req);

     CommonPage qryPrefecturesPage(PrefectureLikeQueryReqDTO req);

    /**
     * 查询商品详情
     * @param productId
     * @return
     */
    ProductSkuDO qryProductSku(Long productId);

    /**
     *  查询商品信息
     * @param productId
     * @param productSkuId
     * @param checkClose 是否过滤下架
     * @return
     */
    ProductSkuDO qryProductSku(Long productId, Long productSkuId,boolean checkClose);

    /**
     * 根据专区id获取专区信息和专区关联商品列表
     * @param prefectureId  专区id
     * @return  专区信息和专区关联商品列表
     */
    List<PrefectureInfoDO> qryProductsByPrefectureId(Long prefectureId);

}
