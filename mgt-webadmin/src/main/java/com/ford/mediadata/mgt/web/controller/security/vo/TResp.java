/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved.
 * Resource.java 2015-11-02 11:56:14
 */
package com.ford.mediadata.mgt.web.controller.security.vo;

/**
 * 描述：根据ID获取对象返回
 *
 * Created by yangkui on 2015-11-02 11:56:14.
 * @since 1.0
*/
public class TResp {

    private Long id;

    private String resourceCode;

    private String resourceName;

    private String url;

    private String permission;

    private String resourceParentCode;

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

    public void setResourceParentCode (String resourceParentCode){
        this.resourceParentCode = resourceParentCode;
    }

    public String getResourceParentCode (){
        return this.resourceParentCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}