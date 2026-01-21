package com.qdbank.mall.response.oil;

import com.qdbank.mall.response.skustock.SkustockResDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MobileSkuResDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/5/7 13:57
 * @Version 1.0
 **/
@Data
public class UserOilSkuResDTO {




    @ApiModelProperty(value = "历史油卡充值号")
    private String accNo;

    @ApiModelProperty(value = "油卡类型：zsh:中石化  zsy:中石油")
    private String oilType;

    @ApiModelProperty(value = "中石化规格")
    private List<SkustockResDTO> zshSkus;

    @ApiModelProperty(value = "中石油规格")
    private List<SkustockResDTO> zsySkus;







}
