package com.qdbank.mall.controller.area;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.area.AreaService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.response.area.AreaResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AreaController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/21 9:44
 * @Version 1.0
 **/
@RestController
@Api(tags = "AreaController", description = "省市区地址")
@RequestMapping("/area")
@Slf4j
public class AreaController {
    @Autowired
    private AreaService areaService;
    @ApiOperation("省份地址列表")
    @RequestMapping(value = "/provinceList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<AreaResDTO>> provinceList() {
        //TODO 返回父id为0的省份地址
        return CommonResult.success(areaService.selectProvince());
    }

    @ApiOperation("市、区/县列表->主键")
    @RequestMapping(value = "/cityList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<AreaResDTO>> cityList(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        //TODO 查询id=parentId数据
        return CommonResult.success(null);
    }

}
