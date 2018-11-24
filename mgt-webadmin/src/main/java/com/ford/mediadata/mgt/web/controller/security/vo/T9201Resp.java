/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved.
 * Resource.java 2015-11-01 12:06:33
 */
package com.ford.mediadata.mgt.web.controller.security.vo;

/**
 * 描述：对象创建或修改请求返回
 *
 * Created by yangkui on 2015-11-01 12:06:33.
 * @since 1.0
*/
public class T9201Resp {
    //为空则代码创建/修改成功
    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}