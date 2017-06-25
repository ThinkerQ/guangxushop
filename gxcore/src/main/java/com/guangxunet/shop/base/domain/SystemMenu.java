package com.guangxunet.shop.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 系统菜单
 */
@Setter@Getter
public class SystemMenu extends BaseDomain{

    private String text;

    private String state;

    private Byte checked;

    private String attributes;

    private Long parentId;//父菜单

    private String permission;//

    private List<SystemMenu> children;//子菜单

}