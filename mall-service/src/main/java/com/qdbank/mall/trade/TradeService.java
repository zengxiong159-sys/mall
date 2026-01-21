package com.qdbank.mall.trade;

import java.util.Date;

public interface TradeService {
    /**
     * 通联对账文件数据入库
     * @param date
     */
    public void insertTLFileData(Date date);
}
