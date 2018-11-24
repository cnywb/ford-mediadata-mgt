package com.ford.mediadata.mgt.web.controller.im.vo;

/**
 * Created by 阳葵 on 15/10/14.
 * 分页查询字典大类
 */
public class T1000Resp {
    private String typeCode;
    /** 中文名称 */
    private String nameCn;
    /** 英文名称 */
    private String nameEn;
    private String code;
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
}
