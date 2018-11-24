package com.ford.mediadata.mgt.web.controller.security.vo;/**
 * Created by jovi on 19/11/2016.
 */

import java.util.Date;

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
 * All rights reserved. 2016-11-19 22:34
 * </p>
 *
 * @author MaoJiaWei
 * @version 1.0
 */
public class DealerUserImportGetReq extends CommonGetReq {
    /**
     *  批次号
     */
    private String batchNo;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
