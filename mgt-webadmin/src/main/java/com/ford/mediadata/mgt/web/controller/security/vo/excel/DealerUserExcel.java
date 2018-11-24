package com.ford.mediadata.mgt.web.controller.security.vo.excel;/**
 * Created by jovi on 19/11/2016.
 */

import com.ford.mediadata.mgt.service.excel.impl.ExcelImportBase;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * All rights reserved. 2016-11-19 21:43
 * </p>
 *
 * @author MaoJiaWei
 * @version 1.0
 */
public class DealerUserExcel extends ExcelImportBase {
    /**
     * 登录名称
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 经销商代码
     */
    private String dealerCode;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }
}
