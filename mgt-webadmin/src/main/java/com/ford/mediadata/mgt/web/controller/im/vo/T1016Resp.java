/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * T1016Resp.java 15/10/15 下午5:50
 */
package com.ford.mediadata.mgt.web.controller.im.vo;

/**
 * 描述：根据字典大类类别代码获取 字典大类响应对象
 *
 * @author Create by ziv.hung on 15/10/15.
 * @since 1.0
 */
public class T1016Resp {
    /** 字典大类代码 */
    private String code;
    /** 中文名称 */
    private String nameCh;
    /** 英文名称 */
    private String nameEn;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameCh() {
        return nameCh;
    }

    public void setNameCh(String nameCh) {
        this.nameCh = nameCh;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
}
