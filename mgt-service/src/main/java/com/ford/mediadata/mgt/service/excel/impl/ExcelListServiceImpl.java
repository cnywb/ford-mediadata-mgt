/*
 * Copyright (c)  2016, dabing.io
 * All rights reserved.
 * ExcelListServiceImpl.java 2016-05-12 14:55:25
 */
package com.ford.mediadata.mgt.service.excel.impl;


import com.ford.mediadata.mgt.entity.excel.ExcelList;
import com.ford.mediadata.mgt.repository.excel.ExcelListRepository;
import com.ford.mediadata.mgt.service.excel.ExcelListService;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by ziv.hung on 2016-05-12 14:55:25 .
 *
 * @since 1.0
 */
@Service
public class ExcelListServiceImpl implements ExcelListService {
    @Autowired
    private ExcelListRepository repository;

    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param page 分页对象，里面有关键字进行模糊匹配
     * @return
     */
    public Page<ExcelList> findByGridPage(GridPage page) {
        return repository.findByGridPage(page);
    }

    public List<ExcelList> findAll() {
        return repository.findAll();
    }

    public ExcelList findBy(String businessCode, String businessType) {
        List<ExcelList> excelListList = repository.findBy(businessCode, businessType);
        if (excelListList != null && excelListList.size() > 0) {
            return excelListList.get(0);
        }
        return null;
    }

    /**
     * 根据fileName 和 businessCode 获取ExcelList对象
     *
     * @param fileName     文件名称
     * @param businessType 模块代码对应的操作类型
     * @return
     */
    @Override
    public List<ExcelList> findByFile(String fileName, String businessType) {
        return this.repository.findByFile(fileName, businessType);
    }

    public void save(ExcelList object) {
        repository.save(object);
    }

    /**
     * 物理删除
     */
    public void delete(ExcelList object) {
        this.repository.delete(object);
    }

    /**
     * 物理删除
     */
    public void delete(Long id) {
        this.repository.delete(id);
    }

    public void update(ExcelList object) {
        repository.update(object);
    }

    public ExcelList get(Long id) {
        return repository.get(id);
    }
}