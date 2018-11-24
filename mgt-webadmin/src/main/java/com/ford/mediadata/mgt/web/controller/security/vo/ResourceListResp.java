/*
 * Copyright (c)  2016, dabing.io
 * All rights reserved.
 * ResourceListReq.java 2016-02-23 16:45
 */

package com.ford.mediadata.mgt.web.controller.security.vo;

/**
 * 描述：Resource 数据获取响应对象
 *
 * @author ziv.hung create on 2016-02-23 16:43
 * @since 1.0
 */
public class ResourceListResp {

    private Long id;

    /* 资源代码 系统自动生成 */
    private String code;

    /* 资源名称 */
    private String name;

    /* 资源是否为菜单 是 或 否 */
    private String isMenu;

    /* 上级资源 资源代码 */
    private String parentCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(String isMenu) {
        this.isMenu = isMenu;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}