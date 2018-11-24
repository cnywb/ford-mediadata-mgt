package com.ford.mediadata.mgt.web.controller.vo;

/**
 * Created by wanglijun on 16/7/9.
 */
public class PageResp {
    /***ID*/
    private Long id;

    /**创建日期*/
    protected  String createdDate;


    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
