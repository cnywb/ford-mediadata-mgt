/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * T9123Req.java 15/11/2 22:33
 */
package com.ford.mediadata.mgt.web.controller.security.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：删除角色授权资源
 *
 * @author ziv.hung on 15/11/2.
 * @since 1.0
 */
public class T9123Req {

    private Long roleId;

    private List<T9123ReqVo> reqVos = new ArrayList<T9123ReqVo>();

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<T9123ReqVo> getReqVos() {
        return reqVos;
    }

    public void setReqVos(List<T9123ReqVo> reqVos) {
        this.reqVos = reqVos;
    }
}