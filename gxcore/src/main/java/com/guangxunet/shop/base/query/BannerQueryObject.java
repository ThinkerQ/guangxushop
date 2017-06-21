package com.guangxunet.shop.base.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by chenmy 2017-06-20
 */
@Setter@Getter
public class BannerQueryObject extends QueryObject {
    private String keyword;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    private Integer state;

}
