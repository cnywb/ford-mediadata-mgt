/*
 * Copyright (c)  2015, dabing
 * All rights reserved. 
 * T7000Resp.java 15/10/13 下午4:44
 */
package com.ford.mediadata.mgt.web.controller.im.vo;

/**
 * 描述：字典大类列表数据
 *
 * @author ziv.hung
 * @since 1.0
 */
public class T1010Resp {
    private Long id;
    /**
     * 字典大类代码
     */
    private String code;
    /**
     * 类型代码，用来区分各种字典分类
     */
    private String typeCode;
    /**
     * 中文名称
     */
    private String nameCn;
    /**
     * 英文名称
     */
    private String nameEn;
    /**
     * 描述
     */
    private String comment;
    /** 创建时间 */
    private String firstInsert;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFirstInsert() {
        return firstInsert;
    }

    public void setFirstInsert(String firstInsert) {
        this.firstInsert = firstInsert;
    }
}
