package com.guangxunet.shop.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guangxunet.shop.business.domain.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 商品实体表
 */
@Setter@Getter@ToString
public class Product extends BaseDomain{

    private String firstName;//主名称

    private String secondName;//副名称

    private String littlePath;//小图路径

    private String bigPath;//大图路径
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date time;//时间

    private Integer status;//状态

    private Category category;//分类

    private Brand brand;//品牌

}