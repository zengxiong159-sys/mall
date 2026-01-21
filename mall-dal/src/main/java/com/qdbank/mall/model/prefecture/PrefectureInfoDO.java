package com.qdbank.mall.model.prefecture;

import com.qdbank.mall.model.product.ProductSkuDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName PrefectureInfoDO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/5 15:46
 * @Version 1.0
 **/
@Data
public class PrefectureInfoDO extends PrefectureDO{
    @ApiModelProperty(value = "关联商品")
    List<ProductSkuDO> productSkuDOS;
}
