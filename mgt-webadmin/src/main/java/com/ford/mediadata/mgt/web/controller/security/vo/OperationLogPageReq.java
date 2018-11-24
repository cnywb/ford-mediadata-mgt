package com.ford.mediadata.mgt.web.controller.security.vo;

import com.ford.mediadata.mgt.entity.security.OperationType;
import com.ford.mediadata.mgt.web.controller.vo.PageReq;

import io.dabing.common.date.DateUtil;

import java.util.Date;

/**
 * Created by wanglijun on 16/12/3.
 */
public class OperationLogPageReq extends PageReq {
    /**用户名*/
    protected String userName;


    /**操作类型*/
    protected OperationType operationType;

    /**起始日期*/
    private Date startDate;
    /**结束日期*/
    private Date endDate;


    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Date getEndDateCover() {
        return this.endDate != null ? DateUtil.getNextDay(this.endDate, 1) : null;
    }
}
