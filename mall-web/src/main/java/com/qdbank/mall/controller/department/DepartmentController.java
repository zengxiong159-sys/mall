package com.qdbank.mall.controller.department;

import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.department.DepartmentService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.dept.DeptLikeQueryReqDTO;
import com.qdbank.mall.request.dept.DeptReqDTO;
import com.qdbank.mall.request.dept.UpdateDeptReqDTO;
import com.qdbank.mall.response.dept.DeptResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "DepartmentController", description = "部门管理")
@RequestMapping("/department")
@Slf4j
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @ApiOperation(value = "部门新增")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<DeptResDTO> create(@Validated @RequestBody DeptReqDTO deptReqDTO) {
        int count = departmentService.insert(deptReqDTO);
        if (count < 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }
    @ApiOperation("获取部门信息->部门编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<DeptResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        return CommonResult.success(departmentService.getDepartmentById(commonIDReqDTO.getId()));
    }

    @ApiOperation("修改部门信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody UpdateDeptReqDTO admin) {
        int count = departmentService.update(admin.getId(), admin);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
    @ApiOperation("删除部门信息->部门编号")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        int count = departmentService.deleteDepartmentById(commonIDReqDTO.getId());
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
    @ApiOperation("根据部门名称获取部门列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<DeptResDTO>> list(@RequestBody DeptLikeQueryReqDTO deptLikeQueryReqDTO) {

        return CommonResult.success(CommonPage.restPage(departmentService.list(deptLikeQueryReqDTO)));
    }
}
