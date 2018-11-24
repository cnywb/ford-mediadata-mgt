/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * T9123ReqVo.java 15/11/2 22:33
 */
package com.ford.mediadata.mgt.web.controller.security.vo;

/**
 * 描述：已存在授权资源
 *
 * @author ziv.hung on 15/11/2.
 * @since 1.0
 */
public class T9121Resp {
    private String resourceCode;
    private String resourceName;

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