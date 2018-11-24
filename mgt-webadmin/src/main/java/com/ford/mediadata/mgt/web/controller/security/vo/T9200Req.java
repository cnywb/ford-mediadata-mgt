/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved.
 * Resource.java 2015-11-01 12:06:33
 */
package com.ford.mediadata.mgt.web.controller.security.vo;
import io.dabing.common.grid.GridPage;

/**
 * 描述：分页查询请求
 *
 * Created by yangkui on 2015-11-01 12:06:33.
 * @since 1.0
*/
public class T9200Req {
    private GridPage page;

    public GridPage getPage() {
        return page;
    }

    public void setPage(GridPage page) {
        this.page = page;
    }
}