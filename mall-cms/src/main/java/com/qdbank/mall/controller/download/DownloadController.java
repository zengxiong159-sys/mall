package com.qdbank.mall.controller.download;

import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.download.DownloadService;
import com.qdbank.mall.request.download.DownloadReqDTO;
import com.qdbank.mall.response.download.DownloadRespDTO;
import com.qdbank.mall.response.merchant.MerchantResDTO;
import com.qdbank.mall.user.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DownloadController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/1/13 14:26
 * @Version 1.0
 **/
@RestController
@Api(tags = "DownloadController", description = "下载管理")
@RequestMapping("/download")
@Slf4j
public class DownloadController {
    @Qualifier("cmsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private DownloadService downloadService;
    @ApiOperation("获取文件下载列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<DownloadRespDTO>> list(@RequestBody DownloadReqDTO downloadReqDTO) {
        MerchantResDTO merchantResDTO = umsAdminService.selectUserNameAndMerchantNo();
        downloadReqDTO.setMerchantNo(merchantResDTO.getMerchantNo());
        return CommonResult.success(CommonPage.restPage(downloadService.downloadList(downloadReqDTO)));
    }
}
