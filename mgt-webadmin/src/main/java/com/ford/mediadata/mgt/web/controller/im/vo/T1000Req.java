package com.ford.mediadata.mgt.web.controller.im.vo;

import io.dabing.common.grid.GridPage;

/**
 * Created by 阳葵 on 15/10/14.
 * 分页查询数据字典大类
 */
public class T1000Req {
    private String typeCode;
    private GridPage page;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public GridPage getPage() {
        return page;
    }

    public void setPage(GridPage page) {
        this.page = page;
    }
}
