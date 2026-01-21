package com.qdbank.mall.controller.userwhite;

import com.qdbank.mall.annotation.ExcludeHandler;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.financial.FinancialService;
import com.qdbank.mall.request.CommonStringIDReqDTO;
import com.qdbank.mall.request.financial.FinancialReqDTO;
import com.qdbank.mall.request.userwhite.*;
import com.qdbank.mall.response.userwhite.UserWhiteResDTO;
import com.qdbank.mall.userwhite.UserWhiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags = "UserWhiteController", description = "用户白名单管理")
@RequestMapping("/openapi/userwhite")
public class UserWhiteController {
    @Autowired
    private UserWhiteService userWhiteService;
    @ApiOperation(value = "白名单上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public CommonResult upload(UserWhiteReqDTO userWhiteReqDTO) {
        userWhiteService.uploadUserName(userWhiteReqDTO.getFile());
        return CommonResult.success(null);
    }

    @ApiOperation(value = "白名单新增")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody @Validated UserWhiteCreateReqDTO userWhiteCreateReqDTO) {
        userWhiteService.create(userWhiteCreateReqDTO);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "白名单列表查询")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<UserWhiteResDTO>> list( @RequestBody  UserWhiteQueryReqDTO userWhiteCreateReqDTO) {
        return CommonResult.success(CommonPage.restPage(userWhiteService.list(userWhiteCreateReqDTO)));
    }

    @ApiOperation(value = "白名单信息修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update( UserWhiteUpdateReqDTO userWhiteUpdateReqDTO) {
        userWhiteService.update(userWhiteUpdateReqDTO);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "白名单明细")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.POST)
    public CommonResult<UserWhiteResDTO> detail(@PathVariable("id")Long id) {
        return CommonResult.success(userWhiteService.detail(id));
    }

    @ApiOperation(value = "白名单批量修改状态")
    @RequestMapping(value = "/batchUpdateStatus", method = RequestMethod.POST)
    public CommonResult batchUpdateStatus(CommonStringIDReqDTO commonStringIDReqDTO){
        userWhiteService.batchUpdatStatus(commonStringIDReqDTO.getId());
        return CommonResult.success(null);
    }

    @ExcludeHandler
    @ApiOperation(value="白名单导出",produces="application/octet-stream")
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void excleTotalDownload(HttpServletResponse response, @Validated @RequestBody  UserWhiteQueryReqDTO userWhiteQueryReqDTO){
        userWhiteService.export(response,userWhiteQueryReqDTO);
    }
    @ApiOperation(value="白名单状态修改")
    @RequestMapping(value = "/updateStatus",method = RequestMethod.POST)
    public CommonResult updateStatus(UpdateStatusReqDTO updateStatusReqDTO){
        int count = userWhiteService.updateStatus(updateStatusReqDTO);
        if(count > 0) return CommonResult.success(null);
        return CommonResult.failed();
    }

}
