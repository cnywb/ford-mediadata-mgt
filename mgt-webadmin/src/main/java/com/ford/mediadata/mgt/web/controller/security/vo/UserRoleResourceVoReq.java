package com.ford.mediadata.mgt.web.controller.security.vo;

import io.dabing.common.grid.GridPage;

/**
 * Created by ziv.hung on 16/8/22.
 */

public class UserRoleResourceVoReq {

    private GridPage page;
    private String userName;
    private String roleName;
    private String resourceName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public GridPage getPage() {
        return page;
    }

    public void setPage(GridPage page) {
        this.page = page;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
