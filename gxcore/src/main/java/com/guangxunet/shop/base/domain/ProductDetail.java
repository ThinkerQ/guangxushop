package com.guangxunet.shop.base.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 商品详情表
 */
@Setter@Getter@ToString
public class ProductDetail {
    private Long id;

    private Long productId;//商品

    private String path;//详情图片

    private String describe;//描述

}