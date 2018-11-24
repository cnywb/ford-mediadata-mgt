/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * T9123ReqVo.java 15/11/2 22:33
 */
package com.ford.mediadata.mgt.web.controller.security.vo;

/**
 * 描述：删除授权资源
 *
 * @author ziv.hung on 15/11/2.
 * @since 1.0
 */
public class T9121ReqVo {
    private Long id;
    private String resourceCode;
    private String resourceName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}