/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * T9111Req.java 15/11/2 下午6:04
 */
package com.ford.mediadata.mgt.web.controller.security.vo;

/**
 * 描述：角色添加用户
 *
 * @author Create by ziv.hung on 15/11/2.
 * @since 1.0
 */
public class T9111ReqVo {

    private Long id;
    private String name;
    private String employeeNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }
}
