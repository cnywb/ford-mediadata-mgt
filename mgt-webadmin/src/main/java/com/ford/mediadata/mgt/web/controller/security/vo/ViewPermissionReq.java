/*
 * Copyright (c)  2016, dabing.io
 * All rights reserved.
 * ViewPermissionReq.java 2016-02-26 19:05
 */

package com.ford.mediadata.mgt.web.controller.security.vo;

/**
 * 描述: 已有资源权限预览请求对象
 *
 * @author ziv.hung create on 2016-02-26.
 * @since 1.0
 */
public class ViewPermissionReq {

    private String roleCode;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}