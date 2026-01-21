package com.qdbank.mall.feign;

import com.qdbank.mall.model.earlybird.Response;
import com.qdbank.mall.model.earlybird.employee.EmployeeInfoDTO;
import com.qdbank.mall.model.earlybird.employee.EmployeeQueryInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("performance-metric-app")
public interface EarlyBirdFeignService {

    /**
     *  根据身份证号码查询员工信息
     * */
    @RequestMapping(value = "/sr/api/emp/info",method = RequestMethod.POST)
    Response<EmployeeInfoDTO> queryEmployeeInfo(@RequestBody EmployeeQueryInfo employeeQueryInfo);

}
