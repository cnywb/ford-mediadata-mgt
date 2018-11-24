package com.ford.mediadata.mgt.web.controller.security.vo;

/**
 * Created by ziv.hung on 16/8/22.
 */

public class UserRoleResourceVo {

    private Object userName;
    private Object userLoginName;
    private Object roleCode;
    private Object roleName;
    private Object roleRemark;
    private Object resourceCode;
    private Object resourceName;
    private Object resourceType;
    private Object resourceIsMenu;
    private Object resourcePermission;

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public Object getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(Object userLoginName) {
        this.userLoginName = userLoginName;
    }

    public Object getRoleName() {
        return roleName;
    }

    public void setRoleName(Object roleName) {
        this.roleName = roleName;
    }

    public Object getRoleRemark() {
        return roleRemark;
    }

    public void setRoleRemark(Object roleRemark) {
        this.roleRemark = roleRemark;
    }

    public Object getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(Object roleCode) {
        this.roleCode = roleCode;
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

    public Object getResourceType() {
        return resourceType;
    }

    public void setResourceType(Object resourceType) {
        this.resourceType = resourceType;
    }

    public Object getResourceIsMenu() {
        return resourceIsMenu;
    }

    public void setResourceIsMenu(Object resourceIsMenu) {
        this.resourceIsMenu = resourceIsMenu;
    }

    public Object getResourcePermission() {
        return resourcePermission;
    }

    public void setResourcePermission(Object resourcePermission) {
        this.resourcePermission = resourcePermission;
    }
}
