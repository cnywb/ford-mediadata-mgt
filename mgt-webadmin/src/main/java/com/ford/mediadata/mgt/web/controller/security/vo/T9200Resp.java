/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved.
 * Resource.java 2015-11-01 12:06:33
 */
package com.ford.mediadata.mgt.web.controller.security.vo;

/**
 * 描述：分页查询请求返回
 *
 * Created by yangkui on 2015-11-01 12:06:33.
 * @since 1.0
*/
public class T9200Resp {

    private Long id;

    private String resourceCode;

    private String resourceName;

    private String url;

    private String permission;

    private String firstInsert;

    private String type;

    private String parentResourceCode;

    private Boolean isMenu;

    public void setResourceCode (String resourceCode){
        this.resourceCode = resourceCode;
    }

    public String getResourceCode (){
        return this.resourceCode;
    }

    public void setResourceName (String resourceName){
        this.resourceName = resourceName;
    }

    public String getResourceName (){
        return this.resourceName;
    }

    public void setUrl (String url){
        this.url = url;
    }

    public String getUrl (){
        return this.url;
    }

    public void setPermission (String permission){
        this.permission = permission;
    }

    public String getPermission (){
        return this.permission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstInsert() {
        return firstInsert;
    }

    public void setFirstInsert(String firstInsert) {
        this.firstInsert = firstInsert;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentResourceCode() {
        return parentResourceCode;
    }

    public void setParentResourceCode(String parentResourceCode) {
        this.parentResourceCode = parentResourceCode;
    }

    public Boolean getMenu() {
        return isMenu;
    }

    public void setMenu(Boolean menu) {
        isMenu = menu;
    }
}