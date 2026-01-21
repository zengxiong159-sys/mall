package com.qdbank.mall.controller.specificationattribute;

import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.specificationattribute.*;
import com.qdbank.mall.response.merchant.MerchantResDTO;
import com.qdbank.mall.response.specificationattribute.SpecificationattributeResDTO;
import com.qdbank.mall.specificationattribute.SpecificationattributeService;
import com.qdbank.mall.user.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SpecificationattributeController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/15 11:07
 * @Version 1.0
 **/
@RestController
@Api(tags = "SpecificationattributeController", description = "规格管理")
@RequestMapping("/specification")
@Slf4j
public class SpecificationattributeController {
    @Autowired
    private SpecificationattributeService specificationattributeService;
    @Qualifier("cmsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @ApiOperation("规格列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<SpecificationattributeResDTO>> list(@Validated @RequestBody SpecificationattributeListReqDTO specificationattributeListReqDTO) {
        MerchantResDTO merchantDO = umsAdminService.selectUserNameAndMerchantNo();
        specificationattributeListReqDTO.setMerchantNo(merchantDO.getMerchantNo());
        return CommonResult.success(CommonPage.restPage(specificationattributeService.list(specificationattributeListReqDTO)));
    }

    @ApiOperation("规格父级列表")
    @RequestMapping(value = "/parents", method = RequestMethod.POST)
    public CommonResult<List<SpecificationattributeResDTO>> parentlist(@Validated @RequestBody ParentSpecificationattributeReqDTO parentSpecificationattributeReqDTO) {
        MerchantResDTO merchantDO = umsAdminService.selectUserNameAndMerchantNo();
        parentSpecificationattributeReqDTO.setMerchantNo(merchantDO.getMerchantNo());
        return CommonResult.success(specificationattributeService.parentlist(parentSpecificationattributeReqDTO));
    }

    @ApiOperation(value = "新建规格")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@Validated @RequestBody SpecificationattributeReqDTO specificationattributeReqDTO) {
        MerchantResDTO merchantDO = umsAdminService.selectUserNameAndMerchantNo();
        specificationattributeReqDTO.setMerchantNo(merchantDO.getMerchantNo());
        int count =specificationattributeService.create(specificationattributeReqDTO);
        if (count < 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(count, Constant.NOT_ENCRYPT);
    }

    @ApiOperation(value = "编辑规格")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@Validated @RequestBody UpdateSpecificationNameReqDTO updateSpecificationNameReqDTO) {
        MerchantResDTO merchantDO = umsAdminService.selectUserNameAndMerchantNo();
        updateSpecificationNameReqDTO.setMerchantNo(merchantDO.getMerchantNo());
        int count = specificationattributeService.update(updateSpecificationNameReqDTO);
        if (count > 0) {
            return CommonResult.success(count, Constant.NOT_ENCRYPT);
        }
        return CommonResult.failed();
    }
    @ApiOperation(value = "删除规格")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestBody DeleteSpecificationReqDTO deleteSpecificationReqDTO) {
        int count = specificationattributeService.delete(deleteSpecificationReqDTO);
        if (count > 0) {
            return CommonResult.success(count, Constant.NOT_ENCRYPT);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "规格详情->规格编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public CommonResult<SpecificationattributeResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {

        return CommonResult.failed();
    }

    @ApiOperation(value = "创建规格属性")
    @RequestMapping(value = "/createAttribute", method = RequestMethod.POST)
    public CommonResult createAttribute(@Validated @RequestBody SpecificationattributeReqDTO specificationattributeReqDTO) {
        int count =0;
        if (count < 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }



    @ApiOperation("商品规格属性查询->规格属性父级ID")
    @RequestMapping(value = "/parentList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<SpecificationattributeResDTO>> getList(@Validated @RequestBody SpecificationattributeParentListReqDTO specificationattributeParentListReqDTO) {
        MerchantResDTO merchantDO = umsAdminService.selectUserNameAndMerchantNo();
        specificationattributeParentListReqDTO.setMerchantNo(merchantDO.getMerchantNo());
        List<SpecificationattributeResDTO> specificationattributeResDTOS = specificationattributeService.getList(specificationattributeParentListReqDTO);
        return CommonResult.success(specificationattributeResDTOS);
    }
}
