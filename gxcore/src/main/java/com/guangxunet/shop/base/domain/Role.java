package com.guangxunet.shop.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色
 */
@Setter@Getter
public class Role extends BaseDomain{

    private String name;//角色名称

    private String sn;//编号

    //拥有的权限
    private List<Permission> permission = new ArrayList<>();

}