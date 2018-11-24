package com.ford.mediadata.mgt.web.controller.vo;

import io.dabing.common.grid.GridPage;

/**
 * 分页请求页面
 * Created by wanglijun on 16/7/9.
 */
public class PageReq {

    protected GridPage page;



    public GridPage getPage() {
        return page;
    }

    public void setPage(GridPage page) {
        this.page = page;
    }


}
