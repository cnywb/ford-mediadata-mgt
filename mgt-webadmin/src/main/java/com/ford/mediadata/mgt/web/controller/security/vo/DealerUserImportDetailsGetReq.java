package com.ford.mediadata.mgt.web.controller.security.vo;/**
 * Created by jovi on 19/11/2016.
 */

import com.ford.mediadata.mgt.web.controller.CommonGetReq;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * All rights reserved. 2016-11-19 22:43
 * </p>
 *
 * @author MaoJiaWei
 * @version 1.0
 */
public class DealerUserImportDetailsGetReq extends CommonGetReq {
    /**
     * 批次号
     */
    private String batchNo;
    /**
     * 登录名称
     */
    private String loginName;
    /**
     * 经销商代码
     */
    private String dealerCode;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }
}
