package com.qdbank.mall.orderimport;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.request.orderimport.OrderImportReqDTO;
import org.springframework.transaction.annotation.Transactional;

public interface OrderImportService {
    public CommonResult orderImport(OrderImportReqDTO orderImportReqDTO);
}
