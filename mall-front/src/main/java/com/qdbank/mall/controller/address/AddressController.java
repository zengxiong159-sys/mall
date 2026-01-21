package com.qdbank.mall.controller.address;

import com.qdbank.mall.address.AddressService;
import com.qdbank.mall.address.mapper.AddressMapper;
import com.qdbank.mall.annotation.CheckUser;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.bo.UserDetails;
import com.qdbank.mall.model.address.AddressDO;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.address.AddressReqDTO;
import com.qdbank.mall.request.address.SetDefaultAddressReqDTO;
import com.qdbank.mall.request.address.UpdateAddressReqDTO;
import com.qdbank.mall.response.address.AddressResDTO;
import com.qdbank.mall.util.UserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AddressController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/19 11:41
 * @Version 1.0
 **/
@RestController
@Api(tags = "AddressController", description = "收货地址管理")
@RequestMapping("/address")
@Slf4j
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    AddressMapper addressMapper;

    @ApiOperation("地址列表->客户号")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<AddressResDTO>> list() {
        UserDetails user  =UserContextHolder.getUserDetails();
        return CommonResult.success(addressService.qryUserAddresss(user.getCustNo()));
    }

    @CheckUser
    @ApiOperation(value = "新增地址")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<AddressResDTO> create(@Validated @RequestBody AddressReqDTO addressReqDTO) {
        AddressDO addressDO  =addressService.createAddress(addressReqDTO);
        return CommonResult.success(addressMapper.poToDTO(addressDO));
    }

    @CheckUser
    @ApiOperation("地址详情->用户地址编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<AddressResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        return CommonResult.success(null);
    }

    @CheckUser
    @ApiOperation(value = "编辑地址")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@Validated @RequestBody UpdateAddressReqDTO updateAddressReqDTO) {
        UserDetails user  =UserContextHolder.getUserDetails();
        return CommonResult.success(addressService.updateUserAdress(user.getCustNo(),updateAddressReqDTO));
    }

    @CheckUser
    @ApiOperation("删除收货地址信息->地址编号")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        UserDetails user  =UserContextHolder.getUserDetails();
        return CommonResult.success(addressService.deleteAddressById(user.getCustNo(),commonIDReqDTO.getId()));
    }

    @CheckUser
    @ApiOperation(value = "设置默认地址")
    @RequestMapping(value = "/setDefaultAddress", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult setDefaultAddress(@Validated @RequestBody SetDefaultAddressReqDTO setDefaultAddressReqDTO) {
        UserDetails user  =UserContextHolder.getUserDetails();
        addressService.setDefaultAddress(user.getCustNo(),setDefaultAddressReqDTO);
        return CommonResult.success(1);
    }
}
