package com.qdbank.mall.controller.paramsconfig;

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
import org.springframework.web.bind.annotation.*;

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
        @ApiOperation(value = "新增参数配置")
        @RequestMapping(value = "/create", method = RequestMethod.POST)
        public CommonResult create(@Validated @RequestBody ParamsConfigReqDTO paramsConfigReqDTO) {
            int count = paramsConfigService.create(paramsConfigReqDTO);
            if (count < 1) {
                return CommonResult.failed();
            }
            return CommonResult.success(null);
        }
        @ApiOperation("参数配置详情->参数配置编号")
        @RequestMapping(value = "/detail", method = RequestMethod.POST)
        public CommonResult<ParamsConfigResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
            return CommonResult.success(paramsConfigService.detail(commonIDReqDTO.getId()));
        }

        @ApiOperation("修改参数配置信息")
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public CommonResult update(@RequestBody UpdateParamsConfigReqDTO updateParamsConfigReqDTO) {
            int count = paramsConfigService.update(updateParamsConfigReqDTO);
            if (count > 0) {
                return CommonResult.success(null);
            }
            return CommonResult.failed();
        }
        @ApiOperation("删除参数配置信息->参数配置编号")
        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public CommonResult delete(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
            int count = paramsConfigService.delete(commonIDReqDTO.getId());
            if (count > 0) {
                return CommonResult.success(null);
            }
            return CommonResult.failed();
        }
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
