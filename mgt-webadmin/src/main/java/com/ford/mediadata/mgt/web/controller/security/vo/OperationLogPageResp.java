package com.ford.mediadata.mgt.web.controller.security.vo;

import com.ford.mediadata.mgt.entity.security.OperationType;
import com.ford.mediadata.mgt.web.controller.vo.PageResp;

import java.util.Date;

/**
 * Created by wanglijun on 16/12/3.
 */
public class OperationLogPageResp extends PageResp {

    protected Long id;
    /**用户名*/
    protected String userName;
    /**
     * 用户ID
     * */
    private Long userId;

    /**操作类型*/
    protected OperationType operationType;
    /**操作类型名称*/
    protected  String  operationTypeName;

    /**操作内容*/
    protected String operationContent;

    /**操作日期*/
    protected Date operationDate;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public String getOperationTypeName() {
        return operationTypeName;
    }

    public void setOperationTypeName(String operationTypeName) {
        this.operationTypeName = operationTypeName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
