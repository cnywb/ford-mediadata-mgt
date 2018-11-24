/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * T9003Req.java 15/10/19 下午4:00
 */
package com.ford.mediadata.mgt.web.controller.security.vo;

import java.util.List;

/**
 * 描述：管理人员 信息删除请求对象
 *
 * @author Create by ziv.hung on 15/10/19.
 * @since 1.0
 */
public class T9003Req {
    private List<T9003ReqVo> reqs;

    public List<T9003ReqVo> getReqs() {
        return reqs;
    }

    public void setReqs(List<T9003ReqVo> reqs) {
        this.reqs = reqs;
    }
}
