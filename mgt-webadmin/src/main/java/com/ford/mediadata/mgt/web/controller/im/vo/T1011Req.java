/*
 * Copyright (c)  2015, dabing
 * All rights reserved. 
 * T7001Req.java 15/10/13 下午4:46
 */
package com.ford.mediadata.mgt.web.controller.im.vo;

/**
 * 描述：创建字典大类请求对象
 *
 * @author ziv.hung
 * @since 1.0
 */
public class T1011Req {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
