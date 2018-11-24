/*
 * Copyright (c)  2016, dabing.io
 * All rights reserved.
 * ExcelImportBase.java 2016-05-13 14:28
 */

package com.ford.mediadata.mgt.service.excel.impl;

/**
 * 描述:TODO
 *
 * @author ziv.hung create on 2016-05-13.
 * @since 1.0
 */
public class ExcelImportBase {

    private String status;

    private String errorMsg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}