package com.ford.mediadata.mgt.web.controller.im.vo;

import io.dabing.common.grid.GridPage;

/**
 * Created by 阳葵 on 15/10/14.
 * 根据大类代码分页查询字典小类
 */
public class T1001Req {
    private GridPage page;
    private String parentCode;

    public GridPage getPage() {
        return page;
    }

    public void setPage(GridPage page) {
        this.page = page;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
