/*
 * Copyright (c)  2016, dabing.io
 * All rights reserved.
 * ResourceHandleReq.java 2016-02-23 16:42
 */
package com.ford.mediadata.mgt.web.controller.security.vo;

/**
 * 描述：Resource 数据新增/修改数据请求对象
 *
 * @author ziv.hung create on 2016-02-23 16:42
 * @since 1.0
 */
public class ResourceHandleReq {

    private Long id;
    /* 资源类型 */
    private String type;
    /* 是否菜单 */
    private String isMenu;
    /* 资源名称 */
    private String name;
    /* 资源,URL或权限识别码 */
    private String permission;
    /* 父资源代码 */
    private String parentCode;
    /* 父资源名称 */
    private String parentName;
    /* 资源 排序 */
    private Integer sortNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(String isMenu) {
        this.isMenu = isMenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}
