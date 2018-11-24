/*
 * Copyright (c)  2016, dabing.io
 * All rights reserved.
 * AuthorizeResourceReq.java 2016-02-26 19:04
 */

package com.ford.mediadata.mgt.web.controller.security.vo;

import java.util.List;

/**
 * 描述: 授权资源请求对象
 *
 * @author ziv.hung create on 2016-02-26.
 * @since 1.0
 */
public class AuthorizeResourceReq {

    private String roleCode;

    private List<PermissionVo> permissionVoList;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public List<PermissionVo> getPermissionVoList() {
        return permissionVoList;
    }

    public void setPermissionVoList(List<PermissionVo> permissionVoList) {
        this.permissionVoList = permissionVoList;
    }
}