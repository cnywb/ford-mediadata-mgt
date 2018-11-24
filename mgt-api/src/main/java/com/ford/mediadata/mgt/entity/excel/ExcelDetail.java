/*
 * Copyright (c)  2016, dabing.io
 * All rights reserved.
 * ExcelList.java 2016-05-12 14:26
 */
package com.ford.mediadata.mgt.entity.excel;


import javax.persistence.*;

import com.ford.mediadata.mgt.entity.VersionEntity;

/**
 * 描述：ExcelList 数据模型 导入导出标头定制明细表
 *
 * @author Richard create on 2016-05-12 14:26
 * @since 1.0
 */
@Entity
@Table(name = "MD_EXCEL_DETAIL")
public class ExcelDetail extends VersionEntity {


    public static final String DATA_TYPE_STRING = "String";

    public static final String DATA_TYPE_INTEGER = "Integer";

    public static final String DATA_TYPE_LONG = "Long";

    public static final String DATA_TYPE_DATE = "Date";

    public static final String DATA_TYPE_DOUBLE = "Double";

    @Id
    @Column(name = "ID")
    private Long id;
    /* 模块配置 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXCEL_LIST_ID")
    protected ExcelList excelList;
    /* excel 列名称 */
    @Column(name = "EXCEL_CELL")
    protected String excelCell;
    /* excel 列号(也可当 导出时表头的排序使用) */
    @Column(name = "EXCEL_CELL_NUM")
    protected Long excelCellNum;
    /* 模块对应模型字段名称 */
    @Column(name = "TABLE_COLUMN")
    protected String tableColumn;
    /* 数据类型, String,Integer,Long,Date,Double */
    @Column(name = "DATA_TYPE")
    protected String dataType;
    /* 是否必填 */
    @Column(name = "NOT_NULL")
    protected Boolean notNull;

    /***
     * 主键方法
     *
     * @return
     */
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExcelCell() {
        return excelCell;
    }

    public void setExcelCell(String excelCell) {
        this.excelCell = excelCell;
    }

    public Long getExcelCellNum() {
        return excelCellNum;
    }

    public void setExcelCellNum(Long excelCellNum) {
        this.excelCellNum = excelCellNum;
    }

    public String getTableColumn() {
        return tableColumn;
    }

    public void setTableColumn(String tableColumn) {
        this.tableColumn = tableColumn;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public ExcelList getExcelList() {
        return excelList;
    }

    public void setExcelList(ExcelList excelList) {
        this.excelList = excelList;
    }

    public Boolean getNotNull() {
        return notNull;
    }

    public void setNotNull(Boolean notNull) {
        this.notNull = notNull;
    }
}