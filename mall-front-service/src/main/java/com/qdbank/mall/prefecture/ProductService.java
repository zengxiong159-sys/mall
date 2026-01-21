package com.qdbank.mall.prefecture;

import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.response.product.ProductSkuResDTO;

import java.util.List;

public interface ProductService {


    List<ProductSkuResDTO> listProductSkuInfo(ProductEnum productEnum);
}
