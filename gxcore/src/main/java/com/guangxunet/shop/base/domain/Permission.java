package com.guangxunet.shop.base.domain;

import java.util.List;

import com.guangxunet.shop.base.domain.BaseDomain;

import lombok.Getter;
import lombok.Setter;

/**
 * 权限
 * @author chemy 
 *
 */
@Setter@Getter
public class Permission extends BaseDomain{

    private String name;

    private String resource;

}