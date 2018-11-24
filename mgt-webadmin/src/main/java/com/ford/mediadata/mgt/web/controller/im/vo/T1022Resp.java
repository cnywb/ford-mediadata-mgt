/*
 * Copyright (c)  2015, dabing
 * All rights reserved. 
 * T7012Resp.java 15/10/13 下午6:07
 */
package com.ford.mediadata.mgt.web.controller.im.vo;

/**
 * 描述：字典项明细项目对象
 *
 * @author ziv.hung
 * @since 1.0
 */
public class T1022Resp {

    private Long id;
    /** 字典大类代码 */
    private String typeCode;
    /** 编码值 */
    private String code;
    /** 编码中文名称 */
    private String nameCn;
    /** 编码英文名称 */
    private String nameEn;
    /** 编码排序 */
    private int sortNo;
    /** 默认选择 */
    private Boolean selected;

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

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }


    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
