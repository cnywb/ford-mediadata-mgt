package com.ford.mediadata.mgt.web.controller.im.vo;

/**
 * Created by 阳葵 on 15/10/14.
 *
 */
public class T1001Resp {
    private String codeValue;
    /** 编码中文名称 */
    private String nameCn;
    /** 编码英文名称 */
    private String nameEn;
    /** 编码排序 */
    private int sortNo;
    private String code;

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
