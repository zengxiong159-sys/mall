package com.qdbank.mall.controller.paramsConfig;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.paramsconfig.ParamsConfigService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.paramsconfig.ParamsConfigReqDTO;
import com.qdbank.mall.request.paramsconfig.UpdateParamsConfigReqDTO;
import com.qdbank.mall.response.paramsconfig.ParamsConfigResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName ParamsConfigController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/1 11:50
 * @Version 1.0
 **/
@RestController
@Api(tags = "ParamsConfigController", description = "系统参数配置")
@RequestMapping("/paramsconfig")
@Slf4j
public class ParamsConfigController {
        @Autowired
        private ParamsConfigService paramsConfigService;

    @ApiOperation("参数配置列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<List<ParamsConfigResDTO>> list() {
        return CommonResult.success(paramsConfigService.list());
    }

    @ApiOperation("获取参数配置码表")
    @RequestMapping(value = "/qryByCode", method = RequestMethod.POST)
    public CommonResult<List<ParamsConfigResDTO>> list(@RequestBody ParamsConfigReqDTO dto) {
        return CommonResult.success(paramsConfigService.qryByType(dto.getParamType()));
    }
}
