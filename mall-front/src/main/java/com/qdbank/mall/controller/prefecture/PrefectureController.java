package com.qdbank.mall.controller.prefecture;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.model.prefecture.PrefectureDO;
import com.qdbank.mall.model.prefecture.PrefectureInfoDO;
import com.qdbank.mall.model.product.ProductSkuDO;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.prefecture.PrefectureService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.prefecture.PrefectureLikeQueryReqDTO;
import com.qdbank.mall.request.prefecture.PrefectureReqDTO;
import com.qdbank.mall.request.prefecture.UpdatePrefectureReqDTO;
import com.qdbank.mall.response.prefecture.PrefectureResDTO;
import com.qdbank.mall.response.product.ProductSkuResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PrefectureController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/11 11:29
 * @Version 1.0
 **/
@RestController
@Api(tags = "PrefectureController", description = "专区查询接口")
@RequestMapping("/prefecture")
@Slf4j
public class PrefectureController {

    @Autowired
    private PrefectureService prefectureService;


    @ApiOperation("专区列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<PrefectureResDTO>> list() {
        List<PrefectureResDTO> list = prefectureService.qryPrefectures();
        return CommonResult.success(list);
    }

    @ApiOperation("专区商品页面查询")
    @RequestMapping(value = "/qryProductsByPreId", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<ProductSkuResDTO>> qryProductsByPreId(@RequestBody PrefectureLikeQueryReqDTO req) {
        CommonPage<ProductSkuResDTO> list= prefectureService.qryPrefecturesPage(req);
        return CommonResult.success(list);
    }





}
