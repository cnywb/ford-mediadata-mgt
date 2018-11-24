/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * T9100.java 15/10/31 上午9:49
 */
package com.ford.mediadata.mgt.web.controller.security.vo;

import io.dabing.common.grid.GridPage;

/**
 * 描述：查找角色
 *
 * @author yangkui on 15/11/2.
 * @since 1.0
 */
public class T9120Req {

    private Long roleId;

    private GridPage page;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public GridPage getPage() {
        return page;
    }

    public void setPage(GridPage page) {
        this.page = page;
    }
}