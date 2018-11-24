/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * T9111Req.java 15/11/2 下午6:04
 */
package com.ford.mediadata.mgt.web.controller.security.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：创建角色用户
 *
 * @author Create by ziv.hung on 15/11/2.
 * @since 1.0
 */
public class T9111Req {

    private String roleCode;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    private List<T9111ReqVo> reqVoList = new ArrayList<T9111ReqVo>();

    public List<T9111ReqVo> getReqVoList() {
        return reqVoList;
    }

    public void setReqVoList(List<T9111ReqVo> reqVoList) {
        this.reqVoList = reqVoList;
    }
}
