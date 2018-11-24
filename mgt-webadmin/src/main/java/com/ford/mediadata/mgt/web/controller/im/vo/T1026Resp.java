/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * T1026Req.java 15/10/15 下午5:42
 */
package com.ford.mediadata.mgt.web.controller.im.vo;

/**
 * 描述：根据字典大类代码获取 字典项响应对象
 *
 * @author Create by ziv.hung on 15/10/15.
 * @since 1.0
 */
public class T1026Resp {
    /** 编码值 */
    private String code;
    /** 字典大类代码 */
    private String typeCode;
    /** 编码中文名称 */
    private String nameCn;
    /** 编码英文名称 */
    private String nameEn;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
}
