package com.ford.mediadata.mgt.web.controller;/**
 * Created by jovi on 6/6/16.
 */

import io.dabing.common.grid.GridPage;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * All rights reserved. 2016-06-06 19:50
 * </p>
 *
 * @author MaoJiaWei
 * @version 1.0
 */
public abstract class CommonGetReq {

    private GridPage page;

    public GridPage getPage() {
        return page;
    }

    public void setPage(GridPage page) {
        this.page = page;
    }
}
