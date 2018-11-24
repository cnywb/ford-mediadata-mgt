/*
 * Copyright (c)  2015, dabing
 * All rights reserved. 
 * 7010.java 15/10/13 下午6:01
 */
package com.ford.mediadata.mgt.web.controller.im.vo;

import io.dabing.common.grid.GridPage;

/**
 * 描述：字典项列表查询请求对象
 *
 * @author ziv.hung
 * @since 1.0
 */
public class T1020Req {

    private String typeCode;

    private GridPage page;

    public GridPage getPage() {
        return page;
    }

    public void setPage(GridPage page) {
        this.page = page;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
