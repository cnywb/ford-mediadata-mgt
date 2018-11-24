/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * T9000Resp.java 15/10/19 下午3:53
 */
package com.ford.mediadata.mgt.web.controller.security.vo;

/**
 * 描述：管理人员数据列表查询响应对象
 *
 * @author Create by ziv.hung on 15/10/19.
 * @since 1.0
 */
public class T9000Resp {
    private Long id;

    /**
     * 后台登录用户名
     */
    private String loginName;
    /**
     * 姓名
     */
    private String name;
    /**
     * 联系电话
     */
    private String mobile;
    /**
     * 邮件
     */
    private String email;
    /**
     * 机构ID
     */
    private Long orgId;
    /**
     * 机构代码
     */
    private String orgCode;
    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 创建时间
     */
    private String firstInsert;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getFirstInsert() {
        return firstInsert;
    }

    public void setFirstInsert(String firstInsert) {
        this.firstInsert = firstInsert;
    }
}
