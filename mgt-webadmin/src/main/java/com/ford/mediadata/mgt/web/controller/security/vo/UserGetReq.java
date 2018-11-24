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
 * All rights reserved. 2016-11-19 23:09
 * </p>
 *
 * @author MaoJiaWei
 * @version 1.0
 */
public class UserGetReq extends CommonGetReq {
    /**
     * 批次号
     */
    private String batchNo;
    /**
     * 登录名称
     */
    private String loginName;
    /**
     * 真实名称
     */
    private String name;

    /**
     * 关键字
     */
    private String keyWord;

    /**
     * 联系电话
     */
    private String mobile;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
