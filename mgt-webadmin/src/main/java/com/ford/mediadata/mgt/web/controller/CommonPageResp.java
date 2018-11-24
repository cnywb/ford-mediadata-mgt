package com.ford.mediadata.mgt.web.controller;/**
 * Created by jovi on 7/6/16.
 */

import java.io.Serializable;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * All rights reserved. 2016-07-06 10:46
 * </p>
 *
 * @author MaoJiaWei
 * @version 1.0
 */
public abstract class CommonPageResp implements Serializable{


    /**
     * 创建日期
     */
    private String createdDateStr;

    public String getCreatedDateStr() {
        return createdDateStr;
    }

    public void setCreatedDateStr(String createdDateStr) {
        this.createdDateStr = createdDateStr;
    }
}
