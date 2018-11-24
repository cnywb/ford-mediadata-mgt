/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * T1026Req.java 15/10/15 下午5:42
 */
package com.ford.mediadata.mgt.web.controller.im.vo;

/**
 * 描述：根据字典大类代码获取 字典项请求对象
 *
 * @author Create by ziv.hung on 15/10/15.
 * @since 1.0
 */
public class T1026Req {

    /** 字典大类代码 */
    private String typeCode;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
