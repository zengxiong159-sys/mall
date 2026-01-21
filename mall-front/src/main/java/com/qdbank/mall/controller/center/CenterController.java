package com.qdbank.mall.controller.center;

import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.center.CenterService;
import com.qdbank.mall.request.center.CenterLikeReqDTO;
import com.qdbank.mall.response.center.CenterResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "CenterController", description = "配置管理")
@RequestMapping("/openapi/center")
@Slf4j
public class CenterController {
    @Autowired
    private CenterService centerService;
    @ApiOperation("配置列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<CenterResDTO>> list(CenterLikeReqDTO centerLikeReqDTO){
        return CommonResult.success(CommonPage.restPage(centerService.list(centerLikeReqDTO)));
    }
}
