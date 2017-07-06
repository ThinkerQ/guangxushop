package com.guangxunet.shop.base.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 号码表
 */
@Setter@Getter@ToString
public class Number {
    private Long id;

    private Long periodsId;//期数

    private Long userId;//用户

}