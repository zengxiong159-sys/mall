package com.qdbank.mall.product;

import com.qdbank.mall.request.product.UpdateProductReqDTO;

/**
 * AsyncProductService
 *
 * @author shaoshihang
 * @date 2021/3/18 15:49
 * @since 1.0.0
 */
public interface AsyncProductService {

    public void deletePicture(String groupName,String filePath);

    public void productDownNoticeMgt(Long productId);
}
