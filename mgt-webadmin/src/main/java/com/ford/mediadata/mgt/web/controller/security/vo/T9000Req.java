/*
 * Copyright (c)  2015, dabing.io
 * All rights reserved. 
 * T9000Req.java 15/10/19 下午3:56
 */
package com.ford.mediadata.mgt.web.controller.security.vo;

import io.dabing.common.grid.GridPage;


/**
 * 描述 管理人员数据列表请求对象
 *
 * @author Created by ziv.hung on 15/10/19.
 * @since 1.0
 */
public class T9000Req {

    private GridPage page;

    public GridPage getPage() {
        return page;
    }

    public void setPage(GridPage page) {
        this.page = page;
    }
}
