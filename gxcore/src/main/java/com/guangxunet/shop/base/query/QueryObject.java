package com.guangxunet.shop.base.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by chenmy
 */
@Setter
@Getter
public class QueryObject {
    protected Integer page;
    protected Integer rows;

    public Integer getStart() {
        return (this.page - 1) * this.rows;
    }

}
