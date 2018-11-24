/*
 * Copyright (c)  2016, dabing.io
 * All rights reserved.
 * ExcelList.java 2016-05-12 14:26
 */
package com.ford.mediadata.mgt.entity.excel;


import com.ford.mediadata.mgt.entity.VersionEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 描述：ExcelList 数据模型 导入导出业务信息表
 *
 * @author ziv.hung create on 2016-05-12 14:26
 * @since 1.0
 */
@Entity
@Table(name = "MD_EXCEL_LIST")
public class ExcelList extends VersionEntity {

    /**
     * 客户excel导入/导出 业务标识代码
     */
    public static final String B_CODE_PRODUCT = "PRODUCT";
    /**
     * 敏感客户导入
     */
    public static final String B_CODE_CUSTOMER = "customer";

    /**
     * 日志excel导入/导出 业务标识代码
     */
    public static final String B_CODE_LOG = "LOG";


    /**
     * 客户excel导入/导出 业务标识代码
     */
    public static final String B_CODE_USER_ROLE_RESOURCE = "USER_ROLE_RESOURCE";

    /**
     * 话单导出
     */
    public static final String B_CALLORDER = "CALLORDER";

    /**
     * 订单导出
     */
    public static final String B_COMMONORDER = "COMMONORDER";

    public static final String TYPE_IMPORT = "导入";

    public static final String TYPE_EXPORT = "导出";
    @Id
    @Column(name = "ID")
    private Long id;
    /* 模块编码 */
    @Column(name = "BUSINESS_CODE")
    protected String businessCode;
    /* 导出模块描述信息 */
    @Column(name = "BUSINESS_DESC")
    protected String businessDesc;
    /* 操作类型 导出 , 导入 */
    @Column(name = "BUSINESS_TYPE")
    protected String businessType;
    // 操作类型为导出 时 生成文件 命名
    @Column(name = "FILE_NAME")
    protected String fileName;
    // excel的sheet名称
    @Column(name = "SHEET_NAME")
    protected String sheetName;
    // 处理导入或导出的数据上线
    @Column(name = "LIMITS")
    protected Long limits;

    @Column(name = "FROM_")
    private String from;

    @Column(name = "WHERE_")
    private String where;

    @Column(name = "ORDERBY_")
    private String orderBy;
    //获取数据的服务名称,为空时采用默认值
    @Column(name = "SERVICE_NAME")
    private String serviceName;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessDesc() {
        return businessDesc;
    }

    public void setBusinessDesc(String businessDesc) {
        this.businessDesc = businessDesc;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public static String getTypeImport() {
        return TYPE_IMPORT;
    }

    public static String getTypeExport() {
        return TYPE_EXPORT;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Long getLimits() {
        return limits;
    }

    public void setLimits(Long limits) {
        this.limits = limits;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}