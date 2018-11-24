/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * aaaa.java 15/11/3 下午4:10
 */
package com.ford.mediadata.mgt.entity.security;

/**
 * 描述：权限 角色资源缓存对象
 *
 * @author Create by ziv.hung on 15/11/3.
 * @since 1.0
 */
public class BasPermissionCacheVo {
    private Object resourceId;
    private Object roleCode;
    private Object parentCode;
    private Object permissionStr;
    private Object resourceCode;
    private Object resourceName;

    public Object getResourceId() {
        return resourceId;
    }

    public void setResourceId(Object resourceId) {
        this.resourceId = resourceId;
    }

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