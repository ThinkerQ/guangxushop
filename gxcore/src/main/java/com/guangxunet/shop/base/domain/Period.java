package com.guangxunet.shop.base.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 期数表
 */
@Setter@Getter@ToString
public class Period {
    private Long id;

    private Long periodNumber;//期数号码

    private Long prizeUserId;//获得者

    private Integer needCount;//需要人次

    private Integer alreadyCount;//已投人次

    private Integer surplusCount;//剩余人次

    private Long numberId;//中奖号码表ID

    private Long productId;//商品表ID

    private Date createTime;//创建时间

    private Date prizeTime;//揭晓时间

    private Integer status;//状态:0,正在进行;1,已揭晓

}