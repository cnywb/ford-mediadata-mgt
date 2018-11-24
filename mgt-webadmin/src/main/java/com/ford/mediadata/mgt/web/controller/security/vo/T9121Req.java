/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * T9123Req.java 15/11/2 22:33
 */
package com.ford.mediadata.mgt.web.controller.security.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：新增角色授权资源
 *
 * @author ziv.hung on 15/11/2.
 * @since 1.0
 */
public class T9121Req {

    private Long roleId;

    private List<T9121ReqVo> reqVoList = new ArrayList<T9121ReqVo>();

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<T9121ReqVo> getReqVoList() {
        return reqVoList;
    }

    public void setReqVoList(List<T9121ReqVo> reqVoList) {
        this.reqVoList = reqVoList;
    }
}