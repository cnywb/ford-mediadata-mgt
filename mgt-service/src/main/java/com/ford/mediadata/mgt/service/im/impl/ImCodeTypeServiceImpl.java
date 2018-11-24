/*
 * Copyright (c)  2015, dabing
 * All rights reserved. 
 * ImCodeType.java 15/10/13 下午4:23
 */
package com.ford.mediadata.mgt.service.im.impl;

import com.ford.mediadata.mgt.entity.im.ImCodeType;
import com.ford.mediadata.mgt.repository.im.ImCodeTypeRepository;
import com.ford.mediadata.mgt.service.im.ImCodeTypeService;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：字典大类服务实现
 *
 * @author ziv.hung
 * @since 1.0
 */
@Service
public class ImCodeTypeServiceImpl extends AbstractService implements ImCodeTypeService {
    @Autowired
    private ImCodeTypeRepository repository;

    /**
     * 根据GridPage对象按分页查找
     *
     * @param page 分页对象，里面有关键字进行模糊匹配
     * @return
     */
    public Page<ImCodeType> findByGridPage(GridPage page) {
        return repository.findByGridPage(page);
    }

    /**
     * 根据字典分类代码查询所有字典
     *
     * @param typeCode 字典分类代码 不允许为空
     * @param page     分页对象，里面有关键字进行模糊匹配
     * @return
     */
    public Page<ImCodeType> findByCodeGridPage(String typeCode, GridPage page) {
        return repository.findByCodeGridPage(typeCode, page);
    }

    public List<ImCodeType> findAll(String typeCode) {
        return repository.findAll(typeCode);
    }

    public void save(ImCodeType object) {
        this.repository.save(object);
    }

    public void update(ImCodeType object) {
        this.repository.update(object);
    }

    public ImCodeType get(Long id) {
        return this.repository.get(id);
    }

    public void delete(Long id) {
        this.repository.delete(id);
    }
}