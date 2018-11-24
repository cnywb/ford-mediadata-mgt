/*
 * Copyright (c)  2016, dabing.io
 * All rights reserved.
 * ViewPermissionResp.java 2016-02-26 19:05
 */

package com.ford.mediadata.mgt.web.controller.security.vo;

/**
 * 描述: 已有资源权限预览响应对象
 *
 * @author ziv.hung create on 2016-02-26.
 * @since 1.0
 */
public class ViewPermissionResp {
    //大写的名称用于接收sql查询返回,小写名称用于页面渲染使用
    private Object RESOURCEID;
    private Object RESOURCECODE;
    private Object RESOURCENAME;
    private Object PARENTCODE;
    private Object ISMENU;
    private Object resourceId;
    private Object resourceCode;
    private Object resourceName;
    private Object parentCode;
    private Object isMenu;
    private Object permissionBoolean;

    private Object PRESOURCECODE;

    public Object getRESOURCEID() {
        return RESOURCEID;
    }

    public void setRESOURCEID(Object RESOURCEID) {
        this.RESOURCEID = RESOURCEID;
    }

    public Object getRESOURCECODE() {
        return RESOURCECODE;
    }

    public void setRESOURCECODE(Object RESOURCECODE) {
        this.RESOURCECODE = RESOURCECODE;
    }

    public Object getRESOURCENAME() {
        return RESOURCENAME;
    }

    public void setRESOURCENAME(Object RESOURCENAME) {
        this.RESOURCENAME = RESOURCENAME;
    }

    public Object getPARENTCODE() {
        return PARENTCODE;
    }

    public void setPARENTCODE(Object PARENTCODE) {
        this.PARENTCODE = PARENTCODE;
    }

    public Object getISMENU() {
        return ISMENU;
    }

    public void setISMENU(Object ISMENU) {
        this.ISMENU = ISMENU;
    }

    public Object getPermissionBoolean() {
        return permissionBoolean;
    }

    public void setPermissionBoolean(Object permissionBoolean) {
        this.permissionBoolean = permissionBoolean;
    }

    public Object getResourceId() {
        return resourceId;
    }

    public void setResourceId(Object resourceId) {
        this.resourceId = resourceId;
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

    public Object getParentCode() {
        return parentCode;
    }

    public void setParentCode(Object parentCode) {
        this.parentCode = parentCode;
    }

    public Object getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Object isMenu) {
        this.isMenu = isMenu;
    }

    public Object getPRESOURCECODE() {
        return PRESOURCECODE;
    }

    public void setPRESOURCECODE(Object PRESOURCECODE) {
        this.PRESOURCECODE = PRESOURCECODE;
    }
}