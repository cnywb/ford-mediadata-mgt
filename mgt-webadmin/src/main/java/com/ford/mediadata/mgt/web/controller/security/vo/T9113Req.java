/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * T9100.java 15/10/31 上午9:49
 */
package com.ford.mediadata.mgt.web.controller.security.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：删除 角色用户
 *
 * @author ziv.hung on 15/11/2.
 * @since 1.0
 */
public class T9113Req {

    private String roleCode;

    private List<T9113ReqVo> reqVos = new ArrayList<T9113ReqVo>();

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public List<T9113ReqVo> getReqVos() {
        return reqVos;
    }

    public void setReqVos(List<T9113ReqVo> reqVos) {
        this.reqVos = reqVos;
    }
}