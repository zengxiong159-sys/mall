package com.qdbank.mall.controller.freighttemplate;

import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.freighttemplate.FreightTemplateService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.freighttemplate.*;
import com.qdbank.mall.response.freighttemplate.FreightTemplateResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName FreightTemplateController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/15 15:00
 * @Version 1.0
 **/
@RestController
@Api(tags = "FreightTemplateController", description = "运费模板管理")
@RequestMapping("/freighttemplate")
@Slf4j
public class FreightTemplateController {
    @Autowired
    private FreightTemplateService freightTemplateService;
    @ApiOperation("运费模板列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<FreightTemplateResDTO>> list(@Validated @RequestBody AreaFreightTemplateListReqDTO areaFreightTemplateListReqDTO) {
        return CommonResult.success(CommonPage.restPage(freightTemplateService.list(areaFreightTemplateListReqDTO)));
    }
    @ApiOperation(value = "新建运费模板")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@Validated @RequestBody FreightTemplateReqDTO freightTemplateReqDTO) {
        int count = freightTemplateService.create(freightTemplateReqDTO);
        if (count < 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(count, Constant.NOT_ENCRYPT);
    }

    @ApiOperation(value = "编辑运费模板")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@Validated @RequestBody UpdateFreightTemplateReqDTO updateFreightTemplateReqDTO) {
        int count = freightTemplateService.update(updateFreightTemplateReqDTO);
        if (count > 0) {
            return CommonResult.success(count, Constant.NOT_ENCRYPT);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "修改运费模板状态")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public CommonResult updateStatus(@Validated @RequestBody UpdateFreightStatusReqDTO updateFreightStatusReqDTO) {
        int count = freightTemplateService.updateStatus(updateFreightStatusReqDTO);
        if (count > 0) {
            return CommonResult.success(count, Constant.NOT_ENCRYPT);
        }
        return CommonResult.failed();
    }

    @ApiOperation("运费模板详细信息->模板编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public CommonResult<FreightTemplateResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        return CommonResult.success(freightTemplateService.detail(commonIDReqDTO.getId()));
    }
}
