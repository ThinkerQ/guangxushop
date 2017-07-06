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

    private Long periodNumber;

    private Long prizeUserId;

    private Integer needCount;

    private Integer alreadyCount;

    private Integer surplusCount;

    private Long numberId;

    private Long productId;

    private Date createTime;

    private Date prizeTime;

    private Integer status;

}