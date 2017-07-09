package com.guangxunet.shop.base.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
@Alias("SystemLog") 
public class Systemlog {
    private Long id;

    private Long opuserId;//操作者

    private Date optime;//操作时间

    private String opip;//IP地址

    private String function;//使用功能

    private String params;//操作参数信息

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOpuserId() {
        return opuserId;
    }

    public void setOpuserId(Long opuserId) {
        this.opuserId = opuserId;
    }

    public Date getOptime() {
        return optime;
    }

    public void setOptime(Date optime) {
        this.optime = optime;
    }

    public String getOpip() {
        return opip;
    }

    public void setOpip(String opip) {
        this.opip = opip == null ? null : opip.trim();
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function == null ? null : function.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }
}