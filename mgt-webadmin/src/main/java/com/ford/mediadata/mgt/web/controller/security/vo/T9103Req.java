/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * T9103.java 15/10/31 上午9:50
 */
package com.ford.mediadata.mgt.web.controller.security.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：删除角色
 *
 * @author yangkui on 15/10/31.
 * @since 1.0
 */
public class T9103Req {

    private List<String> reqVos = new ArrayList<String>();

    public List<String> getReqVos() {
        return reqVos;
    }

    public void setReqVos(List<String> reqVos) {
        this.reqVos = reqVos;
    }
}