package com.qdbank.mall.controller.prefecture;

import cn.hutool.core.collection.CollectionUtil;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.prefecture.PrefectureService;
import com.qdbank.mall.replace.ReplaceService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.prefecture.PrefectureLikeQueryReqDTO;
import com.qdbank.mall.request.prefecture.PrefectureReqDTO;
import com.qdbank.mall.request.prefecture.UpdatePrefectureReqDTO;
import com.qdbank.mall.request.prefecture.UpdatePrefectureStatusReqDTO;
import com.qdbank.mall.response.prefecture.PrefectureDetailResDTO;
import com.qdbank.mall.response.prefecture.PrefectureResDTO;
import com.qdbank.mall.response.product.ProductSkuResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName PrefectureController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/11 11:29
 * @Version 1.0
 **/
@RestController
@Api(tags = "PrefectureController", description = "专区配置")
@RequestMapping("/prefecture")
@Slf4j
public class PrefectureController {
    @Autowired
    private PrefectureService prefectureService;
    @Autowired
    private ReplaceService replaceService;
    @ApiOperation("专区列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<PrefectureResDTO>> list(@RequestBody PrefectureLikeQueryReqDTO prefectureLikeQueryReqDTO) {

        return CommonResult.success(CommonPage.restPage(prefectureService.list(prefectureLikeQueryReqDTO)));
    }

    @ApiOperation(value = "新建专区")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Validated @RequestBody PrefectureReqDTO prefectureReqDTO) {
        int count = prefectureService.create(prefectureReqDTO);
        if (count < 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }

    @ApiOperation(value = "编辑专区")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@Validated @RequestBody UpdatePrefectureReqDTO updatePrefectureReqDTO) {
        int count = prefectureService.update(updatePrefectureReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "修改专区状态")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@Validated @RequestBody UpdatePrefectureStatusReqDTO updatePrefectureStatusReqDTO) {
        int count = prefectureService.updateStatus(updatePrefectureStatusReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation("专区详情")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<PrefectureResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        PrefectureDetailResDTO prefectureDetailResDTO = prefectureService.getDetail(commonIDReqDTO.getId());
        if(CollectionUtil.isNotEmpty(prefectureDetailResDTO.getProductInfos())){
            for(ProductSkuResDTO productSkuResDTO : prefectureDetailResDTO.getProductInfos()){
                productSkuResDTO.setMailPicUrl(replaceService.replaceUrl(productSkuResDTO.getMailPicUrl()));
            }
        }
        return CommonResult.success(prefectureDetailResDTO);
    }

    @ApiOperation("活动专区商品列表")
    @RequestMapping(value = "/activityProductList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<PrefectureResDTO> activityProductList(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        return CommonResult.success(prefectureService.activityProductList(commonIDReqDTO.getId()));
    }

}
