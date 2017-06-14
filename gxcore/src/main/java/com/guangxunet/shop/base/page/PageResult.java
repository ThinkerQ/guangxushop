package com.guangxunet.shop.base.page;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

/**
 * Created by chenmy on 2016/9/13.
 */
@Getter
public class PageResult {
    private Long total;
    private List rows;
    public static final PageResult result = new PageResult(0L, Collections.EMPTY_LIST);

    public PageResult(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }
}
