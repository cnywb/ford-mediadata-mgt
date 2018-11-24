package com.ford.mediadata.mgt.web.controller.security.vo;/**
 * Created by jovi on 19/11/2016.
 */

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * All rights reserved. 2016-11-19 17:58
 * </p>
 *
 * @author MaoJiaWei
 * @version 1.0
 */
public class BasUserImportPageResp {
    /**
     * 主键
     */
    private Long id;

    /**
     * 批次号
     * @return
     */
    private String batchNo;

    /**
     * 成功数量
     * @return
     */
    private Long addNum;

    /**
     * 失败数量
     * @return
     */
    private Long failNum;

    /**
     * 总数
     * @return
     */
    private Long totalNum;

    /**
     * 导入时间
     * @return
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date importDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Long getAddNum() {
        return addNum;
    }

    public void setAddNum(Long addNum) {
        this.addNum = addNum;
    }

    public Long getFailNum() {
        return failNum;
    }

    public void setFailNum(Long failNum) {
        this.failNum = failNum;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }
}
