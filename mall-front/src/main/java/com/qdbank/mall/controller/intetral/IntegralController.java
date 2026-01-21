package com.qdbank.mall.controller.intetral;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.integral.IntegralService;
import com.qdbank.mall.request.integral.IntegralBalanceReqDTO;
import com.qdbank.mall.request.integral.IntegralQueryReqDTO;
import com.qdbank.mall.response.integral.IntegralBalanceResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IntegralController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/3/24 17:39
 * @Version 1.0
 **/
@RestController
@RequestMapping("/intetral")
@Slf4j
public class IntegralController {
    @Autowired
    private IntegralService integralService;

    @RequestMapping("/pointbal")
    public CommonResult<IntegralBalanceResDTO> queryPointBal(@RequestBody IntegralQueryReqDTO integralBalanceReqDTO) {
        return integralService.queryPointBal(integralBalanceReqDTO);
    }
    @RequestMapping("/sign")
    public CommonResult integralSign(@RequestBody IntegralQueryReqDTO integralBalanceReqDTO){
        return integralService.integralSign(integralBalanceReqDTO);
    }
}
