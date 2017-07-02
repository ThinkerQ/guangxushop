package com.guangxunet.shop.base.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by chenmy
 */
@Setter
@Getter
public class QueryObject {
    protected Integer page=1;//页数
    protected Integer rows=10;//条数
    //起始数
    public Integer getStart() {
        return (this.page - 1) * this.rows;
    }

}
