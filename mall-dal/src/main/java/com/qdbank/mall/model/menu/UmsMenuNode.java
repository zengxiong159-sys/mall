package com.qdbank.mall.model.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 后台菜单节点封装
 * Created by ningyuehuai on 2020/10/4.
 */
@Getter
@Setter
public class UmsMenuNode extends UmsMenuDO {
    @ApiModelProperty(value = "子级菜单")
    private List<UmsMenuNode> children;
}
