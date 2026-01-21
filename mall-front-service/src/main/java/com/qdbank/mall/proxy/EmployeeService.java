package com.qdbank.mall.proxy;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.qdbank.mall.feign.EarlyBirdFeignService;
import com.qdbank.mall.model.earlybird.Response;
import com.qdbank.mall.model.earlybird.employee.EmployeeInfo;
import com.qdbank.mall.model.earlybird.employee.EmployeeInfoDTO;
import com.qdbank.mall.model.earlybird.employee.EmployeeQueryInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class EmployeeService {

    @Resource
    private EarlyBirdFeignService earlyBirdFeignService;

    //在职员工状态列表
    private final List<String> EMPLOYEE_IN_SERVICE_STATUS= Lists.newArrayList("00114","00111","00112");

    /**
     *  员工信息查询
     * */
    public EmployeeInfo queryEmployeeInfo(String idCode,String mobile){
        EmployeeQueryInfo employeeQueryInfo=new EmployeeQueryInfo();
        employeeQueryInfo.setIdNo(idCode);
        employeeQueryInfo.setMobile(mobile);
        Response<EmployeeInfoDTO> response= earlyBirdFeignService.queryEmployeeInfo(employeeQueryInfo);
        log.info("员工信息查询。 idCde:{},response:{}",idCode, JSON.toJSONString(response));
        if(StringUtils.equalsIgnoreCase(response.getCode(),"0")
                && Objects.nonNull(response.getData())
                && EMPLOYEE_IN_SERVICE_STATUS.contains(response.getData().getEmpsubstsId())){
            EmployeeInfoDTO employeeInfoDTO= response.getData();

            EmployeeInfo employeeInfo=new EmployeeInfo();
            employeeInfo.setName(employeeInfoDTO.getEmpNm());
            employeeInfo.setMobileNo(employeeInfoDTO.getEmpmobileNo());
            employeeInfo.setGender(employeeInfoDTO.getEmpsexId());
            return employeeInfo;
        }

        return null;
    }


}
