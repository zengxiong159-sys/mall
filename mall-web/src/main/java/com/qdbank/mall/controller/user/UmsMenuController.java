package com.qdbank.mall.controller.user;


import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.model.menu.UmsMenuNode;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.menu.MenuListReqDTO;
import com.qdbank.mall.request.menu.MenuReqDTO;
import com.qdbank.mall.request.menu.UpdateMenuReqDTO;
import com.qdbank.mall.response.menu.MenuResDTO;
import com.qdbank.mall.user.UmsMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台菜单管理Controller
 * Created by ningyuehuai on 2020/10/4.
 */
@Controller
@Api(tags = "UmsMenuDOController", description = "后台菜单管理")
@RequestMapping("/menu")
public class UmsMenuController {

    @Autowired
    private UmsMenuService menuService;

    @ApiOperation("添加后台菜单")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody MenuReqDTO menuReqDTO) {
        int count = menuService.create(menuReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改后台菜单")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody UpdateMenuReqDTO menuReqDTO) {
        int count = menuService.update(menuReqDTO.getId(), menuReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据ID获取菜单详情->订单编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<MenuResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        MenuResDTO UmsMenuDO = menuService.getItem(commonIDReqDTO.getId());
        return CommonResult.success(UmsMenuDO);
    }

    @ApiOperation("删除后台菜单->菜单编号")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        int count = menuService.delete(commonIDReqDTO.getId());
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("分页查询后台菜单")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<MenuResDTO>> list(@RequestBody  MenuListReqDTO menuListReqDTO) {
        return CommonResult.success(CommonPage.restPage( menuService.list(menuListReqDTO.getParentId(), menuListReqDTO.getPageSize(), menuListReqDTO.getPageNum())));
    }

    @ApiOperation("树形结构返回所有菜单列表")
    @RequestMapping(value = "/treeList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<UmsMenuNode>> treeList() {
        List<UmsMenuNode> list = menuService.treeList();
        return CommonResult.success(list, Constant.NOT_ENCRYPT);
    }

    @ApiOperation("修改菜单显示状态")
    @RequestMapping(value = "/updateHidden/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateHidden(@PathVariable Long id, @RequestBody Integer hidden) {
        int count = menuService.updateHidden(id, hidden);
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }
}
