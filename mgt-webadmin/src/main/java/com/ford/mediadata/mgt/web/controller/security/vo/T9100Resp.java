package com.ford.mediadata.mgt.web.controller.security.vo;

/**
 * Created by 阳葵 on 15/10/31.
 */
public class T9100Resp {
    private Long id;
    /**
     * 角色代码
     */
    private String code;
    /**
     * 角色名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFirstInsert() {
        return firstInsert;
    }

    public void setFirstInsert(String firstInsert) {
        this.firstInsert = firstInsert;
    }
}
