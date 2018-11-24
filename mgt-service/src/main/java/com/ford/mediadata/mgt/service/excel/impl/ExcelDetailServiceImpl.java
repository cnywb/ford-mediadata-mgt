/*
 * Copyright (c)  2016, dabing.io
 * All rights reserved.
 * ExcelDetailServiceImpl.java 2016-05-12 15:03:01
 */
package com.ford.mediadata.mgt.service.excel.impl;


import com.ford.mediadata.mgt.entity.excel.ExcelDetail;
import com.ford.mediadata.mgt.repository.excel.ExcelDetailRepository;
import com.ford.mediadata.mgt.service.excel.ExcelDetailService;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ziv.hung on 2016-05-12 15:03:01 .
 *
 * @since 1.0
 */
@Service
public class ExcelDetailServiceImpl implements ExcelDetailService {
    @Autowired
    private ExcelDetailRepository repository;

    /**
     * 根据GridPage对象按分页查找服务
     *
     * @param page 分页对象，里面有关键字进行模糊匹配
     * @return
     */
    public Page<ExcelDetail> findByGridPage(GridPage page) {
        return repository.findByGridPage(page);
    }

    /**
     * 根据模块ID获取 excel列数据
     *
     * @param excelListId 模块ID
     * @return
     */
    public List<ExcelDetail> findBy(Long excelListId) {
        return repository.findBy(excelListId);
    }

    public void save(ExcelDetail object) {
        repository.save(object);
    }

    /**
     * 物理删除
     */
    public void delete(ExcelDetail object) {
        this.repository.delete(object);
    }

    /**
     * 物理删除
     */
    public void delete(Long id) {
        this.repository.delete(id);
    }

    public void update(ExcelDetail object) {
        repository.update(object);
    }

    public ExcelDetail get(Long id) {
        return repository.get(id);
    }
}