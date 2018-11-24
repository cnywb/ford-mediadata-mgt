/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved.
 * Resource.java 2015-11-01 12:06:33
 */
package com.ford.mediadata.mgt.web.controller.security.vo;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：批量删除请求
 *
 * Created by yangkui on 2015-11-01 12:06:33.
 * @since 1.0
*/
public class T9203Req {
    private List<T9203ReqVo> reqVos = new ArrayList<T9203ReqVo>();

    public List<T9203ReqVo> getReqVos() {
        return reqVos;
    }

    public void setReqVos(List<T9203ReqVo> reqVos) {
        this.reqVos = reqVos;
    }
}