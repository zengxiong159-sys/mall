package com.qdbank.mall.integral;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.request.integral.IntegralBalanceReqDTO;
import com.qdbank.mall.request.integral.IntegralQueryReqDTO;
import com.qdbank.mall.response.feign.FeignResponse;
import com.qdbank.mall.response.feign.integral.IntegralDetail;
import com.qdbank.mall.response.feign.integral.IntegralResDTO;
import com.qdbank.mall.response.integral.IntegralBalanceResDTO;

public interface IntegralService {
    /**
     * 根据客户号查询用户积分余额
     * @param integralBalanceReqDTO
     * @return
     */
    public CommonResult<IntegralBalanceResDTO> queryPointBal(IntegralQueryReqDTO integralBalanceReqDTO);

    /**
     * 积分余额原子接口
     * @param integralBalanceReqDTO
     * @return
     */
    public IntegralDetail getPointBal(IntegralBalanceReqDTO integralBalanceReqDTO);

    /**
     * 积分签到
     * @param integralBalanceReqDTO
     * @return
     */
    public CommonResult integralSign(IntegralQueryReqDTO integralBalanceReqDTO);
}
