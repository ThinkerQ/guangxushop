package com.guangxunet.shop.gxmgrsite.domain;

import java.util.ArrayList;
import java.util.List;

import com.guangxunet.shop.base.domain.BaseDomain;

import lombok.Getter;
import lombok.Setter;

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