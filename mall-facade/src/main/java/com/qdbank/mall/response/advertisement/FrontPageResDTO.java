package com.qdbank.mall.response.advertisement;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName FrontPageResDTO
 * @Description
 * @Author hongjh
 * @Date 2021/1/11 10:08
 * @Version 1.0
 **/
@Data
public class FrontPageResDTO {


    /**
     * 广告模块编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "广告baner")
    private List<AdvertisementResDTO> advertisements;



    @ApiModelProperty(value = "快捷入口")
    private List<AdvertisementResDTO> menus;


    @ApiModelProperty(value = "优惠券数目")
    private Integer couponNum;


    @ApiModelProperty(value = "待收货")
    private Integer stayReceriveNum;


}
