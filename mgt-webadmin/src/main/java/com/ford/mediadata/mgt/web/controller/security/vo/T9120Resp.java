/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * T9000Resp.java 15/10/19 下午3:53
 */
package com.ford.mediadata.mgt.web.controller.security.vo;

/**
 * 描述：已添加的资源数据列表查询响应对象
 *
 * @author Create by ziv.hung on 15/11/2.
 * @since 1.0
 */
public class T9120Resp {
    private Object roleCode;
    private Object parentCode;
    private Object permissionStr;
    private Object resourceCode;
    private Object resourceName;

    public Object getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(Object roleCode) {
        this.roleCode = roleCode;
    }

    public Object getParentCode() {
        return parentCode;
    }

    public void setParentCode(Object parentCode) {
        this.parentCode = parentCode;
    }

    public Object getPermissionStr() {
        return permissionStr;
    }

    public void setPermissionStr(Object permissionStr) {
        this.permissionStr = permissionStr;
    }

    public Object getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(Object resourceCode) {
        this.resourceCode = resourceCode;
    }

    public Object getResourceName() {
        return resourceName;
    }

    public void setResourceName(Object resourceName) {
        this.resourceName = resourceName;
    }
}
