/*
 * Copyright (c)  2016, dabing.io
 * All rights reserved.
 * ExcelList.java 2016-05-12 14:55:25
 */
package com.ford.mediadata.mgt.service.excel;


import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;

import java.util.List;

import com.ford.mediadata.mgt.entity.excel.ExcelList;

/**
 * Created by ziv.hung on 2016-05-12 14:55:25 .
 *
 * @since 1.0
 */
public interface ExcelListService {
    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param page 分页对象，里面有关键字进行模糊匹配
     * @return
     */
    Page<ExcelList> findByGridPage(GridPage page);

    /**
     * 模块excel列数据
     *
     * @return
     */
    List<ExcelList> findAll();

    /**
     * 根据businessType 和 businessCode 获取ExcelList对象
     *
     * @param businessCode 模块代码
     * @param businessType 模块代码对应的操作类型
     * @return
     */
    ExcelList findBy(String businessCode, String businessType);

    /**
     * 根据fileName 和 businessCode 获取ExcelList对象
     *
     * @param fileName 文件名称
     * @param businessType 模块代码对应的操作类型
     * @return
     */
    List<ExcelList> findByFile(String fileName, String businessType);

    void save(ExcelList object);

    /**
     * 物理删除
     */
    void delete(ExcelList object);

    /**
     * 物理删除
     */
    void delete(Long id);

    void update(ExcelList object);

    ExcelList get(Long id);
}