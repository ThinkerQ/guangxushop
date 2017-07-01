package com.guangxunet.shop.base.domain;

/**
 * 商品品牌实体类
 * @Description: 
 * @author King
 * @date 2017年6月30日
 */
public class Brand {
    private Long id;
    
    private String name;

    private String sn;//品牌编码

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }
}