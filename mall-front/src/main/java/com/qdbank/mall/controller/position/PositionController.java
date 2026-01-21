package com.qdbank.mall.controller.position;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.position.PositionService;
import com.qdbank.mall.response.position.PositionResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "PositionController", description = "金刚位管理")
@RequestMapping("/openapi/position")
@Slf4j
public class PositionController {
    @Autowired
    private PositionService positionService;
    @ApiOperation("配置列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<List<PositionResDTO>> list(){
        return CommonResult.success(positionService.list());
    }
}
