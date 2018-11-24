/*
 * Copyright (c)  2015, dabing
 * All rights reserved. 
 * ImCodeType.java 15/10/13 下午4:22
 */
package com.ford.mediadata.mgt.service.im;

import io.dabing.common.grid.GridPage;
import io.dabing.core.repository.domain.Page;
import io.dabing.core.service.Service;

import java.util.List;

import com.ford.mediadata.mgt.entity.im.ImCodeType;

/**
 * 描述：字典大类服务
 *
 * @author ziv.hung
 * @since 1.0
 */
public interface ImCodeTypeService extends Service {
    /**
     * 根据GridPage对象按分页查找
     *
     * @param page 分页对象，里面有关键字进行模糊匹配
     * @return
     */
    Page<ImCodeType> findByGridPage(GridPage page);

    /**
     * 根据字典分类代码查询所有字典
     *
     * @param typeCode 字典分类代码 不允许为空
     * @param page     分页对象，里面有关键字进行模糊匹配
     * @return
     */
    Page<ImCodeType> findByCodeGridPage(String typeCode, GridPage page);

    /**
     * 根据商户获取所有大类列表
     *
     * @param typeCode 字典大类类别 允许为空
     * @return
     */
    List<ImCodeType> findAll(String typeCode);

    void save(ImCodeType object);

    void update(ImCodeType object);

    ImCodeType get(Long id);

    void delete(Long id);
}