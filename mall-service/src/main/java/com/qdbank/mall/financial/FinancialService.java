package com.qdbank.mall.financial;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.request.financial.FinancialReqDTO;
import com.qdbank.mall.util.TimeUtil;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface FinancialService<T> {
    /**
     * 导出商城报表文件
     * @param financialReqDTO
     */
    public void export(HttpServletResponse response,FinancialReqDTO financialReqDTO);

    public <T> PageInfo<T> getTradeList(FinancialReqDTO financialReqDTO);

    /**
     * 定时任务将数据入表
     * @param
     */
    @Transactional
    public void insertTradeRecord(Date date);

    static  FinancialReqDTO changeDate(FinancialReqDTO financialReqDTO){
        financialReqDTO.setStartDate(TimeUtil.dateStartChange(DateUtil.offsetDay(financialReqDTO.getStartDate(),-1)));
        financialReqDTO.setEndDate(TimeUtil.dateStartChange(financialReqDTO.getEndDate()));
        return financialReqDTO;
    }


}
